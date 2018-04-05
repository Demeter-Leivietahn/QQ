package qqchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyServer implements Runnable {
	// 定义保存所有Socket的ArrayList，并将其包装为线程安全的
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
	public Socket s;
	static int count=1;
	@Override
	public void run() {
		ServerSocket ss;
		try {
			System.out.println("***服务器已启动***");
			ss = new ServerSocket(8083);
			while (true) {
				// 此行代码会阻塞，将一直等待别人的连接
				Socket s = ss.accept();
 				System.out.println("***当前连接客户端个数:"+(count++)+"***");
 				socketList.add(s);
				// 每当客户端连接后启动一条ServerThread线程为该客户端服务
				new Thread(new ServerThread(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread(new MyServer()).start();
	}
}

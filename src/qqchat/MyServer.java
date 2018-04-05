package qqchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyServer implements Runnable {
	// ���屣������Socket��ArrayList���������װΪ�̰߳�ȫ��
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
	public Socket s;
	static int count=1;
	@Override
	public void run() {
		ServerSocket ss;
		try {
			System.out.println("***������������***");
			ss = new ServerSocket(8083);
			while (true) {
				// ���д������������һֱ�ȴ����˵�����
				Socket s = ss.accept();
 				System.out.println("***��ǰ���ӿͻ��˸���:"+(count++)+"***");
 				socketList.add(s);
				// ÿ���ͻ������Ӻ�����һ��ServerThread�߳�Ϊ�ÿͻ��˷���
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

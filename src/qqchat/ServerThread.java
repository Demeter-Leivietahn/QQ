package qqchat;

import java.io.*;
import java.net.*;

// 负责处理每个线程通信的线程类
public class ServerThread implements Runnable
{
//	// 定义当前线程所处理的Socket
//	Socket s = null;
//	// 该线程所处理的Socket所对应的输入流
//	BufferedReader br = null;
//	public ServerThread(Socket s)
//	throws IOException
//	{
//		this.s = s;
//		// 初始化该Socket对应的输入流
//		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//	}
//	public void run()
//	{
//		try
//		{
//			String content = null;
//			// 采用循环不断从Socket中读取客户端发送过来的数据
//			while ((content = readFromClient()) != null)
//			{
//				// 遍历socketList中的每个Socket，
//				// 将读到的内容向每个Socket发送一次
//				for (Socket s : MyServer.socketList)
//				{
//					PrintStream ps = new PrintStream(s.getOutputStream());
//					ps.println(content);
//				}
//			}
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//	// 定义读取客户端数据的方法
//	private String readFromClient()
//	{
//		try
//		{
//			return br.readLine();
//		}
//		// 如果捕捉到异常，表明该Socket对应的客户端已经关闭
//		catch (IOException e)
//		{
//			// 删除该Socket。
//			MyServer.socketList.remove(s);
//			return null;
//		}
//	}
	// 定义当前线程所处理的Socket
		Socket s = null;
		// 该线程所处理的Socket所对应的输入流
		ObjectInputStream ois = null;
		ObjectOutputStream oos=null;
		public ServerThread(Socket s)
		throws IOException
		{
			this.s = s;
			// 初始化该Socket对应的输入流
			ois = new ObjectInputStream(s.getInputStream());
		}
		public void run()
		{
			try
			{
				Message content = null;
				System.out.println("***服务器端线程已经启动***");
				// 采用循环不断从Socket中读取客户端发送过来的数据
				while ((content =(Message) ois.readObject()) != null)
				{
					// 遍历socketList中的每个Socket，
					// 将读到的内容向每个Socket发送一次
					for (Socket so : MyServer.socketList)
					{
						try{
							oos = new ObjectOutputStream(so.getOutputStream());
							oos.writeObject(content);
							oos.flush();
						}catch(IOException e){
							so.close();
							oos.close();
							s.close();
						}
					}
					content=null;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 定义读取客户端数据的方法
//		private String readFromClient()
//		{
//			try
//			{
//				return br.readLine();
//			}
//			// 如果捕捉到异常，表明该Socket对应的客户端已经关闭
//			catch (IOException e)
//			{
//				// 删除该Socket。
//				MyServer.socketList.remove(s);
//				return null;
//			}
//		}
}

package qqchat;

import java.io.*;
import java.net.*;

// ������ÿ���߳�ͨ�ŵ��߳���
public class ServerThread implements Runnable
{
//	// ���嵱ǰ�߳��������Socket
//	Socket s = null;
//	// ���߳��������Socket����Ӧ��������
//	BufferedReader br = null;
//	public ServerThread(Socket s)
//	throws IOException
//	{
//		this.s = s;
//		// ��ʼ����Socket��Ӧ��������
//		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//	}
//	public void run()
//	{
//		try
//		{
//			String content = null;
//			// ����ѭ�����ϴ�Socket�ж�ȡ�ͻ��˷��͹���������
//			while ((content = readFromClient()) != null)
//			{
//				// ����socketList�е�ÿ��Socket��
//				// ��������������ÿ��Socket����һ��
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
//	// �����ȡ�ͻ������ݵķ���
//	private String readFromClient()
//	{
//		try
//		{
//			return br.readLine();
//		}
//		// �����׽���쳣��������Socket��Ӧ�Ŀͻ����Ѿ��ر�
//		catch (IOException e)
//		{
//			// ɾ����Socket��
//			MyServer.socketList.remove(s);
//			return null;
//		}
//	}
	// ���嵱ǰ�߳��������Socket
		Socket s = null;
		// ���߳��������Socket����Ӧ��������
		ObjectInputStream ois = null;
		ObjectOutputStream oos=null;
		public ServerThread(Socket s)
		throws IOException
		{
			this.s = s;
			// ��ʼ����Socket��Ӧ��������
			ois = new ObjectInputStream(s.getInputStream());
		}
		public void run()
		{
			try
			{
				Message content = null;
				System.out.println("***���������߳��Ѿ�����***");
				// ����ѭ�����ϴ�Socket�ж�ȡ�ͻ��˷��͹���������
				while ((content =(Message) ois.readObject()) != null)
				{
					// ����socketList�е�ÿ��Socket��
					// ��������������ÿ��Socket����һ��
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
		// �����ȡ�ͻ������ݵķ���
//		private String readFromClient()
//		{
//			try
//			{
//				return br.readLine();
//			}
//			// �����׽���쳣��������Socket��Ӧ�Ŀͻ����Ѿ��ر�
//			catch (IOException e)
//			{
//				// ɾ����Socket��
//				MyServer.socketList.remove(s);
//				return null;
//			}
//		}
}

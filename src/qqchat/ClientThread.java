package qqchat;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import Util.photo;

public class ClientThread implements Runnable {
	// public JTextArea input=null;
	// // 该线程负责处理的Socket
	// private Socket s;
	// // 该线程所处理的Socket所对应的输入流
	// BufferedReader br = null;
	// public ClientThread(Socket s,JTextArea input)
	// throws IOException
	// {
	// this.s = s;
	// this.input=input;
	// br = new BufferedReader(
	// new InputStreamReader(s.getInputStream()));
	// }
	// public void run()
	// {
	// try
	// {
	// String content = null;
	// // 不断读取Socket输入流中的内容，并将这些内容打印输出
	// while ((content = br.readLine()) != null)
	// {
	// input.append(content+"\n");
	// }
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// }
	public StyledDocument sdc = null;
	public JTextPane input = null;
	public photo ph = new photo();
	public long count;
	public long friendcount;
	public YouPrintIguess2 you;
	public JButton guess;

	// 该线程负责处理的Socket
	private Socket s;
	// 该线程所处理的Socket所对应的输入流
	ObjectInputStream ois = null;

	public ClientThread(Socket s, JTextPane input, long count, long friendcount,YouPrintIguess2 you
			,JButton guess) throws IOException {
		this.s = s;
		this.input = input;
		this.count = count;
		this.friendcount = friendcount;
		this.you=you;
		this.guess=guess;
		ois = new ObjectInputStream(s.getInputStream());
	}

	public void run() {
		try {
			System.out.println("***客户端接收线程已启动***");
			Message content = null;
			// 不断读取Socket输入流中的内容，并将这些内容打印输出
			while ((content = (Message) ois.readObject()) != null) {
				if ((content.getOwner().equals(count + "") && content.getGetter().equals(friendcount + ""))
						|| (content.getOwner().equals(friendcount + "") && content.getGetter().equals(count + ""))
						||content.getOwner().equals("all")) {
					sdc = input.getStyledDocument();
					if (content.getMessgaeType().equals("message")) {
						sdc.insertString(sdc.getLength(), content.getMessgae(), null);
						sdc.insertString(sdc.getLength(), "\n", null);
					}
					if (content.getMessgaeType().equals("file")) {
						sdc.insertString(sdc.getLength(), "系统提示:文件收发成功，收件者将会在默认download目录下找到相应文件", null);
						sdc.insertString(sdc.getLength(), "\n", null);
						File file = new File(".//download//" + content.getFilename());
						if (!file.exists()) {
							file.createNewFile();
						}
						FileOutputStream fos = new FileOutputStream(file);
						byte[] by = content.getBy();
						fos.write(by);
					}
					if (content.getMessgaeType().equals("icon")) {
						sdc.insertString(sdc.getLength(), content.getMessgae(), null);
						sdc.insertString(sdc.getLength(), "\n", null);
						byte[] by = content.getBy();
						ImageIcon icon = new ImageIcon(by);
						input.setCaretPosition(sdc.getLength());
						input.insertIcon(icon);
						sdc.insertString(sdc.getLength(), "\n", null);

					}
					if(content.getMessgaeType().equals("guess")){
						Graphics2D g2d = (Graphics2D) you.can.getGraphics();
						g2d.setColor(content.getColor());
						BasicStroke bs;
						bs = new BasicStroke(content.getSize(), BasicStroke.CAP_ROUND, BasicStroke.CAP_BUTT);// 设置线条宽度
						g2d.setStroke(bs);
						g2d.drawLine(content.getStartX(), content.getStartY(),
								content.getEndX(), content.getEndY());
					}
				}
				ois = new ObjectInputStream(s.getInputStream());
			}
		} catch (Exception e) {
			try {
				s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

package qqchat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import Game2048.My2048;
import Util.photo;
import database.sqlbase;

public class chat extends JFrame implements Runnable {
	public JTextArea input = new JTextArea();// 文件输入框
	public JTextPane chat = new JTextPane();// 文本显示框
	public Socket s;
//	public PrintStream ps;
	public ObjectOutputStream oos;
	public long friendcount;
	public long count;
	public YouPrintIguess2 you;
	public JButton guess = new JButton("你画我猜");
	public JCheckBox all=new JCheckBox("all");
	public Point origin = new Point();
	public String fontstyle = "华文行楷";
	public int fontsize = 27;
	public photo ph = new photo();
	public sqlbase base=new sqlbase();
	public JPanel em;
	
//	public void run() {
//		try {
//			s = new Socket(InetAddress.getLocalHost().getHostAddress(), 8080);
//			// 客户端启动ClientThread线程不断读取来自服务器的数据
//			new Thread(new ClientThread(s,chat)).start(); 
//			// 获取该Socket对应的输出流
//			ps = new PrintStream(s.getOutputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
	public void run() {
		try {
			s = new Socket(InetAddress.getLocalHost().getHostAddress(), 8080);
			// 客户端启动ClientThread线程不断读取来自服务器的数据
			// 获取该Socket对应的输出流
			oos = new ObjectOutputStream(s.getOutputStream());
			you=new YouPrintIguess2(oos);
			you.setVisible(false);
			new Thread(new ClientThread(s,chat,count,friendcount,you,guess)).start(); 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	public chat(String name, long friendcount,long count) throws UnknownHostException, IOException, SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 680);
		setLocation(500, 200);
		setResizable(false);
		setUndecorated(true);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			public void mouseDragged(MouseEvent arg0) {
				Point p = getLocation();
				setLocation(p.x + arg0.getX() - origin.x, p.y + arg0.getY() - origin.y);

			}
		});
		
		this.friendcount=friendcount;
		this.count=count;
		
		Icon backicon = ph.ph(850, 680, ".\\img\\b.png");
		JLabel back = new JLabel(backicon);
		back.setBounds(0, 0, 850, 680);

		JLabel label8 = new JLabel(" x");
		label8.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		label8.setOpaque(false);
		label8.setForeground(Color.white);
		label8.setBackground(Color.red);
		label8.setBounds(820, 0, 40, 30);
		label8.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label8.setToolTipText("关闭");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				you.disable();
			}
		});

		JLabel label9 = new JLabel(" -");
		label9.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		label9.setOpaque(false);
		label9.setForeground(Color.white);
		label9.setBackground(Color.cyan);
		label9.setBounds(800, 0, 40, 30);
		label9.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label9.setToolTipText("缩小");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});
		
		//好友头像
		String filepath=base.getImage(friendcount);
		File imagefile=new File(filepath);
		if(!imagefile.exists()){
			filepath=".\\img\\DefaultImage.jpg";
		}
		ImageIcon headicon=(ImageIcon) ph.ph(60, 60, filepath);
		JLabel head=new JLabel(headicon);
		head.setBounds(50, 5, 60, 60);
		
		JLabel headName=new JLabel(name+"  "+friendcount);
		headName.setBounds(150, 35, 300, 40);
		headName.setFont(new Font(fontstyle, Font.PLAIN, 40));
		headName.setOpaque(false);
		headName.setForeground(Color.white);
		headName.addMouseListener(new personInforListener(friendcount));
		
		chat.setBackground(Color.white);
		chat.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		chat.setEditable(false);
		chat.setLocation(25, 80);
		chat.setSize(800, 475);
//		chat.setLineWrap(true);

		JScrollPane chatarea = new JScrollPane(chat);
		chatarea.setLocation(28, 80);
		chatarea.setSize(800, 475);
		chatarea.setBorder(BorderFactory.createLineBorder(new Color(255, 153, 255)));

		input.setBackground(Color.white);
		input.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		input.setLineWrap(true);
		input.setLocation(0, 580);
		input.setSize(550, 100);

		JScrollPane inputarea = new JScrollPane(input);
		inputarea.setLocation(0, 580);
		inputarea.setSize(550, 100);
		inputarea.setBorder(BorderFactory.createLineBorder(new Color(255, 153, 255)));

		all.setOpaque(false);
		all.setForeground(Color.white);
		all.setBounds(750,550,100,30);
		all.setFont(new Font(fontstyle, Font.PLAIN, 20));
		
		em=new JPanel();
		em.setLayout(new GridLayout(3, 6, 5, 5));
		em.setSize(80*6+5*5, 80*3+5*2);
		em.setBounds(80, 300,80*6+5*5, 80*3+5*2);
		em.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		em.setVisible(false);
		JLabel picture;
		for(int i=1;i<=18;i++){
			picture=new JLabel(ph.ph(80, 80, ".\\img\\"+i+".png"));
			String picpath=".\\img\\"+i+".png";
			picture.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					File file=new File(picpath);
					byte[] by=new byte[1024*1024*8];
					try {
						FileInputStream fis=new FileInputStream(file);
						fis.read(by);
						Message me=new Message();
						me.setMessgaeType("icon");
						me.setGetter(friendcount+"");
						me.setOwner(count+"");
						me.setMessgae(base.getName(count)+"说:");
						me.setBy(by);
						if(all.isSelected()){
							me.setOwner("all");
							me.setMessgae(base.getName(count)+"(全体消息)说:");
						}
						oos.writeObject(me);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			em.add(picture);
			
		}
		
		JLabel setemtion=new JLabel("表情");
		setemtion.setFont(new Font(fontstyle, Font.PLAIN, 20));
		setemtion.setBounds(50,550,100,30);
		setemtion.setForeground(Color.white);
		setemtion.addMouseListener(new sendEmtion(em));
		
		JButton send = new JButton();// send Button
		send.setBackground(new Color(255, 153, 255));
		send.setText("Send");
		send.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		send.setBounds(550, 630, 100, 50);
		send.setFocusPainted(false);
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String line = input.getText();
				Message me=new Message();
				try {
					me.setGetter(friendcount+"");
					me.setOwner(count+"");
					me.setMessgaeType("message");
					me.setMessgae(base.getName(count)+"说:"+line);
					if(all.isSelected()){
						me.setOwner("all");
						me.setMessgae(base.getName(count)+"(全体消息)说:"+line);
					}
					oos.writeObject(me);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//					try {
//						oos.print(base.getName(count)+"说:");
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					ps.println(line);
					input.setText("");
			}
		});

		JButton img = new JButton("Img");
		img.setBackground(new Color(255, 153, 255));
		img.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		img.setBounds(650, 630, 100, 50);
		img.setFocusPainted(false);
		img.addActionListener(new imgListener());

		JButton game = new JButton("2048");
		game.setBackground(new Color(255, 153, 255));
		game.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		game.setBounds(550, 580, 150, 50);
		game.setFocusPainted(false);
		game.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new My2048();
			}
		});
		
		guess.setBackground(new Color(255, 153, 255));
		guess.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		guess.setBounds(700, 580, 150, 50);
		guess.setFocusPainted(false);
		guess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				you.setVisible(true);
			}
		});

		JButton file = new JButton("File");
		file.setBackground(new Color(255, 153, 255));
		file.setFont(new Font(fontstyle, Font.PLAIN, fontsize));
		file.setBounds(750, 630, 100, 50);
		file.setFocusPainted(false);
		file.addActionListener(new fileListener());

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(em);
		panel.add(chatarea);
		panel.add(label8);
		panel.add(label9);
		panel.add(send);
		panel.add(game);
		panel.add(guess);
		panel.add(all);
		panel.add(head);
		panel.add(headName);
		panel.add(img);
		panel.add(file);
		panel.add(inputarea);
		panel.add(setemtion);
		panel.add(back);

		add(panel);
		setVisible(true);
	}

	// 图片文件读取
	class imgListener implements ActionListener {
		private JFileChooser ch = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
		
		public imgListener() {
			
		}

		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			ch.setFileFilter(filter);
			ch.setDefaultLocale(null);
			ch.showOpenDialog(frame);
			String filepath;
			if(ch.getSelectedFile().getAbsolutePath().isEmpty()){
				return;
			}
			filepath=ch.getSelectedFile().getAbsolutePath();
			if(filepath==null||filepath.equals("")){
				return;
			}
			File file=new File(filepath);
			byte[] by=new byte[1024*1024*8];
			try {
				FileInputStream fis=new FileInputStream(file);
				fis.read(by);
				Message me=new Message();
				me.setMessgaeType("icon");
				me.setGetter(friendcount+"");
				me.setOwner(count+"");
				me.setMessgae(base.getName(count)+"说:");
				me.setBy(by);
				if(all.isSelected()){
					me.setOwner("all");
					me.setMessgae(base.getName(count)+"(全体消息)说:");
				}
				oos.writeObject(me);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ch.setSelectedFile(null);
		}

	}
	//文件读取
	class fileListener implements ActionListener {
		private JFileChooser ch = new JFileChooser();

		public fileListener() {
			
		}

		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			ch.setDefaultLocale(null);
			ch.showOpenDialog(frame);
			String filepath;
			if(ch.getSelectedFile().getAbsolutePath().isEmpty()){
				return;
			}
			filepath=ch.getSelectedFile().getAbsolutePath();
			if(filepath==null||filepath.equals("")){
				return;
			}
			File file=new File(filepath);
			byte[] by=new byte[1024*1024*8];
			try {
				FileInputStream fis=new FileInputStream(file);
				fis.read(by);
				Message me=new Message();
				me.setMessgaeType("file");
				me.setGetter(friendcount+"");
				me.setOwner(count+"");
				me.setFilename(file.getName());
				me.setBy(by);
				if(all.isSelected()){
					me.setOwner("all");
				}
				oos.writeObject(me);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ch.setSelectedFile(null);
		}

	}
	class personInforListener implements MouseListener{
		public long friendcount;
		public personInforListener(long friendcount){
			this.friendcount=friendcount;
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			try {
				new PersonInfor(friendcount,false);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		public void mouseReleased(MouseEvent e) {}
	}
	class sendEmtion implements MouseListener{
		JPanel em;
		public sendEmtion(JPanel em){
			this.em=em;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(em.isVisible()){
				em.setVisible(false);
				return;
			}
			if(!em.isVisible()){
				em.setVisible(true);
				return;
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, SQLException {
//		new Thread(new chat("tkj", 477850812,625979754)).start();
	    new Thread(new chat("二狗",1,2)).start();;
	}
}

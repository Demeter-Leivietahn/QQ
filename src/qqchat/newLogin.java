package qqchat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;

import Util.WebsiteUrl;
import Util.photo;
import database.sqlbase;

public class newLogin extends JFrame {
	public Point origin = new Point();
	public JLabel closeOp = new JLabel(" x");
	public JLabel smallOp = new JLabel(" -");
	public JLabel about = new JLabel("about");
	public photo ph = new photo();

	public newLogin() {
		this.setTitle("QQ chat log in");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 当直接关闭时 java程序也关闭
		this.setSize(640, 430);
		this.setLocation(600, 300);
		this.setResizable(false);
		this.setUndecorated(true);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			public void mouseDragged(MouseEvent arg0) {
				Point p = getLocation();
				setLocation(p.x + arg0.getX() - origin.x, p.y + arg0.getY() - origin.y);

			}
		});

		sqlbase base = null;
		try {
			base = new sqlbase();
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel label1 = new JLabel("账号：");
		label1.setForeground(Color.black);
		label1.setFont(new Font("Helvetica", Font.BOLD, 19));
		label1.setBounds(170, 140 + 10, 60, 75);

		JLabel label2 = new JLabel("密码:");
		label2.setForeground(Color.black);
		label2.setFont(new Font("Helvetica", Font.BOLD, 19));
		label2.setBounds(170, 185 + 20, 60, 75);

		JLabel label3 = new JLabel("欢迎来到miniQQ");
		label3.setForeground(Color.white);
		label3.setFont(new Font("Helvetica", Font.BOLD, 38));
		label3.setBounds(180, 30, 300, 75);

		JLabel label5 = new JLabel("注册账号");
		label5.setForeground(Color.black);
		label5.setFont(new Font("Helvetica", Font.PLAIN, 19));
		label5.setBounds(500, 150, 100, 70);
		label5.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new regist();

			}
		});

		JLabel label6 = new JLabel("找回密码");
		label6.setForeground(Color.black);
		label6.setFont(new Font("Helvetica", Font.PLAIN, 19));
		label6.setBounds(500, 205, 100, 70);
		label6.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new refound();
			}
		});

		ImageIcon icon1 = new ImageIcon(".\\img\\p.png");// 背景

		JLabel back;
		back = new JLabel(icon1);
		back.setSize(640, 430);

		closeOp.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		closeOp.setForeground(Color.white);
		closeOp.setBounds(615, 0, 40, 30);
		closeOp.addMouseListener(new actionlis(this));

		smallOp.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		smallOp.setForeground(Color.white);
		smallOp.setBounds(585, 0, 40, 30);
		smallOp.addMouseListener(new actionlis(this));

		about.setFont(new Font("Helvetica", Font.PLAIN, 15));
		about.setForeground(Color.white);
		about.setBounds(545, 0, 70, 30);
		about.addMouseListener(new actionlis(this));

		TextField text2 = new TextField();// 密码框
		text2.setFont(new Font("Helvetica", Font.BOLD, 19));
		text2.setBounds(230, 195 + 30, 220, 30);
		text2.setVisible(false);

		JPasswordField pass = new JPasswordField();
		pass.setFont(new Font("Helvetica", Font.BOLD, 19));
		pass.setBounds(230, 195 + 30, 220, 30);

		ImageIcon icon2 = new ImageIcon(".\\img\\p2.png");
		JButton button1 = new JButton();
		button1.setIcon(icon2);
		button1.setSize(100, 100);
		button1.setBounds(450, 195 + 30, 30, 30);
		button1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				text2.setVisible(false);
				pass.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String a = new String(pass.getPassword());
				pass.setVisible(false);
				text2.setVisible(true);
				text2.setText(a);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});

		JComboBox box = new JComboBox();// 账号框
		box.setFont(new Font("Helvetica", Font.BOLD, 19));
		box.setBounds(230, 140 + 30, 220, 30);
		box.setEditable(true);
		
//		为登录界面的账号默认几个常登陆账号
//		
		box.addItem("625979754");
		box.addItem("532462058");
		box.addItem("652148651");
		box.setBackground(Color.white);

		JButton button2 = new JButton("登录");
		button2.setBounds(170, 315, 315, 30);
		button2.setBackground(Color.white);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("登录")) {
					String username = (String) box.getSelectedItem();
					long count = Long.valueOf(username);
					// char[] passwd=pass.getPassword();
					// String password=new String(passwd).trim();
					String password = pass.getText();
					if (username.length() == 0) {
						JOptionPane.showMessageDialog(null, "账户不能为空，请重新输入！", "警告", JOptionPane.WARNING_MESSAGE);
					} else if (password.length() == 0) {
						JOptionPane.showMessageDialog(null, "密码不能为空，请重新输入！", "警告", JOptionPane.WARNING_MESSAGE);
						pass.requestFocusInWindow();
					} else {
						try {
							if (base.login(count, password)) {
								new alreadylogin(count, base.getName(count));
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "密码错误，请重新输入！", "警告", JOptionPane.WARNING_MESSAGE);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			}
		});

		// 显示时间
		JLabel showtime = new JLabel();
		showtime.setFont(new Font("仿宋", Font.PLAIN, 25));
		showtime.setBounds(20, 10, 115, 30);
		showtime.setForeground(Color.white);

		final Timer timer = new Timer(1000, new ActionListener() {
			DateFormat format = DateFormat.getTimeInstance(DateFormat.MEDIUM);

			@Override
			public void actionPerformed(ActionEvent e) {
				showtime.setText(format.format(System.currentTimeMillis()));
			}
		});
		timer.start();

		ImageIcon icon3 = new ImageIcon(".\\img\\p3.png");
		JLabel label7 = new JLabel(icon3);
		label7.setSize(icon3.getIconWidth(), icon3.getIconHeight());
		label7.setBounds(50, 160, icon3.getIconHeight(), icon3.getIconWidth());

		Timer head = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String username = (String) box.getSelectedItem();
				long count = Long.valueOf(username);
				try {
					if (base.ensure(count)) {
						String image = base.getImage(count);
						if (!image.trim().equals("")) {
							label7.setIcon(ph.ph(130, 130, image));
						} else {
							label7.setIcon(ph.ph(130, 130, ".\\img\\p3.png"));
						}
					} else {
						label7.setIcon(new ImageIcon(".\\img\\p3.png"));
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		head.start();

		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label5);
		panel.add(label6);
		panel.add(smallOp);
		panel.add(closeOp);
		panel.add(about);
		panel.add(showtime);
		panel.add(box);
		panel.add(text2);
		panel.add(pass);
		panel.add(button1);
		panel.add(button2);
		panel.add(label7);
		panel.add(back);

		this.add(panel);
		this.setVisible(true);

	}

	class actionlis implements MouseListener {
		public JFrame frame;

		public actionlis(JFrame frame) {
			this.frame = frame;
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == closeOp) {
				frame.dispose();
			}
			if (e.getSource() == smallOp) {
				frame.setExtendedState(frame.ICONIFIED);
			}
			if (e.getSource() == about) {
				File file = new File(".\\subject\\txte.html");
				try {
					String path=file.getCanonicalPath().replaceAll("\\\\","/");
					WebsiteUrl.shortcutOpen(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == closeOp) {
				closeOp.setToolTipText("关闭");
			}
			if (e.getSource() == smallOp) {
				smallOp.setToolTipText("缩小");
			}
			if (e.getSource() == about) {
				about.setToolTipText("课设相关");
			}
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}
}

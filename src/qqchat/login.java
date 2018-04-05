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
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;

import javax.swing.Icon;
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

public class login {
	static photo mod = new photo();
	static int che = 0;
	static int che1 = 0;
	public static JLabel label44;
	public static JFrame frame = new JFrame("QQ chat log in");
	static JLabel label8 = new JLabel(" x");
	static JLabel label9 = new JLabel(" -");
	static JLabel label10 = new JLabel(" ▽");
	static Point origin = new Point();
	static int count = 0;
	static framedemo demo = new framedemo();

	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 当直接关闭时 java程序也关闭
		frame.setSize(640, 430);
		frame.setLocation(600, 300);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			public void mouseDragged(MouseEvent arg0) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + arg0.getX() - origin.x, p.y + arg0.getY() - origin.y);

			}
		});

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
				if (che == 0) {
					new regist();
					che++;
				}

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
				if (che1 == 0) {
					new refound();
					che1++;
				}

			}
		});

		ImageIcon icon3 = new ImageIcon(".\\img\\pt1.jpg");

		JLabel label7 = new JLabel(icon3);
		label7.setSize(icon3.getIconWidth(), icon3.getIconHeight());
		label7.setBounds(50, 160, icon3.getIconHeight(), icon3.getIconWidth());

		ImageIcon icon1 = new ImageIcon(".\\img\\p.png");// 背景

		label44 = new JLabel(icon1);
		label44.setSize(640, 430);

		label8.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		label8.setOpaque(false);
		label8.setForeground(Color.red);
		label8.setBackground(Color.red);
		label8.setBounds(615, 0, 40, 30);
		label8.addMouseListener(new actionlis1());

		label9.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		label9.setOpaque(false);
		label9.setForeground(Color.white);
		label9.setBackground(Color.cyan);
		label9.setBounds(585, 0, 40, 30);
		label9.addMouseListener(new actionlis2());

		label10.setFont(new Font("Helvetica", Font.PLAIN, 19));// 三角
		label10.setForeground(Color.white);
		label10.setBackground(Color.cyan);
		label10.setBounds(555, 5, 26, 28);
		label10.addMouseListener(new actionlis3());

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
						sqlbase base = null;
						try {
							base = new sqlbase();
						} catch (ClassNotFoundException | SQLException e2) {
							e2.printStackTrace();
						}
						try {
							if (base.login(count, password)) {
								new alreadylogin(count, base.getName(count));
								frame.dispose();
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
		JLabel label11 = new JLabel();
		label11.setFont(new Font("仿宋", Font.PLAIN, 25));
		label11.setBounds(20, 10, 115, 30);
		label11.setForeground(Color.white);

		final Timer timer = new Timer(1000, new ActionListener() {
			DateFormat format = DateFormat.getTimeInstance(DateFormat.MEDIUM);

			@Override
			public void actionPerformed(ActionEvent e) {
				label11.setText(format.format(System.currentTimeMillis()));
			}
		});
		timer.start();

		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label5);
		panel.add(label6);
		panel.add(label10);
		panel.add(label11);
		panel.add(box);
		panel.add(text2);
		panel.add(pass);
		panel.add(button1);
		panel.add(button2);
		panel.add(label8);
		panel.add(label9);
		panel.add(label7);
		panel.add(label44);

		frame.add(panel);
		frame.setVisible(true);
	}

	public static class actionlis1 implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			frame.dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			label8.setToolTipText("关闭");
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}

	public static class actionlis2 implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			frame.setExtendedState(frame.ICONIFIED);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			label9.setToolTipText("缩小");
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	public static class actionlis3 implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			demo.setLocation(frame.getX(), frame.getY());
			demo.setVisible(true);
			frame.setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			label10.setToolTipText("背景设置");

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

	public static class framedemo extends JFrame {
		public framedemo() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 当直接关闭时 java程序也关闭
			setSize(640, 430);
			setLocation(600, 300);
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

			JPanel panel1 = new JPanel();
			panel1.setLayout(null);

			ImageIcon icon1 = new ImageIcon(".\\img\\p.png");//背景
			JLabel label4 = new JLabel(icon1);
			label4.setSize(640, 430);

			JLabel label1 = new JLabel(" x");
			label1.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
			label1.setOpaque(false);
			label1.setForeground(Color.white);
			label1.setBackground(Color.red);
			label1.setBounds(615, 0, 40, 30);
			label1.addMouseListener(new MouseListener() {

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
					label1.setToolTipText("关闭");

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}
			});

			JLabel label2 = new JLabel(" -");
			label2.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
			label2.setOpaque(false);
			label2.setForeground(Color.white);
			label2.setBackground(Color.cyan);
			label2.setBounds(585, 0, 40, 30);
			label2.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					label2.setToolTipText("缩小");
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					setExtendedState(ICONIFIED);
				}
			});

			JLabel label3 = new JLabel("▽");
			
			label3.setFont(new Font("Helvetica", Font.PLAIN, 19));// 三角
			label3.setOpaque(false);
			label3.setForeground(Color.white);
			label3.setBackground(Color.cyan);
			label3.setBounds(555, 5, 26, 28);
			label3.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					label3.setToolTipText("返回");
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					frame.setLocation(getX(), getY());
					setVisible(false);
					frame.setVisible(true);
				}
			});

			ImageIcon icon3 = new ImageIcon(".\\img\\p.png");
			ImageIcon icon4 = new ImageIcon(".\\img\\p4.png");
			ImageIcon icon5 = new ImageIcon(".\\img\\p5.png");
			ImageIcon icon6 = new ImageIcon(".\\img\\p6.png");
			ImageIcon icon7 = new ImageIcon(".\\img\\p7.png");

			ImageIcon icon33 = (ImageIcon) mod.ph(450, 300, ".\\img\\p.png");
			ImageIcon icon44 = (ImageIcon) mod.ph(450, 300, ".\\img\\p4.png");
			ImageIcon icon55 = (ImageIcon) mod.ph(450, 300, ".\\img\\p5.png");
			ImageIcon icon66 = (ImageIcon) mod.ph(450, 300, ".\\img\\p6.png");
			ImageIcon icon77 = (ImageIcon) mod.ph(450, 300, ".\\img\\p7.png");

			JLabel label5 = new JLabel(icon33);
			label5.setBounds(20, 5, 600, 400);

			Icon ic[][] = new Icon[5][2];
			ic[0][0] = icon33;
			ic[1][0] = icon44;
			ic[2][0] = icon55;
			ic[3][0] = icon66;
			ic[4][0] = icon77;

			ic[0][1] = icon3;
			ic[1][1] = icon4;
			ic[2][1] = icon5;
			ic[3][1] = icon6;
			ic[4][1] = icon7;

			JButton button1 = new JButton(">");
			button1.setFont(new Font("Helvetica", Font.PLAIN, 35));
			button1.setBackground(Color.white);
			button1.setBounds(500, 380, 60, 30);
			button1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					count++;
					while (count < 0 || count >= ic.length) {
						if (count < 0) {
							count += ic.length;
						} else {
							count -= ic.length;
						}
					}
					label5.setIcon(ic[count][0]);
				}
			});

			JButton button2 = new JButton("<");
			button2.setFont(new Font("Helvetica", Font.PLAIN, 35));
			button2.setBackground(Color.white);
			button2.setBounds(100, 380, 60, 30);
			button2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					count--;
					while (count < 0 || count >= ic.length) {
						if (count < 0) {
							count += ic.length;
						} else {
							count -= ic.length;
						}
					}
					label5.setIcon(ic[count][0]);
				}
			});

			JButton button3 = new JButton("确认");
			button3.setFont(new Font("Helvetica", Font.PLAIN, 23));
			button3.setBackground(Color.white);
			button3.setBounds(285, 380, 100, 30);
			button3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					label4.setIcon(ic[count][1]);
					label44.setIcon(ic[count][1]);
				}
			});

			
			panel1.add(label1);
			panel1.add(label2);
			panel1.add(label3);
			panel1.add(label5);
			panel1.add(button1);
			panel1.add(button2);
			panel1.add(button3);
			panel1.add(label4);

			add(panel1);
			setVisible(false);

		}
	}
}

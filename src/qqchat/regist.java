package qqchat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.sqlbase;

public class regist extends JFrame {
	login lg = new login();
	public JLabel label8 = new JLabel(" x");
	public JLabel label9 = new JLabel(" -");
	public Point origin = new Point();

	public regist() {
		sqlbase base = null;
		try {
			base = new sqlbase();
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 680);
		setLocation(700, 150);
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

		ImageIcon icon1 = new ImageIcon(".\\img\\photo.png");

		JLabel label1 = new JLabel(icon1);
		label1.setSize(430, 680);

		JLabel label6 = new JLabel("Regist");
		label6.setFont(new Font("Courier", Font.BOLD, 50));
		label6.setForeground(new Color(5, 190, 190));
		label6.setBounds(165, 60, 200, 50);

		label8.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		label8.setOpaque(false);
		label8.setForeground(Color.white);
		label8.setBackground(Color.red);
		label8.setBounds(405, 0, 40, 30);
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
				lg.che = 0;
				dispose();
			}
		});

		label9.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		label9.setOpaque(false);
		label9.setForeground(Color.white);
		label9.setBackground(Color.cyan);
		label9.setBounds(380, 0, 40, 30);
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

		JLabel label2 = new JLabel("name:");// 名字
		label2.setForeground(Color.black);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 25));
		label2.setBounds(75, 150, 100, 70);

		JTextField text1 = new JTextField();
		text1.setFont(new Font("Helvetica", Font.PLAIN, 21));
		text1.setBounds(150, 170, 200, 30);

		JLabel label3 = new JLabel("password:");// 密码
		label3.setForeground(Color.black);
		label3.setFont(new Font("Helvetica", Font.PLAIN, 25));
		label3.setBounds(30, 150 + 70, 200, 70);

		JPasswordField pass1 = new JPasswordField();
		pass1.setFont(new Font("Helvetica", Font.PLAIN, 21));
		pass1.setBounds(150, 170 + 70, 200, 30);

		JLabel label4 = new JLabel("confirm:");// 确认密码
		label4.setForeground(Color.black);
		label4.setFont(new Font("Helvetica", Font.PLAIN, 25));
		label4.setBounds(55, 150 + 70 + 70, 200, 70);

		JPasswordField pass2 = new JPasswordField();
		pass2.setFont(new Font("Helvetica", Font.PLAIN, 21));
		pass2.setBounds(150, 170 + 70 + 70, 200, 30);

		JLabel label5 = new JLabel("phoneNum:");// 电话号码
		label5.setForeground(Color.black);
		label5.setFont(new Font("Helvetica", Font.PLAIN, 25));
		label5.setBounds(15, 150 + 70 + 70 + 70, 150, 70);

		JTextField text2 = new JTextField();
		text2.setFont(new Font("Helvetica", Font.PLAIN, 21));
		text2.setBounds(150, 170 + 70 * 3, 200, 30);

		JLabel label10 = new JLabel("YOUR NEW A0COUNT IS:");
		label10.setFont(new Font("Helvetica", Font.PLAIN, 27));
		label10.setForeground(new Color(149, 56, 170));
		label10.setBounds(20, 200, 400, 75);
		label10.setVisible(false);

		JLabel label11 = new JLabel("error");
		label11.setFont(new Font("Helvetica", Font.PLAIN, 27));
		label11.setForeground(new Color(149, 56, 170));
		label11.setBounds(60, 300, 400, 75);
		label11.setVisible(false);

		JButton button1 = new JButton("OK");
		button1.setBackground(Color.white);
		button1.setFont(new Font("Helvetica", Font.PLAIN, 21));
		button1.setBounds(200, 480, 80, 30);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password1 = pass1.getText();
				String password2 = pass2.getText();
				String name = text1.getText();
				String phone = text2.getText();
				long phoneNum = 0;
				phoneNum = Long.valueOf(phone);
				long acount = 000000000;
				int i = 1;
				while (i == 1) {
					acount = (long) (Math.random() * 888888888) + 100000000;
					try {
						if (base.ensure(acount)) {
							continue;
						} else {
							break;
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (phoneNum > 0) {
					if (!name.isEmpty()) {
						if (password1.equals(password2) && !password1.isEmpty() && !password2.isEmpty()) {
							if (!phone.isEmpty()) {
								try {
									base.regist(acount, name, phoneNum, password1);
									label10.setVisible(true);
									label11.setVisible(true);
									label11.setText(acount + "");
									label2.setVisible(false);
									label3.setVisible(false);
									label4.setVisible(false);
									label5.setVisible(false);
									text1.setVisible(false);
									text2.setVisible(false);
									pass1.setVisible(false);
									pass2.setVisible(false);
									label6.setVisible(false);
									button1.setVisible(false);
									File file=new File(".//information//"+acount);
									if(!file.exists()){
										file.createNewFile();
									}
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null, "your phoneNum is empty,please input again!", "警告",
										JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"the password is empty or confirm wrong password,please input again!", "警告",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "name can't be empty,please input again!", "warning",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "phoneNum can't be nagetive,please input again!", "warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		panel1.add(label10);
		panel1.add(label11);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(label4);
		panel1.add(label5);
		panel1.add(text1);
		panel1.add(text2);
		panel1.add(pass1);
		panel1.add(pass2);
		panel1.add(label6);
		panel1.add(button1);
		panel1.add(label8);
		panel1.add(label9);
		panel1.add(label1);

		add(panel1);
		setVisible(true);

	}

//	public static void main(String[] args) {
//		new regist();
//	}
}

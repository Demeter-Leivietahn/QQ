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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.tree.DefaultTreeModel;

import Util.ReadFriend;
import Util.photo;
import database.sqlbase;

public class FriendFound extends JFrame {
	static Point origin = new Point();
	static photo ph = new photo();
	static Timer timer;
	public JPanel panel = new JPanel();

	public FriendFound(long count, JTree tr, int mod) {
		setLocation(700, 300);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JLabel closelabel = new JLabel(" x");
		closelabel.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		closelabel.setOpaque(false);
		closelabel.setForeground(Color.white);
		closelabel.setBounds(370, 0, 40, 30);
		closelabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				closelabel.setToolTipText("关闭");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		JLabel exlabel = new JLabel(" -");
		exlabel.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		exlabel.setOpaque(false);
		exlabel.setForeground(Color.white);
		exlabel.setBackground(Color.cyan);
		exlabel.setBounds(340, 0, 40, 30);
		exlabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				exlabel.setToolTipText("缩小");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});
		Font font = new Font("Helvetica", Font.PLAIN, 27);

		JLabel back = new JLabel(ph.ph(600, 300, ".\\img\\bbb.png"));
		back.setBounds(0, 0, 400, 300);

		// 将要输入的内容
		JTextField content = new JTextField();
		content.setFont(font);
		content.setBounds(50, 150, 300, 30);
		if (mod == 1) {
			// 下拉选框
			JComboBox<String> search = new JComboBox<String>();
			search.setBackground(Color.white);
			search.setFont(font);
			search.setBounds(50, 100, 300, 30);
			search.addItem("Search for qq Acount:");

			JButton ensure = new JButton("Search");
			ensure.setBounds(250, 250, 130, 30);
			ensure.setFont(font);
			ensure.setFocusPainted(false);
			ensure.setBackground(Color.white);
			ensure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String contentText = content.getText().trim();
					if (!contentText.matches("^[0-9]*[1-9][0-9]*$")) {
						JOptionPane.showMessageDialog(null, "Your Input is not the right form,please input again!",
								"warning", JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							sqlbase base = new sqlbase();
							long friendcount = Long.valueOf(contentText);
							if (base.ensure(friendcount) && friendcount != count) {
								File file = new File(".//information//" + count);
								if (!file.exists()) {
									file.createNewFile();
								}
								FileOutputStream out = new FileOutputStream(file, true);
								String[] str = ReadFriend.rf(".//information//" + count);
								boolean whether = true;
								for (int i = 1; i < str.length; i++) {
									if (str[i].trim().equals(contentText)) {
										whether = false;
									}
								}
								if (whether) {
									out.write((" " + friendcount).getBytes());
									DefaultTreeModel defaultTreeModel = alreadylogin.createModel(count);
									tr.setModel(defaultTreeModel);
									JOptionPane.showMessageDialog(null, "添加好友成功", "系统信息", JOptionPane.WARNING_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, "你和他已是好友", "warning",
											JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "当前账号不存在或你不能添加自己", "warning",
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			panel.add(search);
			panel.add(ensure);
		}else{
			// 下拉选框
			JComboBox<String> search = new JComboBox<String>();
			search.setBackground(Color.white);
			search.setFont(font);
			search.setBounds(50, 100, 300, 30);
			search.addItem("Delete for qq Acount:");

			JButton ensure = new JButton("Dete");
			ensure.setBounds(250, 250, 130, 30);
			ensure.setFont(font);
			ensure.setFocusPainted(false);
			ensure.setBackground(Color.white);
			ensure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String contentText = content.getText().trim();
					if (!contentText.matches("^[0-9]*[1-9][0-9]*$")) {
						JOptionPane.showMessageDialog(null, "Your Input is not the right form,please input again!",
								"warning", JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							sqlbase base = new sqlbase();
							long friendcount = Long.valueOf(contentText);
							if (base.ensure(friendcount) && friendcount != count) {
								File file = new File(".//information//" + count);
								if (!file.exists()) {
									file.createNewFile();
								}
								FileOutputStream out = new FileOutputStream(file, true);
								String[] str = ReadFriend.rf(".//information//" + count);
								boolean whether = true;
								//判断当前账号是否是你的好友
								for (int i = 1; i < str.length; i++) {
									if (str[i].trim().equals(contentText)) {
										whether = false;
										str[i]="";
										break;
									}
								}
								if (!whether) {
									String s="";
									for(int i=1;i<str.length;i++){
										s+=" "+str[i];
									}
									FileOutputStream out2 = new FileOutputStream(file, false);
									out2.write(s.getBytes());
									DefaultTreeModel defaultTreeModel = alreadylogin.createModel(count);
									tr.setModel(defaultTreeModel);
									JOptionPane.showMessageDialog(null, "删除好友成功", "系统信息", JOptionPane.WARNING_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, "你和他不是好友", "warning", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "当前账号不存在或你不能删除自己", "warning",
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			panel.add(search);
			panel.add(ensure);
		}

		panel.setLayout(null);
		panel.add(exlabel);
		panel.add(closelabel);
		panel.add(content);
		panel.add(back);

		add(panel);
		setVisible(true);
	}

	// public static void main(String[] args) {
	// JTree tr=new JTree();
	// new FriendFound(625979754,tr);
	// }
}

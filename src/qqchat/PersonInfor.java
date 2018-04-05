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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Util.photo;
import database.sqlbase;

public class PersonInfor extends JFrame {
	public Point origin = new Point();
	public photo ph = new photo();

	public PersonInfor(long count, boolean modify) throws ClassNotFoundException, SQLException {
		sqlbase base = new sqlbase();
		setLocation(500, 200);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		JPanel panel = new JPanel();
		panel.setLayout(null);
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
		closelabel.setBounds(765, 0, 40, 30);
		closelabel.addMouseListener(new MouseListener() {

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
				closelabel.setToolTipText("关闭");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		JLabel exlabel = new JLabel(" -");
		exlabel.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		exlabel.setOpaque(false);
		exlabel.setForeground(Color.white);
		exlabel.setBackground(Color.cyan);
		exlabel.setBounds(735, 0, 40, 30);
		exlabel.addMouseListener(new MouseListener() {

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
				exlabel.setToolTipText("缩小");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});

		Font font = new Font("Helvetica", Font.PLAIN, 27);

		JLabel back = new JLabel(ph.ph(1000, 600, ".\\img\\background.png"));
		back.setBounds(0, 0, 800, 600);

		JLabel realname = new JLabel("Real Name:");
		realname.setBounds(50, 50, 150, 30);
		realname.setFont(font);
		realname.setForeground(Color.cyan);

		JTextField realnamefield = new JTextField();
		realnamefield.setBounds(200, 50, 150, 30);
		realnamefield.setFont(font);
		realnamefield.setBackground(Color.white);

		JLabel sex = new JLabel("Sex:");
		sex.setBounds(450, 50, 60, 30);
		sex.setFont(font);
		sex.setForeground(Color.cyan);

		JComboBox<String> sexcb = new JComboBox<String>();
		sexcb.setBounds(510, 50, 100, 30);
		sexcb.setFont(font);
		sexcb.addItem("Boy");
		sexcb.addItem("Girl");
		sexcb.addItem("Gay?");
		sexcb.setBackground(Color.white);

		JLabel birth = new JLabel("BirthDay:");
		birth.setBounds(50, 150, 150, 30);
		birth.setFont(font);
		birth.setForeground(Color.cyan);

		JComboBox<Integer> year = new JComboBox<Integer>();
		year.setBounds(200, 150, 100, 30);
		year.setFont(font);
		year.setBackground(Color.white);
		for (int i = 1; i <= 3000; i++) {
			year.addItem(i);
		}
		year.setBackground(Color.white);

		JLabel nian = new JLabel("年");
		nian.setBounds(310, 150, 50, 30);
		nian.setFont(font);
		nian.setForeground(Color.cyan);

		JComboBox<Integer> month = new JComboBox<Integer>();
		month.setBounds(350, 150, 50, 30);
		month.setFont(font);
		month.setBackground(Color.white);
		for (int i = 1; i <= 12; i++) {
			month.addItem(i);
		}
		month.setBackground(Color.white);

		JLabel yue = new JLabel("月");
		yue.setBounds(410, 150, 50, 30);
		yue.setFont(font);
		yue.setForeground(Color.cyan);

		JComboBox<Integer> day = new JComboBox<Integer>();
		day.setBounds(450, 150, 70, 30);
		day.setFont(font);
		day.setBackground(Color.white);
		for (int i = 1; i <= 31; i++) {
			day.addItem(i);
		}
		day.setBackground(Color.white);

		JLabel ri = new JLabel("日");
		ri.setBounds(530, 150, 50, 30);
		ri.setFont(font);
		ri.setForeground(Color.cyan);

		JLabel address = new JLabel("Address:");
		address.setBounds(50, 250, 150, 30);
		address.setFont(font);
		address.setForeground(Color.cyan);

		JTextField addressfield = new JTextField();
		addressfield.setBounds(200, 250, 500, 30);
		addressfield.setFont(font);
		addressfield.setBackground(Color.white);

		JLabel email = new JLabel("Email:");
		email.setBounds(50, 350, 150, 30);
		email.setFont(font);
		email.setForeground(Color.cyan);

		JTextField emailfield = new JTextField();
		emailfield.setBounds(200, 350, 500, 30);
		emailfield.setFont(font);
		emailfield.setBackground(Color.white);

		JLabel school = new JLabel("School:");
		school.setBounds(50, 450, 150, 30);
		school.setFont(font);
		school.setForeground(Color.cyan);

		JTextField schoolfield = new JTextField();
		schoolfield.setBounds(200, 450, 200, 30);
		schoolfield.setFont(font);
		schoolfield.setBackground(Color.white);
		
		JLabel countLabel=new JLabel("账号:");
		countLabel.setFont(font);
		countLabel.setBounds(50, 550, 100, 30);
		countLabel.setForeground(Color.cyan);
		
		JTextField countFiled=new JTextField(count+"");
		countFiled.setBounds(200, 550, 200, 30);
		countFiled.setFont(font);
		countFiled.setBackground(Color.white);
		countFiled.disable();

		String str[] = new String[8];
		str = base.select(count);
		realnamefield.setText(str[0]);
		sexcb.setSelectedItem(str[1]);
		System.out.println(str[2]);
		if (!str[2].isEmpty() && str[2] != null && !str[2].trim().equals("null") && str[2].trim() != "") {
			year.setSelectedItem(Integer.valueOf(str[2].trim()));
		}
		if (!str[3].isEmpty() && str[3] != null && !str[3].trim().equals("null") && str[3].trim() != "") {
			month.setSelectedItem(Integer.valueOf(str[3].trim()));
		}
		if (!str[4].isEmpty() && str[4] != null && !str[4].trim().equals("null") && str[4].trim() != "") {
			day.setSelectedItem(Integer.valueOf(str[4]));
		}

		addressfield.setText(str[5]);
		emailfield.setText(str[6]);
		schoolfield.setText(str[7]);

		realnamefield.setEditable(false);
		sexcb.disable();
		year.disable();
		month.disable();
		day.disable();
		addressfield.setEditable(false);
		emailfield.setEditable(false);
		schoolfield.setEditable(false);
		if (modify) {
			JButton edit = new JButton("Edit");
			edit.setBackground(Color.black);
			edit.setFont(font);
			edit.setForeground(Color.cyan);
			edit.setBounds(530, 500, 100, 50);
			edit.setFocusPainted(false);
			if (modify) {
				edit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						realnamefield.setEditable(true);
						addressfield.setEditable(true);
						emailfield.setEditable(true);
						schoolfield.setEditable(true);
						sexcb.enable();
						year.enable();
						month.enable();
						day.enable();
					}
				});
			}

			JButton finish = new JButton("Finish");
			finish.setBackground(Color.black);
			finish.setFont(font);
			finish.setForeground(Color.cyan);
			finish.setBounds(650, 500, 120, 50);
			finish.setFocusPainted(false);
			if (modify) {
				finish.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						realnamefield.setEditable(false);
						addressfield.setEditable(false);
						emailfield.setEditable(false);
						schoolfield.setEditable(false);
						sexcb.disable();
						year.disable();
						month.disable();
						day.disable();
						try {
							sqlbase base = new sqlbase();
							base.modifypersoninfor(realnamefield.getText().trim(), sexcb.getSelectedItem().toString(),
									year.getSelectedItem().toString(), month.getSelectedItem().toString(),
									day.getSelectedItem().toString(), addressfield.getText().trim(),
									emailfield.getText().trim(), schoolfield.getText().trim(), (long) count);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), "修改成功", "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});
			}
			panel.add(edit);
			panel.add(finish);
		}
		panel.add(realname);
		panel.add(realnamefield);
		panel.add(sex);
		panel.add(sexcb);
		panel.add(birth);
		panel.add(year);
		panel.add(nian);
		panel.add(month);
		panel.add(yue);
		panel.add(day);
		panel.add(ri);
		panel.add(address);
		panel.add(addressfield);
		panel.add(countLabel);
		panel.add(email);
		panel.add(emailfield);
		panel.add(countFiled);
		panel.add(school);
		panel.add(schoolfield);
		panel.add(exlabel);
		panel.add(closelabel);
		panel.add(back);

		add(panel);
		setVisible(true);
	}

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		new PersonInfor(625979754, true);
//	}
}

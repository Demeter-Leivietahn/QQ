package qqchat;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Util.photo;

public class YouPrintIguess2 extends JFrame implements ActionListener {
	public photo ph = new photo();
	public Color color = Color.black;
	public int size = 5;
	public JComboBox fontsize;
	public Point origin = new Point();
	public ObjectOutputStream oos;
	public myCanvas can = new myCanvas();
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	String[] colorful = { "black", "red", "yellow", "green", "blue", "gray", "cyan", "pink", "orange", "eraser" };
	Color[] col = { Color.black, Color.red, Color.yellow, Color.green, Color.blue, Color.gray, Color.cyan, Color.pink,
			Color.orange, Color.white };

	public YouPrintIguess2(ObjectOutputStream oos) {
		this.oos = oos;
		this.setDefaultCloseOperation(2);
		this.setResizable(false);
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

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

		JLabel label8 = new JLabel(" x");
		label8.setFont(new Font("Helvetica", Font.PLAIN, 27));// x
		label8.setOpaque(false);
		label8.setForeground(Color.white);
		label8.setBackground(Color.red);
		label8.setBounds(760, 0, 40, 30);
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
				setVisible(false);
			}
		});

		JLabel label9 = new JLabel(" -");
		label9.setFont(new Font("Helvetica", Font.PLAIN, 27));// -
		label9.setOpaque(false);
		label9.setForeground(Color.white);
		label9.setBackground(Color.cyan);
		label9.setBounds(730, 0, 40, 30);
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

		JPanel panel = new JPanel();
		panel.setLayout(null);

		int y = 0;
		JButton color = new JButton();
		for (int i = 0; i < colorful.length; i++) {
			color = new JButton(colorful[i]);
			color.setFocusPainted(false);
			color.setBackground(col[i]);
			color.addActionListener(this);
			if (colorful[i] != "eraser") {
				color.setForeground(Color.white);
			}
			color.setBounds(670, y += 50, 100, 30);
			panel.add(color);
		}

		String[] str = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "20", "25",
				"30", "40", "500" };
		fontsize = new JComboBox(str);
		fontsize.setBounds(670, y += 50, 100, 30);
		fontsize.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fontsize.setSelectedItem(size);
		fontsize.addActionListener(this);
		fontsize.setSelectedItem("5");

		panel.add(can);
		panel.add(fontsize);
		panel.add(label8);
		panel.add(label9);
		ImageIcon backimage = (ImageIcon) ph.ph(800, 700, ".//img//p4.png");
		JLabel back = new JLabel(backimage);
		back.setBounds(0, 0, backimage.getIconWidth(), backimage.getIconHeight());
		panel.add(back);

		this.add(panel);
		this.setVisible(true);
	}

	// 画布监听
	class myCanvas extends Canvas implements MouseMotionListener, MouseListener {
		boolean move;

		public myCanvas() {
			Toolkit kit = Toolkit.getDefaultToolkit();
			Image img = kit.getImage(".\\img\\cur.png");
			Cursor dynamiteCuror = kit.createCustomCursor(img, new Point(10, 10), "dynamite stick");
			setCursor(dynamiteCuror);
			addMouseMotionListener(this);
			addMouseListener(this);
			setLocation(20, 50);
			setBackground(Color.WHITE);
			setSize(630, 550);
			setBackground(new Color(205, 255, 255));
		}

		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);
			BasicStroke bs;
			bs = new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.CAP_BUTT);// 设置线条宽度
			g2d.setStroke(bs);
			g2d.drawLine(startX, startY, endX, endY);
			Message me = new Message();
			me.setOwner("all");
			me.setMessgaeType("guess");
			me.setStartX(startX);
			me.setStartY(startY);
			me.setEndX(endX);
			me.setEndY(endY);
			me.setColor(color);
			me.setSize(size);
			try {
				oos.writeObject(me);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			endX = startX;
			endY = startY;
			repaint();
			startX = e.getX();
			startY = e.getY();
		}

		public void update(Graphics g) {
			paint(g);
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	// 颜色按钮监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("red")) {
			color = Color.red;
		}
		if (e.getActionCommand().equals("black")) {
			color = Color.black;
		}
		if (e.getActionCommand().equals("blue")) {
			color = Color.blue;
		}
		if (e.getActionCommand().equals("yellow")) {
			color = Color.yellow;
		}
		if (e.getActionCommand().equals("green")) {
			color = Color.green;
		}
		if (e.getActionCommand().equals("eraser")) {
			color = Color.white;
		}
		if (e.getActionCommand().equals("gray")) {
			color = Color.gray;
		}
		if (e.getActionCommand().equals("red")) {
			color = Color.red;
		}
		if (e.getActionCommand().equals("cyan")) {
			color = Color.cyan;
		}
		if (e.getActionCommand().equals("orange")) {
			color = Color.orange;
		}
		if (e.getActionCommand().equals("pink")) {
			color = Color.pink;
		}
		size = Integer.parseInt(this.fontsize.getSelectedItem().toString());
	}

//	public static void main(String[] args) {
////		new YouPrintIguess2();
//	}
}

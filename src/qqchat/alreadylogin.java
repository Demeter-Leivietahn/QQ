package qqchat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Util.ReadFriend;
import Util.User;
import Util.photo;
import database.sqlbase;

public class alreadylogin extends JFrame implements ActionListener {
	public photo ph = new photo();
	public JLabel head = null;
	private Rectangle rect;
	public JTree tr = new JTree();

	// 窗体离屏幕左边的距离
	private int left;
	// 窗体离屏幕顶部的距离
	private int top;
	// 窗体的宽
	private int width;
	// 窗体的高
	private int height;
	// 鼠标在窗体的位置
	private Point point;

	private Timer timer = new Timer(100, this);

	public void actionPerformed(ActionEvent e) {
		if (isVisible()) {
			left = getLocationOnScreen().x;
			top = getLocationOnScreen().y;
			width = getWidth();
			height = getHeight();
			// 获取窗体的轮廓
			rect = new Rectangle(0, 0, width, height);
			// 获取鼠标在窗体的位置
			point = getMousePosition();
			if ((top < 0) && isPtInRect(rect, point)) {
				// 当鼠标在当前窗体内，并且窗体的Top属性小于0
				// 设置窗体的Top属性为0,就是将窗口上边沿紧靠顶部
				setLocation(left, 0);
			} else if (top > -5 && top < 5 && !(isPtInRect(rect, point))) {
				// 当窗体的上边框与屏幕的顶端的距离小于5时 ，
				// 并且鼠标不再窗体上 将QQ窗体隐藏到屏幕的顶端
				setLocation(left, 5 - height);
			}
		}
	}

	/**
	 * 判断一个点是否在一个矩形内
	 * 
	 * @param rect：Rectangle对象
	 * @param point：Point对象
	 * @return：如果在矩形内返回true，不在或者对象为null则返回false
	 */
	public boolean isPtInRect(Rectangle rect, Point point) {
		if (rect != null && point != null) {
			int x0 = rect.x;
			int y0 = rect.y;
			int x1 = rect.width;
			int y1 = rect.height;
			int x = point.x;
			int y = point.y;

			return x >= x0 && x < x1 && y >= y0 && y < y1;
		}
		return false;
	}

	public long count = 000000000;
	public Point origin = new Point();

	public alreadylogin(long count, String name) throws SQLException, ClassNotFoundException, IOException {
		sqlbase base = new sqlbase();
		timer.start();
		this.count = count;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 当直接关闭时 java程序也关闭
		setSize(370, 850);
		setLocation(800, 100);
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

		ImageIcon icon1 = new ImageIcon(".\\img\\back1.png");
		JLabel label1 = new JLabel(icon1);
		label1.setBounds(0, 0, 370, 850);

		JLabel label2 = new JLabel(" -");
		label2.setForeground(Color.white);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 27));
		label2.setBounds(320, 3, 26, 26);
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

		JLabel label3 = new JLabel(" x");
		label3.setForeground(Color.white);
		label3.setFont(new Font("Helvetica", Font.PLAIN, 27));
		label3.setBounds(340, 3, 26, 26);
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
				label3.setToolTipText("关闭");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				dispose();
			}
		});
		JLabel label5 = new JLabel(name + "");
		label5.setForeground(Color.white);
		label5.setFont(new Font("Book Antiqua", Font.PLAIN, 27));
		label5.setBounds(150, 45, 200, 60);

		JLabel label4 = new JLabel(count + "");
		label4.setForeground(Color.white);
		label4.setFont(new Font("Book Antiqua", Font.PLAIN, 27));
		label4.setBounds(150, 75, 200, 60);
		label4.addMouseListener(new countListener());

		String filepath = base.getImage(count);
		if (!(new File(filepath)).exists()) {
			filepath = ".//img//p4.png";
		}
		ImageIcon headimg = (ImageIcon) ph.ph(100, 100, filepath);
		head = new JLabel(headimg);
		head.setBounds(35, 40, 100, 100);
		head.addMouseListener(new headListener());
		
		// 分组功能的实现
		JPanel group = new JPanel();
		group.setLayout(null);
		group.setSize(300, 600);
		group.setBounds(35, 150, 300, 600);
		group.setBackground(Color.white);

		JTree tr = new JTree();
		DefaultTreeModel defaultTreeModel = createModel(count);
		tr.setModel(defaultTreeModel);
		tr.setCellRenderer(new Friend());
		tr.setBounds(10, 20, 300, 600);
		tr.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				String select = tr.getSelectionPath().toString();
				if (select.length() > 6) {
					String friendcount = select.substring(select.length() - 10, select.length() - 1);
					long FriendCount = Long.valueOf(friendcount);
					try {
						String friendname = base.getName(FriendCount);
						if (base.ensure(FriendCount)) {
							new Thread(new chat(friendname, FriendCount,count)).start();
						} else {
							System.out.println("***当前账号不存在***");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		group.add(tr);

		// 添加好友标签
		Font f = new Font("华文行楷", Font.BOLD, 30);
		JLabel add = new JLabel("+");
		add.setFont(f);
		add.setBounds(330, 800, 50, 30);
		add.setForeground(Color.white);
		add.setToolTipText("添加好友");
		add.addMouseListener(new addListener(count,tr));
		
		//删除好友标签
		JLabel dete = new JLabel("-");
		dete.setFont(f);
		dete.setBounds(300, 800, 50, 30);
		dete.setForeground(Color.white);
		dete.setToolTipText("删除好友");
		dete.addMouseListener(new deteListener(count,tr));

		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.add(label5);
		panel.add(label4);
		panel.add(label2);
		panel.add(label3);
		panel.add(add);
		panel.add(dete);
		panel.add(head);
		panel.add(group);
		panel.add(label1);

		add(panel);
		setVisible(true);

	}

	// 生成好友列表
	public static DefaultTreeModel createModel(long count) throws IOException, ClassNotFoundException, SQLException {
		sqlbase base = new sqlbase();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的好友");
		String[] str = ReadFriend.rf(".//information//" + count);
		DefaultMutableTreeNode friend = null;
			for (int i = 1; i < str.length; i++) {
				if(!str[i].trim().equals("")){
					Long friendcount = Long.valueOf(str[i].trim());
					friend = new DefaultMutableTreeNode(new User(base.getName(friendcount), friendcount + ""));
					root.add(friend);
				}
			}
		return new DefaultTreeModel(root);
	}

	// 个人资料修改监听
	class countListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			try {
				new PersonInfor(count, true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

	}

	// 更改头像监听
	class headListener implements MouseListener {
		JFileChooser ch = new JFileChooser();
		// 文件过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");

		public void mouseClicked(MouseEvent e) {
			sqlbase base = null;
			try {
				base = new sqlbase();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JFrame frame = new JFrame();
			ch.setFileFilter(filter);
			ch.showOpenDialog(frame);
			if (ch.getSelectedFile() != null) {
				String FileName = ch.getSelectedFile().getPath();
				ImageIcon icon = (ImageIcon) ph.ph(100, 100, FileName);
				try {
					String[] str=FileName.split("\\\\");
					FileName="";
					for(int i=0;i<str.length;i++){
						if(i!=str.length){
							FileName+=str[i]+"/";
						}else{
							FileName+=str[i];
						}
					}
					base.setImage(count,FileName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				head.setIcon(icon);
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

	}

	// 添加好友监听
	class addListener implements MouseListener {
		long count;
		JTree tr=new JTree();
		public addListener(long count,JTree tr) {
			this.count = count;
			this.tr=tr;
		}

		public void mouseClicked(MouseEvent arg0) {
				new FriendFound(count,tr,1);
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}
	// 删除好友监听
		class deteListener implements MouseListener {
			long count;
			JTree tr=new JTree();
			public deteListener(long count,JTree tr) {
				this.count = count;
				this.tr=tr;
			}

			public void mouseClicked(MouseEvent arg0) {
					new FriendFound(count,tr,2);
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}

		}


//	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
//		new alreadylogin(625979754, "4545");
//	}
}

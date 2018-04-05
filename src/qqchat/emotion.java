package qqchat;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Util.photo;
import database.sqlbase;

public class emotion extends JPanel {
	public photo ph=new photo();
	public ObjectOutputStream oos;
	public JLabel em;
	public long friendcount;
	public long count;
	public JCheckBox all;
	public emotion(ObjectOutputStream oos,long friendcount,long count,JCheckBox all) throws ClassNotFoundException, SQLException {
		this.friendcount=friendcount;
		this.count=count;
		this.all=all;
		this.oos=oos;
		this.setLayout(new GridLayout(3, 6, 5, 5));
		sqlbase base=new sqlbase();
		for(int i=1;i<=18;i++){
			em=new JLabel(ph.ph(80, 80, ".\\img\\"+i+".png"));
			String filepath=".\\img\\"+i+".png";
			em.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
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
			this.add(em);
			
		}
		this.setVisible(true);
	}
}

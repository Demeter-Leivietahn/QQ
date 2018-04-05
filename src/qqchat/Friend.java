package qqchat;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Friend extends DefaultTreeCellRenderer{
	public Friend(){
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus){
		setText(value.toString());  
		return this; 
	}
}

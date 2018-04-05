package Game2048;

import java.awt.*;
import javax.swing.*;
public class My2048 extends JFrame 
{ 
  public My2048()//构造函数 
  {
    setTitle("2048 点击任意位置重新游戏");//设置标题
    setSize(450, 450);//设定窗口大小
    setLocation(500, 200);//设定窗口起始位置
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(4, 4, 5, 5));//设定布局方式为GridLayout型
    new Operation(this);
    this.setVisible(true);//设为可视
  }
  
  public static void main(String args[]) //程序入口点
  {
    new My2048();
  }
  
}
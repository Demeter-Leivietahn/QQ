package Game2048;

import java.awt.*;
import javax.swing.*;
public class My2048 extends JFrame 
{ 
  public My2048()//���캯�� 
  {
    setTitle("2048 �������λ��������Ϸ");//���ñ���
    setSize(450, 450);//�趨���ڴ�С
    setLocation(500, 200);//�趨������ʼλ��
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(4, 4, 5, 5));//�趨���ַ�ʽΪGridLayout��
    new Operation(this);
    this.setVisible(true);//��Ϊ����
  }
  
  public static void main(String args[]) //������ڵ�
  {
    new My2048();
  }
  
}
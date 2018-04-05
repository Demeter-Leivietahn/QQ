package database;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class sqlbase {
	static Connection conn;

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		sqlbase base = new sqlbase();
//		System.out.println(base.getImage(625979754));
//	}
	public sqlbase() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/qq?user=root&password=&characterEncoding=utf8&useSSL=false";
//		String url = "jdbc:mysql://localhost:3306/qq?useSSL=false";
		String user = "root";
		String passwd = "754050478";
		Connection conn = DriverManager.getConnection(url, user, passwd);
		this.conn = conn;
	}

	
	
	
	
	
	
	
	
	
	
	// 根据账号修改个人信息
	public static void modifypersoninfor(String RealName, String sex, String year, String month, String day,
			String Address, String Email, String school, long count) throws SQLException {
		String sql = "UPDATE sqlqq SET RealName=\"" + RealName + "\"," + "Sex=\"" + sex + "\"," + "Year=\"1" + year
				+ "\",Month=\"1" + month + "\",Day=\"1" + day + "\",Address=\"" + Address + "\"" + ",Email=\"" + Email
				+ "\"" + ",School=\"" + school + "\" " + "WHERE count=" + count;
		System.out.println(sql);
		Statement s = conn.createStatement();
		s.executeUpdate(sql);
		s.close();
	}

	// 查询个人信息
	public static String[] select(long count) throws SQLException {
		String[] str = new String[8];
		String sql = "SELECT * FROM sqlqq WHERE count=" + count;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		while (r.next()) {
			str[0] = r.getString("RealName");
			str[1] = r.getString("Sex");
			str[2] = r.getString("Year");
			str[3] = r.getString("Month");
			str[4] = r.getString("Day");
			str[5] = r.getString("Address");
			str[6] = r.getString("Email");
			str[7] = r.getString("School");
		}
		return str;
	}

	// 确认当前账号是否存在
	public static boolean ensure(long count) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM sqlqq WHERE count=" + count;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		boolean bo = r.next();
		s.close();
		r.close();
		return bo;
	}

	// 找回账号时使用
	public static boolean ensureagain(long count, long phoneNum) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM sqlqq WHERE phoneNum=" + phoneNum + " AND count=" + count;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		boolean bo = r.next();
		s.close();
		r.close();
		return bo;
	}

	// 申请账号使用
	public static void regist(long count, String name, long phone, String password)
			throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO sqlqq VALUES(" + count + "," + "'" + name + "'" + "," + phone + "," + "'" + password
				+ "'" + ",'',"+"'',"+"'',"+"'',"+"'',"+"'',"+"'',"+"'',"+"''"+")";
		System.out.println(sql);
		Statement s = conn.createStatement();
		s.execute(sql);
		s.close();
	}

	// 找回账号使用
	public static void refound(long count, String password) throws SQLException {
		String sql = "UPDATE sqlqq SET password=" + password + " WHERE count=" + count;
		Statement s = conn.createStatement();
		s.execute(sql);
		s.close();
	}

	// 登陆使用
	public static boolean login(long count, String password) throws SQLException {
		String sql = "SELECT * FROM sqlqq WHERE count=" + count + " AND password=\"" + password + "\"";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		boolean bo = r.next();
		s.close();
		r.close();
		return bo;
	}

	// 获取昵称使用
	public static String getName(long count) throws SQLException {
		String sql = "SELECT name FROM sqlqq WHERE count=" + count;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		String username = "error";
		while (r.next()) {
			username = r.getString("name");
		}
		s.close();
		r.close();
		return username;
	}

	// 获取头像地址使用
	public static String getImage(long count) throws SQLException {
		String sql = "Select image FROM sqlqq WHERE count=" + count;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(sql);
		String image = null;
		while (r.next()) {
			image = r.getString("Image");
		}
		return image;
	}

	// 设置头像地址
	public static void setImage(long count, String filepath) throws SQLException {
		String sql = "UPDATE sqlqq SET Image=\"" + filepath + "\"" + " WHERE count=" + count;
		Statement s = conn.createStatement();
		s.executeUpdate(sql);
		s.close();
	}
}

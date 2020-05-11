package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDb{
	
	//查找方法   输入学号  返回用户的名字
	public String searchname(long account)throws ClassNotFoundException,SQLException{
		String name = null;
		
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		while(rs.next() && account!=rs.getLong("Account")) {
		}
		if(!rs.isAfterLast()) {
			name=rs.getString("UserName");
		}
		else {
			name=null;
		}
		rs.close();
		stmt.close();
		conn.close();	
		return name;
	}
	//查找密码
	public String searchpws(long account)throws ClassNotFoundException,SQLException{
		String name = null;
		
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		while(rs.next() && account!=rs.getLong("Account")) {
		}
		if(!rs.isAfterLast()) {
			name=rs.getString("pws");
		}
		else {
			name=null;
		}
		rs.close();
		stmt.close();
		conn.close();	
		return name;
	}
	
	//注册方法 输入用户学号 用户名字 用户密码 注册用户信息
	public void add(long account, String name, String Pws)throws ClassNotFoundException,SQLException{
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		stmt.executeUpdate("insert into student(Account,UserName,pws) values('"+account+"','"+name+"','"+Pws+"')");
		rs.close();
		stmt.close();
		conn.close();	
	}
	
	//修改密码  用户已经登陆状态下 输入用户学号 用户新密码
	public void updata(long aCcount,String pWs)throws ClassNotFoundException,SQLException{		//修改密码输入学号，密码
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";		//数据库地址
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();						//链接数据库
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");		//rs为一条记录，student为表
		stmt.executeUpdate("update student set pws='"+pWs+"' where Account='"+aCcount+"' ");
		rs.close();
		stmt.close();
		conn.close();	
	}
}
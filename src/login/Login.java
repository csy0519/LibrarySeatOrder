package login;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import login.LoginError.LoginErrorCode;
import windowSys.SeatOrdering;
import login.AccessDb;

class LoginError extends JDialog{		//用户名不正确时弹出的对话框类, finished class
	
	class LoginErrorCode{				//关于用户名的错误码, finished inner class
		final static int NoCard = 0;
		final static int Unregistered = 1;
		final static int Registered = 2;
		final static int InvaildUserName = 3;
		final static int WrongPsw = 4;
	}
	
	public LoginError(Login LoginWin, int ErrorCode) {
		JDialog d = new JDialog(LoginWin, "登录错误", true);
		JPanel jp = new JPanel(new BorderLayout());
		switch(ErrorCode) {
			case LoginErrorCode.NoCard : 			jp.add(new JLabel("请刷卡！", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.Unregistered : 		jp.add(new JLabel("用户未注册！", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.Registered : 		jp.add(new JLabel("该用户名已被注册！", JLabel.CENTER), BorderLayout.CENTER);	break;
			case LoginErrorCode.InvaildUserName : 	jp.add(new JLabel("无效用户名！", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.WrongPsw : 			jp.add(new JLabel("密码错误！", JLabel.CENTER), BorderLayout.CENTER);		break;
			default : 								jp.add(new JLabel("未知错误！", JLabel.CENTER), BorderLayout.CENTER);		break;
		}

		d.getContentPane().add(jp);
		d.setSize(300, 200);
		d.setResizable(false);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}
}



public class Login extends JFrame {

	public boolean StringIsDig(String s) {		//判断字符串是否均为数字, finished method
		boolean result = true;
		
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	private String FindUser(int UID, int retValue) throws ClassNotFoundException, SQLException {
		return new AccessDb().search(UID, retValue);
	}
	
	public Login() {						//登录模块的具体实现, To be continued construct method
		JFrame f = new JFrame();
		
		Container container = f.getContentPane();
		container.setLayout(new GridLayout(4, 1));		//创建4行1列网格布局
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JTextField UserName = new JTextField("请刷卡", 10);
		final JTextField Password = new JTextField("请输入密码", 10);
		final JButton LoginBtn = new JButton("登录");
		final JButton RegBtn = new JButton("注册");
		final JButton ResetBtn = new JButton("重置");
//		UserName.setEditable(false);		//禁止用户手动输入用户名
		
		LoginBtn.addActionListener(new ActionListener() {		//登录按钮监视器
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(UserName.getText().equals("请刷卡")) {
					new LoginError(Login.this, LoginErrorCode.NoCard).setVisible(true);
				}else if(StringIsDig(UserName.getText())){
					try {
						if(FindUser(Integer.parseInt(UserName.getText()), 1) == Password.getText()) {			//对比数据库
							
							/**************************************进入选座界面, To be continued*********************************/
//							new SeatOrdering(Integer.parseInt(UserName.getText()));
						System.out.println("login");
						System.out.println(Integer.parseInt(UserName.getText()));
						System.out.println(Password.getText());
							
							
							/**************************************调用结束*************************************/
							
						}else if(FindUser(Integer.parseInt(UserName.getText()), 1) == null){
							new LoginError(Login.this, LoginErrorCode.Unregistered).setVisible(true);
						}else {
							new LoginError(Login.this, LoginErrorCode.WrongPsw);
						}
					} catch (NumberFormatException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}else {
					new LoginError(Login.this, LoginErrorCode.InvaildUserName).setVisible(true);
				}
			}
		});
		
		RegBtn.addActionListener(new ActionListener() {			//注册按钮监视器
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根				
				if(UserName.getText().equals("请刷卡")) {
					new LoginError(Login.this, LoginErrorCode.NoCard).setVisible(true);
				}else if(StringIsDig(UserName.getText())){
					if(!EqualToDB(UserName.getText())) {			//对比数据库
						
						/**************************************写入数据库, To be continued*********************************/
						
						System.out.println("registration");
						System.out.println(UserName.getText());
						System.out.println(Password.getText());
						
						/**************************************调用结束*************************************/
						
					}else {
						new LoginError(Login.this, LoginErrorCode.Registered).setVisible(true);
					}
					
				}else {
					new LoginError(Login.this, LoginErrorCode.InvaildUserName).setVisible(true);
				}
				
			}
		});
		
		ResetBtn.addActionListener(new ActionListener() {		//重置按钮监视器, finished
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				UserName.setText("请刷卡");
				Password.setText("");
				Password.requestFocus();
			}
		});
		
		p1.add(new JLabel("欢迎使用图书馆座位预约系统，请刷卡"));
		p2.add(new JLabel("卡号"));
		p2.add(UserName);
		p3.add(new JLabel("密码"));
		p3.add(Password);
		p4.add(LoginBtn);
		p4.add(RegBtn);
		p4.add(ResetBtn);
		
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setTitle("图书馆座位预约系统");
		f.setSize(600, 300);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
	}
}

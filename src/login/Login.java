package login;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import login.LoginError.LoginErrorCode;
import login.TestRxTx;
import windowSys.AfterLogin;

class LoginError extends JDialog{		//用户名不正确时弹出的对话框类
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class LoginErrorCode{				//关于用户名的错误码
		final static int NoCard = 0;
		final static int Unregistered = 1;
		final static int Registered = 2;
		final static int InvaildUserName = 3;
		final static int WrongPsw = 4;
	}
	
	public LoginError(JFrame LoginWin, int ErrorCode) {
		JDialog d = new JDialog(LoginWin, "登录错误", true);
		JPanel jp = new JPanel(new BorderLayout());
		switch(ErrorCode) {
			case LoginErrorCode.NoCard : 			jp.add(new JLabel("请刷卡！", JLabel.CENTER), BorderLayout.CENTER);					break;
			case LoginErrorCode.Unregistered : 		jp.add(new JLabel("用户未注册！", JLabel.CENTER), BorderLayout.CENTER);					break;
			case LoginErrorCode.Registered : 		jp.add(new JLabel("该卡号已注册，请登录！", JLabel.CENTER), BorderLayout.CENTER);			break;
			case LoginErrorCode.InvaildUserName : 	jp.add(new JLabel("无效用户名！", JLabel.CENTER), BorderLayout.CENTER);					break;
			case LoginErrorCode.WrongPsw : 			jp.add(new JLabel("密码错误！", JLabel.CENTER), BorderLayout.CENTER);					break;
			default : 								jp.add(new JLabel("未知错误，如影响使用请联系开发人员！", JLabel.CENTER), BorderLayout.CENTER);	break;
		}

		d.setIconImage(MainProceed.icon.getImage());
		d.getContentPane().add(jp);
		d.setSize(300, 200);
		d.setResizable(false);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}
}



public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String CurrentUser = null;
	public static String CurrentCard = null;
	public static boolean ThreadBlock = true;
	public static JFrame f = new JFrame();
	public static Image image = null;
	
	static final JTextField UserName = new JTextField("请刷卡", 10);
	
	public boolean StringIsDig(String s) {		//判断字符串是否均为数字
		boolean result = true;
		
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	private String FindUserpws(int UID) throws ClassNotFoundException, SQLException {
		return new AccessDb().searchpws(UID);
	}
	
	private String FindUsername(int UID) throws ClassNotFoundException, SQLException {
		return new AccessDb().searchname(UID);
	}
	
	public Login() {						//登录模块的具体实现
		Container container = f.getContentPane();
		container.setLayout(new GridLayout(4, 1));		//创建4行1列网格布局
		f.setIconImage(MainProceed.icon.getImage());
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		final JPasswordField Password = new JPasswordField("", 10);
		final JButton LoginBtn = new JButton("登录");
		final JButton ForgetBtn = new JButton("忘记密码");
		final JButton RegBtn = new JButton("注册");
		final JButton ResetBtn = new JButton("重置");
		UserName.setEditable(false);		//禁止用户手动输入用户名
		Password.setEchoChar('*');
		
		p1.add(new JLabel("欢迎使用图书馆座位预约系统，请刷卡"));
		p2.add(new JLabel("卡号"));
		p2.add(UserName);
		p3.add(new JLabel("密码"));
		p3.add(Password);
		p4.add(LoginBtn);
		p4.add(ForgetBtn);
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
		
		Password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					LoginBtn.doClick();
				}
			}
		});
		
		LoginBtn.addActionListener(new ActionListener() {		//登录按钮监视器
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(UserName.getText().equals("请刷卡")) {
					new LoginError(Login.this, LoginErrorCode.NoCard);
				}else if(StringIsDig(UserName.getText())){
					try {
						if(FindUsername(Integer.parseInt(UserName.getText())) != null) {
							if(FindUserpws(Integer.parseInt(UserName.getText())).equals(String.valueOf(Password.getPassword()))) {			//对比数据库
								new AfterLogin(Integer.parseInt(UserName.getText()));
								f.setVisible(false);
								ResetBtn.doClick();
							}else {
								new LoginError(Login.this, LoginErrorCode.WrongPsw);
							}
						}else{
							new LoginError(Login.this, LoginErrorCode.Unregistered);
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
					new LoginError(Login.this, LoginErrorCode.InvaildUserName);
				}
			}
		});
		
		ForgetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(UserName.getText().equals("请刷卡")) {
					new LoginError(Login.this, LoginErrorCode.NoCard);
				}else if(StringIsDig(UserName.getText())){
					try {
						if(FindUsername(Integer.parseInt(UserName.getText())) != null) {
							new VerifyID(Login.this, Integer.parseInt(UserName.getText()));
						}else{
							new LoginError(Login.this, LoginErrorCode.Unregistered);
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
					new LoginError(Login.this, LoginErrorCode.InvaildUserName);
				}
			}
		});
		
		RegBtn.addActionListener(new ActionListener() {			//注册按钮监视器
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根				
				if(UserName.getText().equals("请刷卡")) {
					new LoginError(Login.this, LoginErrorCode.NoCard);
				}else if(StringIsDig(UserName.getText())){
					try {
						if(FindUsername(Integer.parseInt(UserName.getText())) == null) {
							new Registration(UserName.getText());
						}else{
							new LoginError(Login.this, LoginErrorCode.Registered);
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
					new LoginError(Login.this, LoginErrorCode.InvaildUserName);
				}
				
			}
		});
		
		ResetBtn.addActionListener(new ActionListener() {		//重置按钮监视器
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				UserName.setText("请刷卡");
				Password.setText("");
				CurrentCard = null;
				CurrentUser = null;
				TestRxTx.currentCard = null;
				Password.requestFocus();
			}
		});
		
		while(true) {
			if(TestRxTx.currentCard != (CurrentCard)) {
				CurrentCard = TestRxTx.currentCard;
				UserName.setText(CurrentCard);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
}

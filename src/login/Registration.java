package login;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class Registration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean UserReg(int UID, String name, String pws) throws ClassNotFoundException, SQLException {
		boolean res = false;
		
		new AccessDb().add(UID, name, pws);
		AccessDb db = new AccessDb();
		if((db.searchname(UID) != null) && db.searchname(UID).equals(name)) {
			if((db.searchpws(UID) != null) && (db.searchpws(UID).equals(pws))) {
				res = true;
			}
		}
		return res;
	}
	
	private void RegResultD(JFrame win, boolean RegResult) {
		JDialog rd = new JDialog(win, "注册结果", true);
		JPanel jp = new JPanel(new BorderLayout());
		if(RegResult) {
			jp.add(new JLabel("注册成功，欢迎使用！", JLabel.CENTER), BorderLayout.CENTER);
		}else {
			jp.add(new JLabel("注册失败，请重试！", JLabel.CENTER), BorderLayout.CENTER);
		}
		rd.setIconImage(MainProceed.icon.getImage());
		rd.getContentPane().add(jp);
		rd.setSize(300, 200);
		rd.setResizable(false);
		rd.setLocationRelativeTo(null);
		rd.setVisible(true);
	}
	
	Registration(String UID){
		JFrame reg = new JFrame("注册");
		Container c = reg.getContentPane();
		c.setLayout(new GridLayout(4, 1));
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JTextField Pswt = new JTextField("", 10);
		final JTextField Unam = new JTextField("", 10);
		final JButton RealRegBtn = new JButton("点击注册");
		
		Pswt.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Unam.requestFocus();
				}
			}
		});
		
		Unam.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					RealRegBtn.doClick();
				}
			}
		});
		
		RealRegBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
						RegResultD(reg, UserReg(Integer.parseInt(UID), Unam.getText(), Pswt.getText()));
						reg.dispose();				
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
			}
		});
		
		p1.add(new JLabel("卡号：" + UID));
		p2.add(new JLabel("设置密码："));
		p2.add(Pswt);
		p3.add(new JLabel("输入姓名："));
		p3.add(Unam);
		p4.add(RealRegBtn);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		
		reg.setIconImage(MainProceed.icon.getImage());
		reg.setSize(400, 200);
		reg.setResizable(false);
		reg.setLocationRelativeTo(null);
		reg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		reg.setVisible(true);
		
	}
}

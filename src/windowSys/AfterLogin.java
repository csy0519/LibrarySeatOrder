package windowSys;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;

import login.Login;
import login.MainProceed;
import login.VerifyID;

public class AfterLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AfterLogin(int UID) {
		JFrame af = new JFrame("卡号：" + UID + "在线预约");
		
		final JMenuBar menubar = new JMenuBar();
		final JMenu exit = new JMenu("退出 (E)");
		final JMenu help = new JMenu("帮助 (H)");
		final JMenuItem logout = new JMenuItem("退出登录 (O)");
		final JMenuItem exitsys = new JMenuItem("退出系统 (X)");
		final JMenuItem nothing = new JMenuItem("没有帮助 (A)");
		final JTabbedPane tab = new JTabbedPane(JTabbedPane.LEFT);
		
		exit.setMnemonic('E');
		logout.setMnemonic('O');
		exitsys.setMnemonic('X');
		help.setMnemonic('H');
		nothing.setMnemonic('A');
		
		af.setJMenuBar(menubar);
		menubar.add(exit);
		menubar.add(help);
		exit.add(logout);
		exit.add(exitsys);
		help.add(nothing);
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO 自动生成的方法存根
				int selectedIndex = tab.getSelectedIndex();
				String title = tab.getTitleAt(selectedIndex);
				if(title.equals("密码修改")){
					new VerifyID(af, UID);
				}
			}
		});
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				af.dispose();
				try {
					Login.CurrentUser = null;
					Login.f.setVisible(true);

				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				Login.CurrentUser = null;
				
			}
		});
		
		exitsys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
		});
		
		af.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
			
		});
		
		af.getContentPane().add(tab, BorderLayout.CENTER);
		new SeatOrdering(af, UID);
		try {
			new Inquire(UID);
			new Companion(UID);
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		tab.addTab("教室预定", null, SeatOrdering.Container_SeatOrdering);
		tab.addTab("个人信息", null, Inquire.Container_Inquire);
		tab.addTab("密码修改", null, new JLabel("请在弹出窗口中完成操作", JLabel.CENTER));
		tab.addTab("查询同伴", null, Companion.Container_Companion);
		
		af.setIconImage(MainProceed.icon.getImage());
		af.setSize(600, 600);
		af.setLocationRelativeTo(null);
		af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		af.setVisible(true);
	}
}

package login;

import javax.swing.*;

public class MainProceed extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		new Thread(new TestRxTx()).start();
		new Login();
	}
}

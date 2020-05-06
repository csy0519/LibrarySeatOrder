package windowSys;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.util.*;


//import login.LoginError.LoginErrorCode;


class YuyueError extends JDialog{
	
	class YuyueCode{				
		final static int Notslectedall = 0;
		final static int endsmaller = 1;
		final static int selected = 2;
		final static int success = 3;
	}
		
		
		public YuyueError(JFrame LoginWin,int ErrorCode) {
			JDialog d = new JDialog(LoginWin,"提示", true);
			JPanel jp = new JPanel(new BorderLayout());
			switch(ErrorCode) {
				case YuyueCode.Notslectedall : 			jp.add(new JLabel("请正确选择", JLabel.CENTER), BorderLayout.CENTER);					break;
				case YuyueCode.endsmaller : 			jp.add(new JLabel("结束时间请大于起始时间", JLabel.CENTER), BorderLayout.CENTER);			break;
				case YuyueCode.selected: 				jp.add(new JLabel("该时间已选择过", JLabel.CENTER), BorderLayout.CENTER);				break;
				case YuyueCode.success:					jp.add(new JLabel("预约成功", JLabel.CENTER), BorderLayout.CENTER);					break;
				default : 								jp.add(new JLabel("未知错误，如影响使用请联系开发人员！", JLabel.CENTER), BorderLayout.CENTER);	break;
			}

			d.getContentPane().add(jp);
			d.setSize(300, 200);
			d.setResizable(false);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
	}
	
	
	
	
}





public class SeatOrdering extends JFrame {
	
	
	public static Container con;
	
	public static long[][][] Yuyue=new long[3][13][45];
	public static int[][][] hh=new int[3][13][45];
	public static int[][][] mm=new int[3][13][45];
	public static int[][][] ss=new int[3][13][45];
	public static int Floor;
	public static int Start;
	public static int End;
	public void TableInit() {
		
	}
	
	public SeatOrdering(JFrame win, long CurrentUserName){
		JFrame SOFrame = new JFrame("座位预约");
		Container c = SOFrame.getContentPane();
		c.setLayout(new GridLayout(5, 1));  //创建6行1列网格布局
		
		
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		
		
		
		
		String[] columnTime=new String[] {"","8`9","9`10","10`11","11`12","12`13","13`14","14`15","15`16","16`17","17`18","18`19","19`20"};
		String[][] columnRoom=new String[3][13];
		Yuyue[0][0][0]=1;
		Yuyue[1][0][0]=2;
		Yuyue[2][0][0]=3;
		for(int i=0;i<3;i++) {
			for(int j=0;j<13;j++) {
				int n=0;
				for(int k=0;k<45;k++) {
					
					if(j>0 && Yuyue[i][j][k]==0) {
						n++;
					}
				}
				if(j>0) {
					columnRoom[i][j]=String.valueOf(n);
				}
				else{ 
					columnRoom[i][j]=String.valueOf(Yuyue[i][0][0])+"楼"; 
				}
			}
		}
		JTable table=new JTable(columnRoom,columnTime);
		for(int i=0;i<13;i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
		table.setRowHeight(22);
		JScrollPane scrollPane = new JScrollPane(table);

		
		
		
		final JTextField UserName = new JTextField(String.valueOf(CurrentUserName), 10);
		UserName.setEditable(false);
		
		
		
		String[] listData1=new String[] {"--请输入--","1楼自习室","2楼自习室","3楼自习室"};
		final JComboBox<String> comboBox1 = new JComboBox<String>(listData1);
	
		
		
		String[] listData2=new String[] {"--请输入--","8：00","9：00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"};
		final JComboBox<String> comboBox2 = new JComboBox<String>(listData2);
		
		
		
		String[] listData3=new String[] {"--请输入--","8：00","9：00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
		final JComboBox<String> comboBox3 = new JComboBox<String>(listData3);
		
		
		
		final JButton Yuding = new JButton("预定座位");
		
		
		
//		final JButton Back = new JButton("返回");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED && comboBox1.getSelectedIndex()!=0) {
					Floor=comboBox1.getSelectedIndex();
				}
			}
		});
		
		
		
		comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED && comboBox2.getSelectedIndex()!=0) {
					Start=comboBox2.getSelectedIndex();
//					System.out.println(Start);
				}
			}
		});
		
		
		comboBox3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED && comboBox3.getSelectedIndex()!=0) {
					End=comboBox3.getSelectedIndex();
//					System.out.println(End);
				}
			}
		});
		
		
		Yuding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(Floor);
//				System.out.println(Start);
//				System.out.println(End);
				if(Floor!=0 && Start!=0 && End!=0) {
					
					
					if(Start<End) {
						
						int x=0;
						label1:
							for(int i=Start;i<End;i++) {
								for(int j=0;j<45;j++) {
									if(CurrentUserName==Yuyue[Floor-1][i][j]) {
										x=1;
										break label1;
									}
								}
							}
						label2:
							for(int k=Start;k<End;k++) {
								for(int i=0;i<3;i++) {
									for(int j=0;j<45;j++) {
										if(CurrentUserName==Yuyue[i][k][j]) {
											x=1;
											break label2;
										}	
									}
								}
							}
						if(x==1) {
							new  YuyueError(SeatOrdering.this,2);
						}
						else {
							for(int i=Start;i<End;i++) {	
								for(int j=0;j<45;j++) {
									if(Yuyue[Floor-1][i][j]==0) {
										Yuyue[Floor-1][i][j]=CurrentUserName;
										
										Calendar cal=Calendar.getInstance();
										hh[Floor-1][i][j]=cal.get(Calendar.HOUR_OF_DAY);
										mm[Floor-1][i][j]=cal.get(Calendar.MINUTE);
										ss[Floor-1][i][j]=cal.get(Calendar.SECOND);
//										System.out.println(hh[Floor-1][i][j]);
//										System.out.println(mm[Floor-1][i][j]);
//										System.out.println(ss[Floor-1][i][j]);
										break;
									}
								}
							}

//							SOFrame.dispose();
//							new  YuyueError(SeatOrdering.this,3);
//							new SeatOrdering(CurrentUserName);
							win.dispose();
							new  YuyueError(SeatOrdering.this,3);
							new AfterLogin((int) CurrentUserName);
						
							
							
							
							}
						
				
					}
					else {
						new  YuyueError(SeatOrdering.this,1);
					}
				}
				else {
					new  YuyueError(SeatOrdering.this,0);
				}
				
				
			}
			
		});
		
		
		
		
		
		
		
//		Back.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		
		
		
		
		
		
		
		p1.add(new JLabel("各个时间段自习室座位剩余情况"));
		p2.add(scrollPane);
		p3.add(new Label("卡号"));
		p3.add(UserName);
		p3.add(new Label("自习室"));
		p3.add(comboBox1);
		p4.add(new Label("开始时间"));
		p4.add(comboBox2);
		p4.add(new Label("结束时间"));
		p4.add(comboBox3);
		p5.add(Yuding);
//		p6.add(Back);
		
		
		
		
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		c.add(p5);
//		c.add(p6);
		
		
		
		
//		SOFrame.setSize(600, 600);
//		SOFrame.setResizable(false);
//		SOFrame.setVisible(true);
		con = c;
	}

//	public static void main(String[] args) {
//		// TODO 自动生成的方法存根
//		SeatOrdering s = new SeatOrdering(20186698);
//	}

}

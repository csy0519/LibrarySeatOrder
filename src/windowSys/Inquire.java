package windowSys;

import login.AccessDb;
import windowSys.Data;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class Inquire extends JFrame{
	
	public static Container Container_Inquire;
	
	String[] columnTime=new String[] {"卡号","用户名","自习室","开始时间","结束时间","预约时间"};
	String[][] columnRoom=new String[12][6];
	int[][] time=new int[3][12];
	int[][]	hour=new int[3][12];
	int[][]	minute=new int[3][12];
	int[][]	second=new int[3][12];
	
	public Inquire(long CurrentUserName)throws ClassNotFoundException,SQLException{

		JFrame SOFrame = new JFrame("预约记录");
		Container c = SOFrame.getContentPane();
		c.setLayout(new GridLayout(3, 1));  //创建3行1列网格布局
		Data data=new Data();
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		int n=0;

		for(int i=0;i<3;i++) {
			for(int j=1;j<13;j++) {
				int x=0;
				for(int k=0;k<45;k++) {
					if(data.Yuyue[i][j][k]==CurrentUserName) {
						hour[i][j-1]=data.hh[i][j][k];
						minute[i][j-1]=data.mm[i][j][k];
						second[i][j-1]=data.ss[i][j][k];
						x=1;
					}
				}
				
				if(x==1) {
					n++;
				}
				else {
					if (n!=0) {
						time[i][j-2]=n;
						n=0;
					}
				}
			}
		}
		
			int num=0;
			for(int i=0;i<3;i++) {
				for(int j=0;j<12;j++) {
					if(time[i][j]!=0) {
						columnRoom[num][0]=String.valueOf(CurrentUserName);
						AccessDb acc=new AccessDb();
						columnRoom[num][1]=acc.searchname(CurrentUserName);
						columnRoom[num][2]=String.valueOf(i+1)+"楼自习室";
						columnRoom[num][3]=String.valueOf(j+9-time[i][j])+":00";
						columnRoom[num][4]=String.valueOf(j+9)+":00";
						columnRoom[num][5]=String.valueOf(hour[i][j])+":"+String.valueOf(minute[i][j])+":"+String.valueOf(second[i][j]);
						num++;
					}
				}
			}
			
		JTable table=new JTable(columnRoom,columnTime);
		for(int i=0;i<6;i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
		table.setRowHeight(22);
		JScrollPane scrollPane = new JScrollPane(table);
		
		p1.add(new JLabel("用户座位预定信息"));
		p2.add(scrollPane);
		
		c.add(p1);
		c.add(p2);
		
		Container_Inquire = c;
	}
	
}
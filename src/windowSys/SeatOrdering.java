package windowSys;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class SeatOrdering extends JFrame {
	
	public void TableInit() {
		
	}
	
	public SeatOrdering(int CurrentUserName){
		JFrame SOFrame = new JFrame("座位预约");
		Container c = SOFrame.getContentPane();
		c.setLayout(new GridLayout(2, 1));
		
//		Vector<String> columnNameV = new Vector<>();// 定义表格列名向量
//		columnNameV.add("8");// 添加列名
//		columnNameV.add("9");// 添加列名
//		columnNameV.add("10");
//		columnNameV.add("11");
//		columnNameV.add("12");
//		columnNameV.add("13");
//		columnNameV.add("14");
//		columnNameV.add("15");
//		columnNameV.add("16");
//		columnNameV.add("17");
//		columnNameV.add("18");
//		columnNameV.add("19");
//		Vector<Vector<String>> tableValueV = new Vector<>();// 定义表格数据向量

//		char[] columnName = new char(); 
		
		for (int row = 0; row < 3; row++) {
			Vector<String> rowV = new Vector<>();// 定义表格行向量
			for(int column = 0; column < 12; column++) {
				rowV.add("45");// 添加单元格数据
			}
			tableValueV.add(rowV);// 将表格行向量添加到表格数据向量中
		}
		JTable table = new JTable(tableValueV, columnNameV);
		
		SOFrame.setSize(900, 600);
		SOFrame.setResizable(false);
		SOFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SeatOrdering s = new SeatOrdering(null);
	}

}

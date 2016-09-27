package view.page.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Table;

public class GraphTopPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GraphPanel panel;
	private Table table;

	public GraphTopPanel(GraphPanel panel, Table table) {
		this.panel = panel;
		this.table = table;
	}

	
//	@Override
//	public void paintAll(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintAll(g);
//	}
	
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
//		 System.out.println(getSize());
		 

			double xmin =  table.getMinX()<panel.getTable().getMinX()?table.getMinX():panel.getTable().getMinX();
			double xmax =  table.getMaxX()>panel.getTable().getMaxX()?table.getMaxX():panel.getTable().getMaxX();
			double ymin =  table.getMinY()<panel.getTable().getMinY()?table.getMinY():panel.getTable().getMinY();
			double ymax =  table.getMaxY()>panel.getTable().getMaxY()?table.getMaxY():panel.getTable().getMaxY();
		 

//			 System.out.println("x = " + xmin + " " + xmax);
//			 System.out.println("y = " +ymin + " " + ymax);
		 

			 int x = getSize().height;
			 int y = getSize().width;

//			 System.out.println(x + " " + y);
			 

 
			 
//			 System.out.println((xmax-xmin)/(panel.getTable().getMaxX()-panel.getTable().getMinX()));
//			 System.out.println(((xmax-xmin)/(table.getMaxX()-table.getMinX())));
			 
//			 System.out.println((ymax-ymin)/(panel.getTable().getMaxY()-panel.getTable().getMinY()));
//			 System.out.println((ymax-ymin)/(table.getMaxY()-table.getMinY()));

 
			 
			 double panelx =   (1/((xmax-xmin)/(panel.getTable().getMaxX()-panel.getTable().getMinX())));
			 double panely =   (1/((ymax-ymin)/(panel.getTable().getMaxY()-panel.getTable().getMinY())));
			 double thisx =   (1/((xmax-xmin)/(table.getMaxX()-table.getMinX())));
			 double thisy =   (1/((ymax-ymin)/(table.getMaxY()-table.getMinY())));

//			 System.out.println(panelx + " " + panely);
//			 System.out.println(thisx + " " + thisy);

		Dimension panelSize = new Dimension((int) (y * panelx), (int) (x * panely));
		Dimension thisSize = new Dimension((int)(y *thisx ),(int)(x * thisy));
			 
			 
//			 System.out.println("panelSize : " + panelSize);
//			 System.out.println("thisSize : " + thisSize);
			 
			  

		double fromx =   (panel.getTable().getMinX()-table.getMinX());
		double fromy =   (panel.getTable().getMinY()-table.getMinY());
		
		System.out.println(fromx + " " + fromy);
			 

		panel.setSize(panelSize);
		panel.setPosition(
				(int) (fromx * getMultiplyX(table, (int) thisSize.getWidth())),
				(int) (fromy * getMultiplyY(table, (int) thisSize.getHeight())));
//		panel.setPosition(50,0);

		panel.paintComponent(g);
		
		int maxPointSize = 10; 

		double[][] tableToDraw = getTableToDraw(table, (int)thisSize.getWidth()
				- maxPointSize - 1, (int)thisSize.getHeight() - maxPointSize - 1);

		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				g.setColor(Color.BLUE);

				g.drawOval((int) tableToDraw[rowNumber][0],
						(int) tableToDraw[rowNumber][collNumber], maxPointSize,
						maxPointSize);

			}
		}
	}

	private double[][] getTableToDraw(Table table, int x, int y) {

		double rowMultiply = getMultiplyX(table, x);
		double colMultiply = getMultiplyY(table, y);

		double[][] modifyTable = new double[table.getRowCount()][table
				.getCollCount()];
		
		for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
			
			modifyTable[rowNumber][0] = (table.getTable()[rowNumber][0] - table
					.getMinX()) * rowMultiply;
			
			for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++){
				
				modifyTable[rowNumber][collNumber] = (table.getTable()[rowNumber][collNumber] - table
						.getMinY()) * colMultiply;
				
			}
		}

		return modifyTable;
	}


	private double getMultiplyY(Table table, int y) {
		double colMultiply = y / (table.getMaxY() - table.getMinY());
		return colMultiply;
	}


	private double getMultiplyX(Table table, int x) {
		double rowMultiply = x / (table.getMaxX() - table.getMinX());
		return rowMultiply;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Test");

		Dimension size = new Dimension(700, 700);
		f.setSize(size);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Table table1 = new Table(new double[][] { 
//							   x->	 y^		y^
				new double[] { 0,	 1,		1.5 },
				new double[] { 0.5,	 1.5,	2 },
				new double[] { 1,	 2,		2.5},
				new double[] { 1.5,	 2.5,	3}
				}, new String[] { "Hello", "Bye", "Bye" });
		Table table2 = new Table(new double[][] { 
//				new double[] { 3, 1 },
//				new double[] { 2, 1.5 },
				new double[] { 1, 2 },
				new double[] { 0, 2.5 }
				}, new String[] { "Hello", "Bye" });

		GraphPanel g2 = new GraphPanel(table2); 
		f.add(new GraphTopPanel(g2, table1));
//		f.add(g2);
		f.setVisible(true);
	}
}

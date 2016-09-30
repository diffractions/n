package view.page.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Table;

public class GraphTopPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;

	private GraphPanel panel1;
	private GraphPanel panel2;


	public GraphTopPanel1(GraphPanel panel1, GraphPanel panel2) {
		this.panel1 = panel1;
		this.panel2 = panel2;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 //System.out.println(getSize());
		//System.out.println(panel2);
		 

			double xmin =  panel2.getTable().getMinX()<panel1.getTable().getMinX()?panel2.getTable().getMinX():panel1.getTable().getMinX();
			double xmax =  panel2.getTable().getMaxX()>panel1.getTable().getMaxX()?panel2.getTable().getMaxX():panel1.getTable().getMaxX();
			double ymin =  panel2.getTable().getMinY()<panel1.getTable().getMinY()?panel2.getTable().getMinY():panel1.getTable().getMinY();
			double ymax =  panel2.getTable().getMaxY()>panel1.getTable().getMaxY()?panel2.getTable().getMaxY():panel1.getTable().getMaxY();

			 int y = getSize().height;
			 int x = getSize().width;


			 System.out.println("x = " + x);
			 System.out.println("y = " + y);

			 System.out.println("x = " + xmin + " " + xmax);
			 System.out.println("y = " + ymin + " " + ymax);

			double xMultiply = (x) / (xmax - xmin);
			double yMultiply = (y) / (ymax - ymin);

			 System.out.println("xMultiply = " + xMultiply);
			 System.out.println("yMultiply = " + yMultiply);

			

			 double panel1x =   (1/((xmax-xmin)/(panel1.getTable().getMaxX()-panel1.getTable().getMinX())));
			 double panel1y =   (1/((ymax-ymin)/(panel1.getTable().getMaxY()-panel1.getTable().getMinY())));
			 double panel2x =   (1/((xmax-xmin)/(panel2.getTable().getMaxX()-panel2.getTable().getMinX())));
			 double panel2y =   (1/((ymax-ymin)/(panel2.getTable().getMaxY()-panel2.getTable().getMinY())));

			 System.out.println(panel1x + " " + panel1y);
			 System.out.println(panel2x + " " + panel2y);

			 System.out.println(panel2x + " " + panel2y);
			 System.out.println(panel2x + " " + panel2y);

			 System.out.println("dif x panel1  " + (panel1.getTable().getMinX()-xmin));
			 System.out.println("dif y panel1  " + (panel1.getTable().getMinY()-ymin));
			 System.out.println("dif x panel2  " + (panel2.getTable().getMinX()-xmin));
			 System.out.println("dif y panel2  " + (panel2.getTable().getMinY()-ymin));


			 System.out.println("panel 1 position = "  + (((double)(panel1.getTable().getMinX()-xmin))*xMultiply) + ", " + ((panel1.getTable().getMinY()-ymin)*yMultiply));
			 System.out.println("panel 2 position = "  + (((double)(panel2.getTable().getMinX()-xmin))*xMultiply) + ", " + ((panel2.getTable().getMinY()-ymin)*yMultiply));

			Dimension panel1position = new Dimension( (int) ((panel1.getTable().getMinX()-xmin)*xMultiply), (int) ((panel1.getTable().getMinY()-ymin)*yMultiply));
			Dimension panel2position = new Dimension( (int) ((panel2.getTable().getMinX()-xmin)*xMultiply), (int) ((panel2.getTable().getMinY()-ymin)*yMultiply));

			Dimension panel1Size = new Dimension( (int) (x * panel1x), (int) (y * panel1y ));
			Dimension panel2Size = new Dimension( (int) (x * panel2x), (int) (y * panel2y ));

			System.out.println("panel 1 Size" + panel1Size);
 			System.out.println("panel 2 Size" + panel2Size);
			
	
			panel1.setSize(panel1Size);
			panel2.setSize(panel2Size);

			panel1.setPosition(panel1position);
			panel2.setPosition(panel2position);
			
			//System.out.println(g);

			panel1.paintComponent(g);
			panel2.paintComponent(g);

		 
/*

//			 System.out.println(x + " " + y);
			 

 
			 
//			 System.out.println((xmax-xmin)/(panel.getTable().getMaxX()-panel.getTable().getMinX()));
//			 System.out.println(((xmax-xmin)/(table.getMaxX()-table.getMinX())));
			 
//			 System.out.println((ymax-ymin)/(panel.getTable().getMaxY()-panel.getTable().getMinY()));
//			 System.out.println((ymax-ymin)/(table.getMaxY()-table.getMinY()));

 
			 
			 double panel2x =   (1/((xmax-xmin)/(panel1.getTable().getMaxX()-panel1.getTable().getMinX())));
			 double panel2y =   (1/((ymax-ymin)/(panel1.getTable().getMaxY()-panel1.getTable().getMinY())));
			 double panel1x =   (1/((xmax-xmin)/(panel2.getTable().getMaxX()-panel2.getTable().getMinX())));
			 double panel1y =   (1/((ymax-ymin)/(panel2.getTable().getMaxY()-panel2.getTable().getMinY())));

//			 System.out.println(panel1x + " " + panel1y);
//			 System.out.println(panel2x + " " + panel2y);

		Dimension panel1Size = new Dimension((int) (y * panel1y ), (int) (x * panel1x));
		Dimension panel2Size = new Dimension((int) (y * panel2y ), (int) (x * panel2x));
			 
			 
//			 System.out.println("panelSize : " + panelSize);
//			 System.out.println("thisSize : " + thisSize);
			 
			  

		double from1x =   (panel1.getTable().getMinX()-panel2.getTable().getMinX());
		double from1y =   (panel1.getTable().getMinY()-panel2.getTable().getMinY());
		
		System.out.println(from1x + " " + from1y);
			 

		panel1.setSize(panel1Size);
		panel1.setPosition(
				(int) (from1x * getMultiplyX(panel2.getTable(), (int) panel2Size.getWidth())),
				(int) (from1y * getMultiplyY(panel2.getTable(), (int) panel2Size.getHeight())));
//		panel1.setPosition(50,0);

		panel1.paintComponent(g);

*/
	
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
				new double[] { 0,	 1,		1.5 },
				new double[] { 0.5,	 1.5,	2 },
				new double[] { 1,	 2,		2.5},
				new double[] { 1.5,	 2.5,	3}
				}, new String[] { "Hello", "Bye", "Bye" });
		Table table2 = new Table(new double[][] { 
				new double[] { 3, 1 },
				new double[] { 2, 1.5 },
				new double[] { 1, 2 },
				new double[] { 0, 2.5 }
				}, new String[] { "Hello", "Bye" });
/*

		Table table1 = new Table(new double[][] { 
				new double[] { 0, 0},
				new double[] { 2, 2},
				new double[] { 4, 4 }
				}, new String[] { "Hello", "Bye"});
		Table table2 = new Table(new double[][] { 
				new double[] { 1, 1 },
				new double[] { 4, 4 }
				}, new String[] { "Hello", "Bye" });
*/
		GraphPanel g1 = new GraphPanel(table1); 
		GraphPanel g2 = new GraphPanel(table2); 

 		f.add(new GraphTopPanel1(g1,g2));
		f.setVisible(true);
	}
}

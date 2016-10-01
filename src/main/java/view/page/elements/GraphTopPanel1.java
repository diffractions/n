package view.page.elements;
 
import java.awt.Dimension;
import java.awt.Graphics; 

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Table; 
 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseAdapter;  

public class GraphTopPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;

	private int maxPointSize = 10;
	private GraphPanel panel1;
	private GraphPanel1 panel2;

	int point = -1;

	private double xmax;

	private double ymin;

	private double ymax;

	private double xmin;
	double poitR;
	public GraphTopPanel1(GraphPanel panel1, final GraphPanel1 panel2) {
		this.panel1 = panel1;
		this.panel2 = panel2;

		addMouseMotionListener(new MouseAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (point != -1) {
					int x = e.getX();
					int y = e.getY();
					panel2.movePoint(point, x, y);
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				point = panel2.searchPoint(e);
				System.out.println(point);

			}

			public void mouseReleased(MouseEvent e) {
				point = -1;
				System.out.println(point);
			}

		});
		
		  xmin = panel2.getTable().getMinX() < panel1.getTable().getMinX() ? panel2.getTable().getMinX() : panel1.getTable().getMinX();
		  xmax = panel2.getTable().getMaxX() > panel1.getTable().getMaxX() ? panel2.getTable().getMaxX() : panel1.getTable().getMaxX();
		  ymin = panel2.getTable().getMinY() < panel1.getTable().getMinY() ? panel2.getTable().getMinY() : panel1.getTable().getMinY();
		  ymax = panel2.getTable().getMaxY() > panel1.getTable().getMaxY() ? panel2.getTable().getMaxY() : panel1.getTable().getMaxY();

		 System.out.println("x = " + xmin + " " + xmax);
		 System.out.println("y = " + ymin + " " + ymax);
 
		  panel2.setYmin(ymin); 
		  panel2.setXmin(xmin);

		  panel2.setYdif((ymax - ymin));
		  panel2.setXdif((xmax - xmin));
		  
			 poitR = maxPointSize / 2;
			 
			 
			 
			 

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 


		


		int height = getSize().height - maxPointSize - 1;
		int width = getSize().width - maxPointSize - 1;

		// System.out.println("x = " + x);
		// System.out.println("y = " + y);
 
		double xMultiply = (width) / (xmax - xmin);
		double yMultiply = (height) / (ymax - ymin);

		// System.out.println("xMultiply = " + xMultiply);
		// System.out.println("yMultiply = " + yMultiply);

		double panel1x = (1 / ((xmax - xmin) / (panel1.getTable().getMaxX() - panel1.getTable().getMinX())));
		double panel1y = (1 / ((ymax - ymin) / (panel1.getTable().getMaxY() - panel1.getTable().getMinY())));
		double panel2x = (1 / ((xmax - xmin) / (panel2.getTable().getMaxX() - panel2.getTable().getMinX())));
		double panel2y = (1 / ((ymax - ymin) / (panel2.getTable().getMaxY() - panel2.getTable().getMinY())));

		// System.out.println("multiply panel1 : " + panel1x + " " + panel1y);
		// System.out.println("multiply panel2 : " + panel2x + " " + panel2y);
 
		Dimension panel1position = new Dimension((int) Math.round(((panel1.getTable().getMinX() - xmin) * xMultiply) + poitR),
				(int) (Math.round((panel1.getTable().getMinY() - ymin) * yMultiply) + poitR));
		Dimension panel2position = new Dimension((int) Math.round(((panel2.getTable().getMinX() - xmin) * xMultiply) + poitR),
				(int) (Math.round((panel2.getTable().getMinY() - ymin) * yMultiply) + poitR));

		Dimension panel1Size = new Dimension((int) Math.round(width * panel1x), (int) Math.round(height * panel1y));
		Dimension panel2Size = new Dimension((int) Math.round(width * panel2x), (int) Math.round(height * panel2y));

		// System.out.println("panel 1 Size" + panel1Size);
		// System.out.println("panel 2 Size" + panel2Size);

		panel1.setSize(panel1Size);
		
		panel2.setSize(width,height);
		panel2.setSizeRange(panel2Size);
  
		panel1.setPosition(panel1position);
//		panel2.setPosition(new Dimension((int)poitR,(int)poitR));
//		panel2.setPosition(panel2position);
  
		panel1.paintComponent(g);
		panel2.paintComponent(g);

	}

 
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Test");

		Dimension size = new Dimension(300, 600);
		f.setSize(size);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Table table1 = new Table(new double[][] { new double[] { 0, 1, 1.5 },
				new double[] { 0.5, 1.5, 2 }, new double[] { 1, 2, 2.5 },
				new double[] { 1.5, 2.5, 3 } }, new String[] { "Hello", "Bye",
				"Bye" });
		Table table2 = new Table(new double[][] { /*
												 * new double[] { 0, 0 }, new
												 * double[] { 3, 1 },
												 */new double[] { 2, 1.5 },
				new double[] { 1, 2 }, new double[] { 0, 2.5 } }, new String[] {
				"Hello", "Bye" });

		GraphPanel g1 = new GraphPanel(table1);
		GraphPanel1 g2 = new GraphPanel1(table2);
		//
		f.add(new GraphTopPanel1(g1, g2));
		// f.add( g2);
		f.setVisible(true);
	}
}

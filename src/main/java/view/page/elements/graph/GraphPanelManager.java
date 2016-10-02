package view.page.elements.graph;
 
import java.awt.Dimension;
import java.awt.Graphics; 

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Table;
import entity.ModifyTable;
import entity.SimpleTable; 
   

public class GraphPanelManager extends JPanel {

	private static final long serialVersionUID = 1L;

	private GraphPanel rootPanel;
	private ModifyGraphPanel [] topPanels;
 
	private double xmax = Integer.MIN_VALUE; 
	private double ymin = Integer.MAX_VALUE; 
	private double ymax = Integer.MIN_VALUE; 
	private double xmin = Integer.MAX_VALUE;

	private GraphTopPnaelMouseListener ml;

	private double rootPanelX;
 	private double rootPanelY;
	

	private double maxPointSize;
	private double maxPoitRadius;
	
	

	public GraphPanelManager(Table table1, ModifyTable ... tables) {
		this.rootPanel = new RootGraphPanel(table1);
		this.topPanels = new TopGraphPanel [tables.length];
 
		
		updateExtr(rootPanel);
		
		for(int i = 0 ; i<tables.length; i++){
			this.topPanels[i]=new TopGraphPanel(tables[i]);  
			
			ml = new GraphTopPnaelMouseListener(tables[i]);
			ml.addModifyGraphPanel(this.topPanels[i]);
			ml.addComponent(this);
			
			addMouseMotionListener(ml);
			addMouseListener(ml); 
			updateExtr(topPanels[i]); 
			
  		}
		
		for (ModifyGraphPanel panel : this.topPanels) {
			panel.setYmin(ymin);
			panel.setXmin(xmin); 
			panel.setYdif((ymax - ymin));
			panel.setXdif((xmax - xmin));
		}
 
		
		this.rootPanel.setXdif(table1.getMaxX() - table1.getMinX());
		this.rootPanel.setYdif(table1.getMaxY() - table1.getMinY()); 
		this.rootPanel.setXmin(table1.getMinX());
		this.rootPanel.setYmin(table1.getMinY());
		
		this.rootPanelX = (1 / ((xmax - xmin) / (rootPanel.getTable().getMaxX() - rootPanel.getTable().getMinX())));
		this.rootPanelY = (1 / ((ymax - ymin) / (rootPanel.getTable().getMaxY() - rootPanel.getTable().getMinY())));
		
 		
		this.maxPointSize = maxPoitRadius*2;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		

 
		double height = getSize().height - maxPointSize - 1;
		double width = getSize().width - maxPointSize - 1;
		updateRootGraphPanel( height, width);

		
		rootPanel.paintComponent(g);
		
		for (ModifyGraphPanel panel : topPanels) {
			panel.setSize(new Dimension((int)width, (int)height));
			panel.setPosition(new Dimension((int) maxPoitRadius, (int) maxPoitRadius));
			panel.paintComponent(g);
		}
	}

	private void updateExtr(GraphPanel panel1) {
		double xmin1p = panel1.getTable().getMinX() < panel1.getTable().getMinX() ? panel1.getTable().getMinX() : panel1.getTable().getMinX();
		double xmax1p = panel1.getTable().getMaxX() > panel1.getTable().getMaxX() ? panel1.getTable().getMaxX() : panel1.getTable().getMaxX();
		double ymin1p = panel1.getTable().getMinY() < panel1.getTable().getMinY() ? panel1.getTable().getMinY() : panel1.getTable().getMinY();
		double ymax1p = panel1.getTable().getMaxY() > panel1.getTable().getMaxY() ? panel1.getTable().getMaxY() : panel1.getTable().getMaxY();

		xmax = xmax<xmax1p?xmax1p:xmax;
		ymax = ymax<ymax1p?ymax1p:ymax;
		xmin = xmin>xmin1p?xmin1p:xmin;
		ymin = ymin>ymin1p?ymin1p:ymin;
		
		maxPoitRadius =  maxPoitRadius > panel1.getLineView().getMaxRadius()? maxPoitRadius:panel1.getLineView().getMaxRadius();
	}
	
	private void updateRootGraphPanel( double height, double width) {

		
		double xMultiply = (width) / (xmax - xmin);
		double yMultiply = (height) / (ymax - ymin);
  

		
		Dimension panel1position = new Dimension((int) Math.round(((rootPanel.getTable().getMinX() - xmin) * xMultiply) + maxPoitRadius),
				(int) (Math.round((rootPanel.getTable().getMinY() - ymin) * yMultiply) + maxPoitRadius));
 
		Dimension panel1Size = new Dimension((int) Math.round(width * rootPanelX), (int) Math.round(height * rootPanelY));
 
 		rootPanel.setSize(panel1Size);
		rootPanel.setPosition(panel1position);
		 
	}

 
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Test");

		Dimension size = new Dimension(300, 600);
		f.setSize(size);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimpleTable table1 = new SimpleTable(new double[][] {
				new double[] { 0, 	1, 		1.5 },
				new double[] { 0.5, 1.5, 	2 },
				new double[] { 1, 	2, 		2.5 },
				new double[] { 1.5, 2.5, 	3 } }, new String[] { "Hello", "Bye",
				"Bye" });
//		Table table2 = new Table(new double[][] { new double[] { 2, 1.5 },
//				new double[] { 1, 2 }, new double[] { 0, 2.5 } }, new String[] {
//				"Hello", "Bye" });
//		Table table3 = new Table(new double[][] { new double[] { 0, 0 },
//				new double[] { 3, 1 }, }, new String[] { "Hello", "Bye" });

 
		
		f.add(new GraphPanelManager(table1/*,table2,table3*/)); 
		f.setVisible(true);
	}
}

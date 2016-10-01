package view.page.elements;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.Arrays;

import javax.swing.JPanel;

import entity.Table;

class GraphPanel1 extends JPanel {

	public void setXdif(double xdif) {
		this.xdif = xdif;
	}

	public void setYdif(double ydif) {
		this.ydif = ydif;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
 
 

	private static final long serialVersionUID = 1L;

	private ArrayList<Ellipse2D> points;

	double xy = 0;

	double yy = 0;

	Table table;

	double rowMultiply;
	double colMultiply;

	private   double xdif; 
	private   double ydif;
	
	private   double xmin; 
	private   double ymin;

	double poitR;


	public GraphPanel1(Table table) {
		this.table = table;

//		xdif = (table.getMaxX() - table.getMinX());
//		ydif = (table.getMaxY() - table.getMinY());
//
//		xmin = table.getMinX();
//		ymin = table.getMinY();
 
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		System.out.println(1);
	}
	
	 
	
	
	public ArrayList<Ellipse2D> getPoints() {
		return points;
	}
	public Table getTable() {
		return table;
	}
	private double[][] getTableToDraw(Table table) {

		double[][] modifyTable = new double[table.getRowCount()][table
				.getCollCount()];
		for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
			modifyTable[rowNumber][0] = rowToDraw(table.getTable()[rowNumber][0]);
			for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++)
				modifyTable[rowNumber][collNumber] = colToDraw(table.getTable()[rowNumber][collNumber]);

		}

		return modifyTable;
	}

	public void movePoint(int pos, int newX, int newY) { 
		newX-=poitR;
		newY-=poitR;
		 double xpt = rowToTable(newX);
		 double ypt = colToTable(newY);
 
		 double [] tRow = table.getRow(pos);
		 tRow[1] = ypt;
		 tRow[0] = xpt;
		
		 table.updRow(pos, tRow);
	}

	@Override
	public void paintComponent(Graphics g) {

//		rowMultiply = getWidth() / xdif;
//		colMultiply = getHeight() / ydif;
//		rowMultiply = range.getWidth() / xdif;
//		colMultiply =  range.getHeight() / ydif;

		rowMultiply = getWidth() / xdif;
		colMultiply =  getHeight() / ydif;

		points = new ArrayList<Ellipse2D>(); 
//		 System.out.println(getWidth()+"  " + getHeight()); 
		int maxPointSize = 10;

		 poitR = maxPointSize / 2;

		double[][] tableToDraw = getTableToDraw(table);
 

//		System.out.println(Arrays.deepToString(table.getTable()));
//		System.out.println(Arrays.deepToString(tableToDraw));
		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
 

				points.add(new Ellipse2D.Double(Math.round(xy - poitR
						+ tableToDraw[rowNumber][0]), Math.round(yy - poitR
						+ tableToDraw[rowNumber][collNumber]), maxPointSize,
						maxPointSize));
 

			}

		}
 
		Graphics2D g2 = (Graphics2D) g;
		for (Ellipse2D e : points) {
			// System.out.println(e.getCenterX()+" " + e.getCenterY());
			g2.draw(e);
		}
	}

	Dimension range = null;

//	private double yminS;
//	private double getHeightRange() { 
//		return range.height;
//	}
//
//	private double getWidthRange() { 
//		return range.getWidth();
//	}

//	private double xminS;
 
	
	public void setSizeRange(Dimension d) {
//		System.out.println("set size d = " + d);
		range = d;
//		super.setSize(d);
	}
	
	private double rowToDraw(double val) {
		return (val - xmin) * rowMultiply;
	}

	private double colToDraw(double val) {
		return (val - ymin) * colMultiply;
	}

	private double colToTable(double val) {
		return ymin + (val / colMultiply);
	}

	private double rowToTable(double val) {
		return xmin + (val / rowMultiply);
	}
	
	public int searchPoint(MouseEvent e) {

		int i = 0;

		Point2D p = e.getPoint();
		for (Ellipse2D t : getPoints()) {
			if (t.contains(p)) {
				return i;
			}
			i++;
		} 
		return -1;
	}

//	public void setPosition(Dimension position) {
//		this.xy = position.getWidth();
//		this.yy = position.getHeight();
//	}

 
}
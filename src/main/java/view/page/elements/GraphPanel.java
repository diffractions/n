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

class GraphPanel extends JPanel {

	public ArrayList<Ellipse2D> getPoints() {
		return points;
	}

	private ArrayList<Ellipse2D> points;

	public void setPosition(double xy, double yy) {
		// System.out.println("position = " + yy+ " " + xy);

		this.xy = xy;
		this.yy = yy;
	}

	public void setPosition(Dimension position) {
		setPosition(position.getWidth(), position.getHeight());
	}

	public Table getTable() {
		return table;
	}

	double xy = 0;
	double yy = 0;

	public GraphPanel(Table table) {
		this.table = table;

		xdif = (table.getMaxX() - table.getMinX());
		ydif = (table.getMaxY() - table.getMinY());

		xmin = table.getMinX();
		ymin = table.getMinY();

		// setBackground(Color.WHITE);
		// setForeground(Color.WHITE);
	}

	Table table;
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {

		rowMultiply = getWidth() / xdif;
		colMultiply = getHeight() / ydif;

		points = new ArrayList<Ellipse2D>();
		// System.out.println(g);
		// System.out.println(getWidth()+"  " + getHeight());
		// super.paintComponent(g);
		int maxPointSize = 10;

		double poitR = maxPointSize / 2;

		double[][] tableToDraw = getTableToDraw(table);
		// double[][] tableToDraw = getTableToDraw(table, getWidth(),
		// getHeight());

//		System.out.println(Arrays.deepToString(table.getTable()));
//		System.out.println(Arrays.deepToString(tableToDraw));
		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				// points.add(new Ellipse2DTablePint (Math.round(xy - poitR
				// + tableToDraw[rowNumber][0]), Math.round(yy - poitR
				// + tableToDraw[rowNumber][collNumber]), maxPointSize,
				// maxPointSize, table, collNumber, rowNumber, getWidth()
				// / (table.getMaxX() - table.getMinX()),
				// getHeight() / (table.getMaxY() - table.getMinY()),
				// table.getMinX(), table.getMinY()));

				points.add(new Ellipse2D.Double(Math.round(xy - poitR
						+ tableToDraw[rowNumber][0]), Math.round(yy - poitR
						+ tableToDraw[rowNumber][collNumber]), maxPointSize,
						maxPointSize));

				// g.drawOval((int) Math.round(xy - poitR
				// + tableToDraw[rowNumber][0]), (int) Math.round(yy
				// - poitR + tableToDraw[rowNumber][collNumber]),
				// maxPointSize, maxPointSize);

			}

		}

		// System.out.println(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ellipse2D e : points) {
			// System.out.println(e.getCenterX()+" " + e.getCenterY());
			g2.draw(e);
		}
	}

	// private double[][] getTableToDraw(Table table, int x, int y) {
	//
	// double rowMultiply = x / (table.getMaxX() - table.getMinX());
	// double colMultiply = y / (table.getMaxY() - table.getMinY());
	//
	// double[][] modifyTable = new double[table.getRowCount()][table
	// .getCollCount()];
	// for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
	// modifyTable[rowNumber][0] = (table.getTable()[rowNumber][0] - table
	// .getMinX()) * rowMultiply;
	// for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++)
	// modifyTable[rowNumber][collNumber] =
	// (table.getTable()[rowNumber][collNumber] - table
	// .getMinY()) * colMultiply;
	// }
	//
	// return modifyTable;
	// }

	double rowMultiply;
	double colMultiply;

	private final double xdif;
	private final double ydif;
	private final double xmin;
	private final double ymin;

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

	private double colToDraw(double val) {
		return (val - ymin) * colMultiply;
	}

	private double rowToDraw(double val) {
		return (val - xmin) * rowMultiply;
	}

	private double colToTable(double val) {
		return ymin + (val / colMultiply);
	}

	private double rowToTable(double val) {
		return xmin + (val / rowMultiply);
	}

	public void movePoint(int pos, int newX, int newY) {
//		 double xp = point.getCenterX();
//		 double yp = point.getCenterY();
		
		 double xpt = rowToTable(newX);
		 double ypt = colToTable(newY);
		//
		 double [] tRow = table.getRow(pos);
		 tRow[1] = ypt;
		 tRow[0] = xpt;
		
		 table.updRow(pos, tRow);
	}

	// public void movePoint(Ellipse2D point, int row, int col){
	// double xp = point.getCenterX();
	// double yp = point.getCenterY();
	//
	// double xpt = rowToTable(xp);
	// double ypt = colToTable(yp);
	//
	// double [] tRow = table.getRow(row);
	// tRow[col] = ypt;
	// tRow[0] = xpt;
	//
	// table.updRow(row, tRow);
	// }



	public int searchPoint(MouseEvent e) {

		int i = 0;

		Point2D p = e.getPoint();
		for (Ellipse2D t : getPoints()) {
			i++;
			if (t.contains(p)) {
				return i;
			}
		}
 
		return -1;
	}
}
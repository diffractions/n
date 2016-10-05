package view.page.elements.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;

import view.page.PageManager;
import entity.Table;

public class RootGraphPanel extends JPanel implements GraphPanel {

	protected static final long serialVersionUID = 1L;

	protected final Table table;

	protected double xPosition = 0;
	protected double yPosition = 0;

	protected double rowMultiply;
	protected double colMultiply;

	protected double xdif;
	protected double ydif;

	protected double xmin;
	protected double ymin;

//	public LinkedList<Ellipse2D> points;
	

	public Map<Integer, LinkedList<Ellipse2D>> points;

//	protected static Map<Table, GraphLineView> tableLineView = new HashMap<Table, GraphLineView>() {
//
//		private static final long serialVersionUID = 1L;
//
//		public GraphLineView put(Table key, GraphLineView value) {
//			if (get(key) != null) {
//				// System.out.println("++++++++++");
//				get(key).update(key);
//				return get(key);
//			} else
//				// System.out.println("-----------");
//				return super.put(key, value);
//		}
//
//	};

	public RootGraphPanel(Table table) {

		putView(table);
		this.table = table;
	}

	public void putView(Table table) {
		PageManager.tableLineView.put(table, new GraphLineWiew1(table));
	}

	@Override
	public void paintComponent(Graphics g) {

		rowMultiply = getWidth() / xdif;
		colMultiply = getHeight() / ydif;
		
		points = new HashMap<Integer, LinkedList<Ellipse2D>>();

		double[][] tableToDraw = getTableToDraw(table);
		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber += PageManager.tableLineView
					.get(this.table).getDropPoint()) {
				// for (int rowNumber = 0; rowNumber < table.getRowCount();
				// rowNumber+=getIO()) {
				drawPoint(g, tableToDraw, collNumber, rowNumber);
//				getLineView().paintPoint(g, collNumber);

			}
		}

		paintOld(g);
	}

	@Override
	public void drawPoint(Graphics g, double[][] tableToDraw, int collNumber,
			int rowNumber) {

		if(	points.get(collNumber)==null){
			points.put(collNumber, new LinkedList<Ellipse2D>());
		}
		
		LinkedList<Ellipse2D> list = points.get(collNumber);
		
		list.add( new Ellipse2D.Double(Math.round(xPosition
				- getLineView().getMaxRadius() + tableToDraw[rowNumber][0]),
				Math.round(yPosition - getLineView().getMaxRadius()
						+ tableToDraw[rowNumber][collNumber]), getLineView()
						.getDiameter(collNumber), getLineView().getDiameter(
						collNumber)));
//		getLineView().paintPoint(g, collNumber);
	}

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public void setPosition(Dimension position) {
		this.xPosition = position.getWidth();
		this.yPosition = position.getHeight();
	}

	@Override
	public GraphLineView getLineView() {
		return PageManager.tableLineView.get(this.table);
	}

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

	private double rowToDraw(double val) {
		return (val - xmin) * rowMultiply;
	}

	private double colToDraw(double val) {
		return (val - ymin) * colMultiply;
	}

	@Override
	public void paintOld(Graphics g) {

	/*	LinkedList<Ellipse2D> list = points.get(collNumber);

		list.clear() ;
		
		list.add( new Ellipse2D.Double(Math.round(xPosition
				- getLineView().getMaxRadius() + tableToDraw[rowNumber][0]),
				Math.round(yPosition - getLineView().getMaxRadius()
						+ tableToDraw[rowNumber][collNumber]), getLineView()
						.getDiameter(collNumber), getLineView().getDiameter(
						collNumber)));
*/

		Graphics2D g2 = (Graphics2D) g;
	//	System.out.println(points);
		for (int i = 1; i <= points.size(); i++){
//			System.out.println(i);
			for (Ellipse2D e : points.get(i)) {

				getLineView().paintPoint(g, i);	
				g2.draw(e);
			}

		}

	}

}

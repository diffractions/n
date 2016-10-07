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
import view.page.elements.elementview.GraphLineView;
import view.page.elements.elementview.GraphLineWiew1;
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
 
	

	public Map<Integer, LinkedList<Ellipse2D>> points;

	public RootGraphPanel(Table table) {

		putView(table);
		this.table = table;
	}

	public void putView(Table table) {
		PageManager.tableLineView.put(table, new GraphLineWiew1(table));
	}

	@Override
	public void paintComponent(Graphics g) {
//		System.out.println("1");

		if(getLineView().getUpdate()){
	
	
			rowMultiply = getWidth() / xdif;
			colMultiply = getHeight() / ydif;
			
			points = new HashMap<Integer, LinkedList<Ellipse2D>>();
	
			double[][] tableToDraw = getTableToDraw(table);
			for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
				for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber += PageManager.tableLineView
						.get(this.table).getDropPoint()) {
	 
					drawPoint(g, tableToDraw, collNumber, rowNumber); 
	
				}
			}
		}
		paintOld(g);
		getLineView().setUpdate(false);

	}

	@Override
	public void drawPoint(Graphics g, double[][] tableToDraw, int collNumber,
			int rowNumber) {

		if(	points.get(collNumber)==null){
			points.put(collNumber, new LinkedList<Ellipse2D>());
		}
		
		LinkedList<Ellipse2D> list = points.get(collNumber);
		
		list.add( new Ellipse2D.Double(
				Math.round(xPosition - getLineView().getDiameter(collNumber)/2 + tableToDraw[rowNumber][0]),
				Math.round(yPosition - getLineView().getDiameter(collNumber)/2 + tableToDraw[rowNumber][collNumber]), 
				getLineView().getDiameter(collNumber), 
				getLineView().getDiameter(collNumber)));
		
		
		 
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
//		System.out.println(2);

		Graphics2D g2 = (Graphics2D) g;
		for (int i = 1; i <= points.size(); i++) {
			for (Ellipse2D e : points.get(i)) {

				getLineView().paintPoint(g, i);
				g2.draw(e);
			}

		}

	}
	 

}

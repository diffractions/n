package view.page.elements.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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

	private static Map<Table, GraphLineView> tableLineView = new HashMap<Table, GraphLineView>() {

		private static final long serialVersionUID = 1L;

		public GraphLineView put(Table key, GraphLineView value) {
			if (get(key) != null) {
				// System.out.println("++++++++++");
				get(key).update(key);
				return get(key);
			} else
				// System.out.println("-----------");
				return super.put(key, value);
		}

	};

	public RootGraphPanel(Table table) {
		tableLineView.put(table, new GraphLineWiew1(table));
		this.table = table;
	}

	@Override
	public void paintComponent(Graphics g) {

		rowMultiply = getWidth() / xdif;
		colMultiply = getHeight() / ydif;

		double[][] tableToDraw = getTableToDraw(table);
		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
				drawPoint(g, tableToDraw, collNumber, rowNumber);

			}
		}
	}

	@Override
	public void drawPoint(Graphics g, double[][] tableToDraw, int collNumber,
			int rowNumber) {
		getLineView().paintPoint(g, collNumber);
		g.drawOval(
				(int) Math.round(xPosition - getLineView().getMaxRadius()
						+ tableToDraw[rowNumber][0]),
				(int) Math.round(yPosition - getLineView().getMaxRadius()
						+ tableToDraw[rowNumber][collNumber]),
				(int) getLineView().getDiameter(collNumber),
				(int) getLineView().getDiameter(collNumber));
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
		return tableLineView.get(this.table);
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

}

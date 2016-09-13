package view.page.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entity.Table;

class GraphFrame extends JPanel {

	public GraphFrame(Table table) {
		this.table = table;
//		setBackground(Color.WHITE);
//		setForeground(Color.WHITE);
	}

	Table table;
	private static final long serialVersionUID = 1L;

	@Override
//	protected void paintComponent(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponent(g);
//	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int maxPointSize = 10;
		g.clearRect(1, 1, getWidth() - maxPointSize, getHeight() - maxPointSize);

		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(),  getHeight());
		
		double[][] tableToDraw = getTableToDraw(table, getWidth()
				- maxPointSize - 1, getHeight() - maxPointSize - 1);

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

		double rowMultiply = x / (table.getMaxX() - table.getMinX());
		double colMultiply = y / (table.getMaxY() - table.getMinY());

		double[][] modifyTable = new double[table.getRowCount()][table
				.getCollCount()];
		for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
			modifyTable[rowNumber][0] = (table.getTable()[rowNumber][0] - table
					.getMinX()) * rowMultiply;
			for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++)
				modifyTable[rowNumber][collNumber] = (table.getTable()[rowNumber][collNumber] - table
						.getMinY()) * colMultiply;
		}

		return modifyTable;
	}
}
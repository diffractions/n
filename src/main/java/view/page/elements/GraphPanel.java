package view.page.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Table;

class GraphPanel extends JPanel {

	public void setPosition( double xy, double yy) {
		//System.out.println("position = " + yy+ " " + xy);
		this.xy = xy;
		this.yy = yy;
	}


	public void setPosition( Dimension position) {
		setPosition(position.getWidth(), position.getHeight());
	}
	
	public Table getTable() {
		return table;
	}

	double xy = 0;
	double yy = 0;

	public GraphPanel(Table table) {
		this.table = table;
		// setBackground(Color.WHITE);
		// setForeground(Color.WHITE);
	}

	Table table;
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
//System.out.println(g);
		//System.out.println(getWidth()+"  " + getHeight());
		//super.paintComponent(g);
		int maxPointSize = 10;


		double poitR = maxPointSize/2;
		

		//g.clearRect(1+xy, 1+yy, getWidth() - maxPointSize+xy, getHeight() - maxPointSize+yy);

		//g.setColor(Color.PINK);
		//g.fillRect(0+xy , 0+yy, getWidth()+xy , getHeight()+xy);

 		//double[][] tableToDraw = getTableToDraw(table, getWidth() - maxPointSize - 1, getHeight() - maxPointSize - 1);
		double[][] tableToDraw = getTableToDraw(table, getWidth(), getHeight());

		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				//g.setColor(Color.BLUE);
 
				g.drawOval((int) Math.round(xy - poitR +  tableToDraw[rowNumber][0]), (int) Math.round(yy - poitR 
						+ tableToDraw[rowNumber][collNumber]),
						maxPointSize, maxPointSize);

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
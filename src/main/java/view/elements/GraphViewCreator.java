package view.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import entity.Table;

public class GraphViewCreator extends AbstractViewCreator implements
		ViewCreator {

	public GraphViewCreator(int x, int y) {
		super(x, y);

	};

	@Override
	public JPanel getView(Table table) {
		if (graph == null)
			graph = new JPanel();
		if (table == null)
			return graph;

		JPanel graph = paintData(table);
		paintAxis(table);
		graph.setBorder(BorderFactory.createLineBorder(Color.RED));
		return graph;
	}

	private void paintAxis(Table table) {

	}

	private JPanel graph = null;

	private JPanel paintData(Table table) {

		graph.setSize(x, y);

		Graphics g = graph.getGraphics();

		int maxPointSize = 10;

		// for (int i = 0; i < table.getColCount() - 1; i++) {
		// maxPointSize = (pointSize[i] > maxPointSize) ? pointSize[i]
		// : maxPointSize;
		// }

		// for (int points : pointSize)
		// maxPointSize = (points > maxPointSize) ? points : maxPointSize;

		// System.out.println(x+ " " + y);

		 System.out.println(graph.getWidth() + " " + maxPointSize + " "
		 + graph.getHeight() + " " + maxPointSize);

		System.out.println(g);
		g.clearRect(1, 1, graph.getWidth() - maxPointSize, graph.getHeight()
				- maxPointSize);

		// graph.removeAll();

		double[][] tableToDraw = getTableToDraw(table, graph.getWidth()
				- maxPointSize - 1, graph.getHeight() - maxPointSize - 1);

		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				// g.setColor(pointColor[collNumber - 1]);
				g.setColor(Color.BLUE);

				g.drawOval((int) tableToDraw[rowNumber][0],
						(int) tableToDraw[rowNumber][collNumber], 10, 10);
				// pointSize[collNumber - 1], pointSize[collNumber - 1]);

			}
		}

		// System.out.println(Arrays.deepToString(tableToDraw));
		return graph;

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

package view.elements;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import entity.Table;

public class GraphViewCreator extends AbstractViewCreator implements
		ViewCreator {

	public GraphViewCreator(int x, int y) {
		super(x, y);
		this.graph = new JPanel();

//		f.setSize(700, 700);
//		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		f.add(g);
//		f.setVisible(true);

	};

	// public GraphViewCreator(JPanel graph) {
	// super(graph.getWidth(), graph.getHeight());
	// this.graph = graph;
	// };

	private JPanel graph;

//	JFrame f = new JFrame("H1");
//	JPanel g = new JPanel();

	@Override
	public JPanel getView(Table table) {

//		System.out.println("G : GV1 : " + graph.hashCode());

		if (table != null) {
//			graph.setBorder(BorderFactory.createLineBorder(Color.RED));
			paintData(graph, table);
			paintAxis(table);

			// f.add(paintData(g, table));
		}

//		f.add(getViews(table)); 

//		System.out.println("G : GV2 : " + graph.hashCode());

		return graph;
	}

//	public JPanel getViews(Table table) {
//		System.out.println("G : GVS1 : " + g.hashCode());
//		if (table != null) {
////			g.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//			paintData(g, table);
//		}
//		System.out.println("G : GVS2 : " + g.hashCode());
//		return g;
//	}

	private void paintAxis(Table table) {

	}

	private JPanel paintData(JPanel graph, Table table) {

//		System.out.println("G : GV3 : " + graph.hashCode());

		Graphics g = graph.getGraphics();
		int maxPointSize = 10;
		System.out.println((graph.getWidth() - maxPointSize) + " "
				+ (graph.getHeight() - maxPointSize));
		g.clearRect(1, 1, graph.getWidth() - maxPointSize, graph.getHeight()
				- maxPointSize);

		double[][] tableToDraw = getTableToDraw(table, graph.getWidth()
				- maxPointSize - 1, graph.getHeight() - maxPointSize - 1);

		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				g.setColor(Color.BLUE);

				g.drawOval((int) tableToDraw[rowNumber][0],
						(int) tableToDraw[rowNumber][collNumber], maxPointSize,
						maxPointSize);

			}
		}
		return graph;
	}

//	 private void paintData(Table table) {
//	
//	 Graphics g = graph.getGraphics();
//	 int maxPointSize = 10;
//	 g.clearRect(1, 1, graph.getWidth() - maxPointSize, graph.getHeight()
//	 - maxPointSize);
//	
//	 double[][] tableToDraw = getTableToDraw(table, graph.getWidth()
//	 - maxPointSize - 1, graph.getHeight() - maxPointSize - 1);
//	
//	 for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++)
//	 {
//	 for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
//	
//	 g.setColor(Color.BLUE);
//	
//	 g.drawOval((int) tableToDraw[rowNumber][0],
//	 (int) tableToDraw[rowNumber][collNumber], maxPointSize,
//	 maxPointSize);
//	
//	 }
//	 }
//	
//	
//	 }

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

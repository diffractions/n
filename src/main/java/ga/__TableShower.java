package ga;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class __TableShower {

	private double minr;
	private double maxr;
	private double minc;
	private double maxc;
	private __TableFileReader table;

	private Color[] pointColor;
	private int[] pointSize;
	private String fPath;

	public static final Color BASE_COLOR = Color.black;
	public static final int BASE_POINT_SIZE = 1;

	public void setData(__TableFileReader table, String fPath) {
		this.table = table;
		minr = Double.MAX_VALUE;
		maxr = Double.MIN_VALUE;
		minc = Double.MAX_VALUE;
		maxc = Double.MIN_VALUE;

		pointColor = new Color[table.getCollCount()];
		pointSize = new int[table.getCollCount()];
		Arrays.fill(pointColor, BASE_COLOR);
		Arrays.fill(pointSize, BASE_POINT_SIZE);

		this.fPath = fPath;
	}

	private double[][] getTableToDraw(int x, int y) {

		if (minr == Double.MAX_VALUE && maxr == Double.MIN_VALUE
				&& minc == Double.MAX_VALUE && maxc == Double.MIN_VALUE) {
			for (double[] row : table.getTable()) {

				minr = (row[0] < minr) ? row[0] : minr;
				maxr = (row[0] > maxr) ? row[0] : maxr;

				for (int collNumber = 1; collNumber < row.length; collNumber++) {
					minc = (row[collNumber] < minc) ? row[collNumber] : minc;
					maxc = (row[collNumber] > maxc) ? row[collNumber] : maxc;
				}

			}
		}

		double rowMultiply = x / (maxr - minr);
		double colMultiply = y / (maxc - minc);

		double[][] modifyTable = new double[table.getRowCount()][table
				.getCollCount()];
		for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
			modifyTable[rowNumber][0] = (table.getTable()[rowNumber][0] - minr)
					* rowMultiply;
			for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++)
				modifyTable[rowNumber][collNumber] = (table.getTable()[rowNumber][collNumber] - minc)
						* colMultiply;
		}

		return modifyTable;
	}

	public void fillTable(JTable tables, String fPath) {

		if (table == null || !this.fPath.equals(fPath))
			return;

		DefaultTableModel models = new DefaultTableModel();

		for (String collName : table.getCollNames())
			models.addColumn(collName);

		for (double[] s : table.getTable()) {
			Double d[] = new Double[s.length];
			for (int i = 0; i < s.length; i++)
				d[i] = s[i];
			models.addRow(d);
		}

		tables.setModel(models);
	}

	public void fillGraph(JPanel graph, String fPath) {

		if (table == null || !this.fPath.equals(fPath))
			return;

		Graphics g = graph.getGraphics();

		int maxPointSize = 0;

		for (int i = 0 ; i < table.getCollCount()-1; i++){
			maxPointSize = (pointSize[i] > maxPointSize) ? pointSize[i] : maxPointSize;
		}
 
		
//		for (int points : pointSize)
//			maxPointSize = (points > maxPointSize) ? points : maxPointSize;

		g.clearRect(1, 1, graph.getWidth() - maxPointSize, graph.getHeight()
				- maxPointSize);

		// graph.removeAll();

		double[][] tableToDraw = getTableToDraw(graph.getWidth() - maxPointSize
				- 1, graph.getHeight() - maxPointSize - 1);

		for (int collNumber = 1; collNumber < table.getCollCount(); collNumber++) {
			for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {

				g.setColor(pointColor[collNumber - 1]);

				g.drawOval((int) tableToDraw[rowNumber][0],
						(int) tableToDraw[rowNumber][collNumber],
						pointSize[collNumber - 1], pointSize[collNumber - 1]);

			}
		}

	}

	public void setPointColor(String fPath, Color... pointColor) {

		if (table == null || !this.fPath.equals(fPath))
			return;

		this.pointColor = pointColor;
	}

	public void setPointSize(String fPath, int... pointSize) {

		if (table == null || !this.fPath.equals(fPath))
			return;

		this.pointSize = pointSize;
	}
}

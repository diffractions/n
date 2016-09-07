package entity;
 

public class Table {

	private double minX;
	private double maxX;
	private double minY;
	private double maxY;

	private int colCount = 0;
	private int rowCount = 0;
	private String[] heders;
	private double[][] table;

	public Table(String[] heders) {
		this.heders = heders;
		this.colCount = heders.length;
		this.table = new double[1][colCount];
		setNullExtr();
	}

	public String[] getHeaders() {
		return heders;
	}

	public void setHeader(int position, String name) {
		heders[position] = name;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public double[][] getTable() {
		double[][] tables = new double[rowCount][colCount];
		System.arraycopy(table, 0, tables, 0, rowCount);
		return tables;
	}

	public void addRow(double[] row) {

		if (table.length < rowCount * 2) {
			double[][] tables = new double[rowCount][colCount];
			System.arraycopy(table, 0, tables, 0, rowCount);
			table = new double[rowCount * 2][colCount];
			System.arraycopy(tables, 0, table, 0, rowCount);
			tables = null;
		}

		updRow(rowCount++, row);

	}

	public void delRow(int pos) {

		if (pos < rowCount) {
			// System.out.println("POSITION: " + pos + ", COUNT OF ROWS: " +
			// rowCount);
			System.arraycopy(table, pos + 1, table, pos, rowCount-- - pos);

			// System.out.println("IN: " + Arrays.deepToString(table));

			if (table.length - 1 > rowCount * 2) {
				double[][] tables = new double[rowCount][colCount];
				System.arraycopy(table, 0, tables, 0, rowCount);
				table = new double[table.length / 2][colCount];
				System.arraycopy(tables, 0, table, 0, rowCount);
				tables = null;
			}

			// System.out.println("OUT: " + Arrays.deepToString(table));
		} else
			throw new IndexOutOfBoundsException();

		updExtr();

	}

	public void updRow(int pos, double[] row) {

		if (pos < rowCount)
			System.arraycopy(row, 0, table[pos], 0, colCount);
		else
			throw new IndexOutOfBoundsException();

		updExtr(row);
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMinX() {
		return minX;
	}

	public double getMaxY() {
		return maxY;
	}

	public double getMinY() {
		return minY;
	}

	private void updExtr() {

		setNullExtr();

		for (int rowNumber = 0; rowNumber < rowCount; rowNumber++) {
			updMaxX(table[rowNumber][0]);
			updMinX(table[rowNumber][0]);
			for (int collNumber = 1; collNumber < colCount; collNumber++) {
				updMaxY(table[rowNumber][collNumber]);
				updMinY(table[rowNumber][collNumber]);
			}
		}

	}

	private void updExtr(double[] row) {

		if (rowCount == 1) {
			setNullExtr();
		}
		updMaxX(row[0]);
		updMinX(row[0]);
		for (int collNumber = 1; collNumber < colCount; collNumber++) {
			updMaxY(row[collNumber]);
			updMinY(row[collNumber]);
		}

	}

	private void setNullExtr() {
		minX = Double.MAX_VALUE;
		maxX = Double.MIN_VALUE;
		minY = Double.MAX_VALUE;
		maxY = Double.MIN_VALUE;
	}

	private void updMaxX(double x) {
		maxX = (x > maxX) ? x : maxX;
	}

	private void updMinX(double x) {
		minX = (x < minX) ? x : minX;
	}

	private void updMaxY(double y) {
		maxY = (y > maxY) ? y : maxY;
	}

	private void updMinY(double y) {
		minY = (y < minY) ? y : minY;
	}

	// public static void main(String[] args) {
	// Table t = new Table(new String[] { "H", "J", "K" });
	// t.addRow(new double[] { 0.5, 0.6, 0.7 });
	// t.addRow(new double[] { 0.4, 0.3, 0.2 });
	// t.addRow(new double[] { 0.4, 0.3, 0.9 });
	// t.addRow(new double[] { 0.4, 0.3, 0.8 });
	//
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	//
	// t.delRow(0);
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	//
	// t.delRow(1);
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	//
	// t.delRow(1);
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	//
	// t.updRow(0, new double[] { 0.5, 0.6, 0.7 });
	//
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	//
	// t.addRow(new double[] { -0.4, 0.3, 10.8 });
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	// t.addRow(new double[] { -0.4, -0.3, 10.8 });
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	// }
}

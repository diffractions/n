package entity;

public class SimpleTable implements Table {

	private double minX;
	private double maxX;
	private double minY;
	private double maxY;

	private int colCount = 0;
	private int rowCount = 0;
	private String[] heders;
	private double[][] table;

	public SimpleTable(String[] heders) {
		for (String s : heders)
			if (s == null || s.equals(""))
				throw new NullPointerException("wrong coll");

		this.heders = heders;
		this.colCount = heders.length;
		this.table = new double[1][colCount];
		setNullExtr();
	}

	public SimpleTable(double[][] table, String[] heders) {
		this(heders);

		if (getRowCount() == 0 || getRowCount() == table.length) {
			for (double[] row : table) {
				double[] addRow = new double[row.length];
				for (int i = 0; i < getCollCount(); i++) {
					addRow[i] = row[i];
				}

				addRow(addRow);
			}

			return;
		}
		throw new NullPointerException("wrong coll");
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

	public int getCollCount() {
		return colCount;
	}

	public double[][] getTable() {
		double[][] tables = new double[rowCount][colCount];
		System.arraycopy(table, 0, tables, 0, rowCount);
		return tables;
	}

	public void updCol(double[] col, int colls) {

		for (int pos = 0; pos < col.length; pos++) {
			table[pos][colls] = col[pos];
		}
	}

	public boolean hasCollByName(String collName) {
		return getCollPosByName(collName) != -1;
	}

	private int getCollPosByName(String collName) {
		int colls = 0;
		for (; colls < getCollCount(); colls++) {
			if (heders[colls].equalsIgnoreCase(collName))
				return colls;
		}
		return -1;
	}

	public void updCol(double[] col, String collName) {
		if (col.length != getRowCount() || collName == null
				|| collName.equals(""))
			throw new NullPointerException("wrong coll");
		int colls = -1;
		if ((colls = getCollPosByName(collName)) != -1) {
			updCol(col, colls);
			return;
		}

	}

	public void addCol(double[] col, String collName) {
		if (col.length != getRowCount() || collName == null
				|| collName.equals(""))
			throw new NullPointerException("wrong coll");

		int colls = -1;
		if ((colls = getCollPosByName(collName)) != -1) {
			updCol(col, colls);
			return;
		}

		String[] head = new String[getCollCount() + 1];
		for (int i = 0; i < getCollCount(); i++)
			head[i] = heders[i];
		head[getCollCount()] = collName;

		this.heders = head;
		this.colCount = heders.length;

		for (int pos = 0; pos < col.length; pos++) {
			double[] addRow = new double[getCollCount()];
			for (int poss = 0; poss < getCollCount() - 1; poss++) {
				addRow[poss] = getTable()[pos][poss];
			}
			addRow[getCollCount() - 1] = col[pos];
			updRow(pos, addRow);
		}
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

		if (pos < rowCount) {
			double[] d = new double[colCount];
			for (int i = 0; i < colCount; i++)
				d[i] = row[i];
			table[pos] = d;
		}
		// System.arraycopy(row, 0, table[pos], 0, colCount);
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

	protected void updExtr() {

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

	protected void updExtr(double[] row) {

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

	public double[] getRow(int row) {
		double[] ret = new double[getCollCount()];
		System.arraycopy(table[row], 0, ret, 0, getCollCount());
		return ret;
	}

}

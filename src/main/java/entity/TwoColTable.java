package entity;

import java.util.Arrays;

public class TwoColTable extends SimpleTable implements ModifyTable {

	RuntimeException e = new RuntimeException("Wrong coll count number");

	public TwoColTable(String[] heders) {
		super(heders);
		if (heders.length != colCount) {
			throw e;
		}

	}

	public TwoColTable(double[][] table, String[] heders) {
		super(table, heders);
		if (heders.length != colCount) {
			throw e;
		}

	}

	public void setHeader(int position, String name) {
		if (position < colCount)
			super.setHeader(position, name);
		else
			throw e;
	}

	public void updCol(double[] col, int colls) {
		if (colls < colCount)
			super.updCol(col, colls);
		else
			throw e;
	}

	public void addCol(double[] col, String collName) {
		throw e;
	}

	public void addRow(double[] row) {

		System.out.println(Arrays.toString(row));
		if (row.length == 2)
			super.addRow(row);
		else
			throw e;

	}

	public void updRow(int pos, double[] row) {

		if (row.length == 2)
			super.updRow(pos, row);
		else
			throw e;
	}

	protected void updExtr(double[] row) {

		if (row.length == 2)
			super.updExtr(row);
		else
			throw e;
	}

}

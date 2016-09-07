package service.table;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList; 

import entity.Table;

public class TableDataFileManager {

	// +writeTable(:Table)
	private String fPath = null;

	public Table readTable(File file) {
		return readTable(file.getPath());
	}

	public Table readTable(String filePath) {

		Table rtable = null;
		this.fPath = filePath;

		ArrayList<double[]> table = new ArrayList<>();

		try (BufferedInputStream is = new BufferedInputStream(
				new FileInputStream(filePath), 10240)) {

			byte b[] = new byte[1024];
			int size = 0;
			while ((size = is.read(b)) > 0) {
				for (String xs : new String(b, 0, size).split("\n")) {
					if (xs.startsWith("#")) {
						continue;
					}
					// System.out.println(xs);
					String[] add = xs.split("\t");

					if (add.length > 1) {

						double[] d = new double[add.length];
						// System.out.println(Arrays.toString(d));
						for (int i = 0; i < add.length; i++) {
							d[i] = Double.parseDouble(add[i].trim());
						}
						// System.out.println(Arrays.toString(d));
						table.add(d);
					}

				}

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// System.out.println(table);

		if (table.size() > 0) {

			String[] colNames = new String[table.get(0).length];
			for (int i = 0; i < colNames.length; i++) {
				colNames[i] = new String(new char[] { (char) (65 + i) });
			}
			rtable = new Table(colNames);

			for (double[] r : table) {
				rtable.addRow(r);
			}

		}

		return rtable;
	}

	public String getFilePath() {
		return fPath;
	}

	// public static void main(String[] args) {
	// TableDataFileManager f = new TableDataFileManager();
	// Table t = f.readTable("1.dat");
	// System.out.println(Arrays.deepToString(t.getTable()));
	// System.out.println(t.getMinX() + " " + t.getMaxX() + " " + t.getMinY()
	// + " " + t.getMaxY());
	// }
}

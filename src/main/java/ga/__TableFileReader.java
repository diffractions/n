package ga;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class __TableFileReader {

	private String[] collNames = new String[0];
	private double[][] table = new double[0][0];
	private String fPath = null;

	public String[] getCollNames() {
		return collNames;
	}

	public int getCollCount() {
		if (table.length != 0)
			return table[0].length;
		else
			return 0;
	}

	public int getRowCount() {
		return table.length;
	}

	public double[][] getTable() {
		return table;
	}

	public void readFile(File file) {
		readFile(file.getPath());

	}

	public void readFile(String filePath) {

		this.fPath = filePath;

		ArrayList<ArrayList<Double>> table = new ArrayList<>();

		try (BufferedInputStream is = new BufferedInputStream(
				new FileInputStream(filePath), 10240)) {

			byte b[] = new byte[1024];
			int size = 0;
			while ((size = is.read(b)) > 0) {
				for (String xs : new String(b, 0, size).split("\n")) {
					if (xs.startsWith("#")) {
						continue;
					}
					String[] add = xs.split("\t");

					if (add.length > 1) {

						while (table.size() < add.length) {
							table.add(new ArrayList<Double>());
						}

						for (int i = 0; i < add.length; i++) {
							table.get(i).add(Double.parseDouble(add[i].trim()));
						}

					}

				}

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (table.size() > 0) {
			double[][] fileTable = new double[table.get(0).size()][table.size()];
			for (int i = 0; i < table.size(); i++) {
				for (int o = 0; o < table.get(i).size(); o++)
					fileTable[o][i] = table.get(i).get(o);
			}
			this.table = fileTable;

			this.collNames = new String[getCollCount()];
			for (int i = 0; i < getCollCount(); i++) {
				collNames[i] = new String(new char[] { (char) (65 + i) });
			}

		} else {
			this.collNames = new String[0];
			this.table = new double[0][0];
		}
	}

	public String getPath() {
		return fPath;
	}
}

package service.table;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import entity.SimpleTable;
//import flanagan.analysis.CurveSmooth;

public class TableDataFileManager {

	// +writeTable(:Table)

	public SimpleTable readTable(File file) {
		return readTable(file.getPath());
	}

	public SimpleTable readTable(String filePath) {

		SimpleTable rtable = null;

		ArrayList<double[]> table = new ArrayList<>();

		try (BufferedInputStream is = new BufferedInputStream(
				new FileInputStream(filePath), 100240)) {

			byte b[] = new byte[100240];
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

//			for(double []d : table){
//				System.out.println(d[0]+"\t" + d[1]);
//			}
			

			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// System.out.println(table);

		if (table.size() > 0) {

			String[] colNames = new String[table.get(0).length];
			for (int i = 0; i < colNames.length; i++) {
				colNames[i] = new String(new char[] { (char) (65 + i) });
			}
			rtable = new SimpleTable(colNames);

			for (double[] r : table) {
				rtable.addRow(r);
			}

		}

		
		double[] d = new double[rtable.getTable().length];
//		int ds = 0;
//		for (double s[] : table) {
//			d[ds++] = s[1];
//		}
//		double[] dd = new CurveSmooth(d).savitzkyGolayPlot(01);
//		for (int i = 0; i < rtable.getTable().length; i++)
//			System.out.println(rtable.getTable()[i][0] + "\t" + dd[i]);
		
		
		return rtable;
	}

 
}

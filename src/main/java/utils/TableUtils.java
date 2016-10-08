package utils;

import entity.ModifyTable;
import entity.SimpleTable;
import entity.Table;
import entity.TwoColTable;

public class TableUtils {
	public static void sort(Table table, int coll) {
		double d[][] = table.getTable();
		for (int i = 0; i < d.length; i++) {
			for (int u = 0; u < d.length; u++) {
				if (d[i][coll] < d[u][coll]) {
					swap(d, i, u);
					// System.out.println("swap");
				}
			}
		}
	}

	private static void swap(double[][] d, int i, int u) {
		for (int p = 0; p < d[0].length; p++) {
			double l = d[i][p];
			d[i][p] = d[u][p];
			d[u][p] = l;
		}

	}

	public static ModifyTable[] separate(ModifyTable src, int size) {

		ModifyTable[] ret = new ModifyTable[size];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new TwoColTable(new String[] { src.getHeaders()[0], src.getHeaders()[1] + i });
		}

		for (int i = 0; i < src.getTable().length;) {
			for (Table s : ret) {
				if (i < src.getTable().length)
					s.addRow(src.getTable()[i++]);
			}
		}

		return ret;

	}

	// public static ModifyTable[] separateExtr(ModifyTable src1,ModifyTable
	// src2) {
	//
	//// ModifyTable[] ret = new ModifyTable[2];
	//// for (int i = 0; i < ret.length; i++) {
	//// ret[i] = new TwoColTable(new String[] { src.getHeaders()[0],
	// src.getHeaders()[1] + i });
	//// }
	//
	// int intialLength1 = src1.getRowCount();
	// int intialLength2 = src2.getRowCount();
	//
	// for (int i = 0; i < intialLength1;i++) {
	//
	// if (i > 0 && i < src.getTable().length-1) {
	// double a = calcA(src, i - 1, i + 1);
	// double b = calcB(src, i - 1, a);
	// double y = ((a * src.getTable()[i][0]) + b);
	// if (i % 2 == 0) {
	// ret[0].addRow(new double[] { src.getTable()[i][0], y });
	// } else {
	// ret[1].addRow(new double[] { src.getTable()[i][0], y });
	// }
	// }
	//// if (i % 2 == 0) {
	//// ret[1].addRow(src.getTable()[i]);
	//// } else {
	//// ret[0].addRow(src.getTable()[i]);
	//// }
	//
	//
	// }
	//
	// return ret;
	//
	// }

	public static ModifyTable[] separateExtr(ModifyTable src) {

		ModifyTable[] ret = new ModifyTable[2];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new TwoColTable(new String[] { src.getHeaders()[0], src.getHeaders()[1] + i });
		}

		for (int i = 0; i < src.getTable().length; i++) {

			if (i > 0 && i < src.getTable().length - 1) {
				double a = calcA(src, i - 1, i + 1);
				double b = calcB(src, i - 1, a);
				double y = ((a * src.getTable()[i][0]) + b);
				if (i % 2 == 0) {
					ret[0].addRow(new double[] { src.getTable()[i][0], y });
				} else {
					ret[1].addRow(new double[] { src.getTable()[i][0], y });
				}
			}
			if (i % 2 == 0) {
				ret[1].addRow(src.getTable()[i]);
			} else {
				ret[0].addRow(src.getTable()[i]);
			}

		}

		return ret;

	}

	public static ModifyTable[] sort(ModifyTable m1, ModifyTable m2) {
		double sum1 = 0;
		for (double d : m1.getTable()[1]) {
			sum1 += d;
		}
		double sum2 = 0;
		for (double d : m2.getTable()[1]) {
			sum2 += d;
		}
		if (sum1 < sum2)
			return new ModifyTable[] { m1, m2 };
		else
			return new ModifyTable[] { m2, m1 };
	}

	
	public static Table merge(ModifyTable m1, ModifyTable m2, String [] headers) {
		
		Table ret = new SimpleTable(headers);
		ModifyTable[] s = sort(m1,m2);
		
		for( double [] k1 : s[0].getTable()){
			for(double [] k2 : s[1].getTable()){
				if(k1[0]==k2[0]){
					ret.addRow(new double[]{k1[0],k1[1],k2[1]});
				}
			}
		}
		
		return ret;
	}
	
	
	// public static void getSpline(ModifyTable dst, ModifyTable src, int coll)
	// {
	//
	// sort(dst, 0);
	// sort(src, 0);
	//
	// // double[] result = new double[table.length];
	// //
	// // int pos = 0;
	// // int from = spline[pos];
	// // int to = spline[pos];
	// // double coef = 0;
	// //
	// // for (int i = from; i <= spline[spline.length - 1]; i++) {
	// // if (to < i) {
	// // from = to;
	// // to = spline[++pos];
	// // coef = (table[to][coll] - table[from][coll]) / (to - from);
	// // }
	// // result[i] = table[from][coll] + (coef * (i - from));
	// // }
	//
	// int from = 0;
	// int to = 1;
	//
	// double a = calcA(src, from, to);
	// double b = calcB(src, from, a);
	//
	// // double y = a*x+b;
	//
	// // boolean flag = false;
	//
	// System.out.println(src.getRow(from)[0]);
	// System.out.println(src.getRow(to)[0]);
	// System.out.println(src.getRow(to + 1)[0]);
	//
	// for (int i = 0; i < dst.getRowCount(); i++) {
	//
	// if (dst.getRow(i)[0] >= src.getRow(from)[0] && dst.getRow(i)[0] <
	// src.getRow(to)[0]) {
	// System.out.println(i);
	// // flag = true;
	//
	// System.out.println(dst.getRow(i)[0] + " " + ((a * dst.getRow(i)[0]) +
	// b));
	//
	// } else if (dst.getRow(i)[0] < src.getRow(from)[0]) {
	//
	// System.out.println(3);
	// // flag = false;
	// continue;
	// } else if (dst.getRow(i)[0] >= src.getRow(to)[0]) {
	// System.out.println(4);
	// from++;
	// to++;
	// if (to < src.getRowCount()) {
	// a = calcA(src, from, to);
	// b = calcB(src, from, a);
	// System.out.println(dst.getRow(i)[0] + " " + ((a * dst.getRow(i)[0]) +
	// b));
	// }
	//
	// // flag = false;
	// continue;
	// }
	// }
	//
	// }

	private static double calcB(Table src, int from, double a) {
		double b = coefB(src.getRow(from)[0], src.getRow(from)[1], a);
		return b;
	}

	private static double calcA(Table src, int from, int to) {
		double a = coefA(src.getRow(from)[0], src.getRow(to)[0], src.getRow(from)[1], src.getRow(to)[1]);
		return a;
	}

	private static double coefA(double x1, double x2, double y1, double y2) {
		return (y2 - y1) / (x2 - x1);
	}

	private static double coefB(double x1, double y1, double a) {
		return (y1 - (a * x1));
	}

	public static void main(String[] args) {
		TwoColTable t = new TwoColTable(new double[][] { new double[] { 4, 1 }, new double[] { 3, 1 },
				new double[] { 1, 1 }, new double[] { 2, 1 } }, new String[] { "z", "v" });
		// System.out.println(Arrays.deepToString(t.getTable()));

		// sort(t, 0);

		// System.out.println(Arrays.deepToString(t.getTable()));
		// System.out.println(Arrays.deepToString(separate(t,
		// 2)[1].getTable()));

		// getSpline(t,
		// new TwoColTable(new double[][] { new double[] { 4, 1 }, new double[]
		// { 3, 1 }, new double[] { 2, 1 } },
		// new String[] { "z", "v" }),
		// 5);

	}
}

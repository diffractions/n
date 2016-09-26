package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Crossing {
	double range;

	public Crossing(int tyu) {
		super();
		this.range = (double) tyu / 100;

	};

	private double height(double p1x, double p1y, double p2x, double p2y,
			double p3x, double p3y) {
		double a = Math.sqrt((p1x - p2x) * (p1x - p2x) + (p1y - p2y)
				* (p1y - p2y));
		double b = Math.sqrt((p1x - p3x) * (p1x - p3x) + (p1y - p3y)
				* (p1y - p3y));
		double c = Math.sqrt((p3x - p2x) * (p3x - p2x) + (p3y - p2y)
				* (p3y - p2y));

		double p = (a + b + c) / 2;

		double h = 2 * Math.sqrt(p * (p - a) * (p - b) * (p - c)) / a;

		h = h < 1E-8 ? 0 : h;

		// System.out.println("a = " + a + "\nb = " + b +"\nc = " + c);
		return h;
	}

	public double[][] getExtr(double[][] table) {

		Collection<Integer> extr = extr(table, null, 0, table.length - 1, 1);
		// System.out.println(extr);
		extr.add(table.length - 1);

		double[][] res = new double[extr.size()][];
		int p = 0;
		for (int i : extr) {
			res[p++] = table[i];
		}
		return res;
		
		
	}
	
	public  int[][] separateExtr(int[] extrs, int   length) {
		int[][] extr = new int[2][((extrs.length + 1) / 2)];

		int p = 0;
		int n = 0;
		for (int t : extrs) {
			if (0 == n) {
				n = 1;
				extr[0][p] = t;
			} else if (1 == n) {
				n = 0;
				extr[1][p++] = t;
			}
		}

//		System.out.println(extr);
//		System.out.println(Arrays.deepToString(res));

		if (extr[1][extr[1].length-1] == 0){
//			if (((extr.size() + 1) / 2) >= 4){
//				
//				double a = (table[res[1][res[1].length-2]][coll]-table[res[1][res[1].length-3]][coll])/(table[res[1][res[1].length-2]][0]-table[res[1][res[1].length-3]][0]);
//				double b = table[res[1][res[1].length-2]][coll]-(a*table[res[1][res[1].length-2]][0]);
//				
//			}
//			else
//		
			extr[1][extr[1].length-1] = length - 1;
		}
		return extr;
	}

	public int[] getExtr(double[][] table, int coll) {
		List<Integer> extr = extr(table, null, 0, table.length - 1, coll);
		extr.add(table.length - 1);
		Collections.sort(extr);
		int[] res = new int[extr.size()];
		int p = 0;
		for (int i : extr) {
			res[p++] = i;
		}

		return res;
	}

	private List<Integer> extr(double[][] table, Boolean mark, int start,
			int end, int coll) {
		List<Integer> res = null;
		int extr = getExtr(table, start, end, coll);
		// System.out.println("extr = " + extr + " start = " + start + " end = "
		// + end);

		if (extr != -1) {
			res = new ArrayList<>();
			if (mark == null) {
				res.add(0);
				mark = getMark(table[start][0], table[start][coll],
						table[end][0], table[end][coll], table[extr][0],
						table[extr][coll]);
			}

			res.add(extr);

			Collection<Integer> res1 = extr(table, !mark, start, extr, coll);
			if (res1 != null)
				res.addAll(res1);

			Collection<Integer> res2 = extr(table, !mark, extr, end, coll);
			if (res2 != null)
				res.addAll(res2);

		}

		return res;
	}

	private int getExtr(double[][] table, int start, int end, int coll) {
		int maxPos = -1;
		double maxVal = 0;

		for (int i = start + 1; i < end; i++) {
			double valI = height(table[start][0], table[start][coll],
					table[end][0], table[end][coll], table[i][0],
					table[i][coll]);

			// System.out.println(start + " " + end);
			// System.out.println(table[start][0] + " " + table[start][1] + "\n"
			// + table[end][0] + " " + table[end][1] + "\n" + table[i][0]
			// + " " + table[i][1]);
			// System.out.println(valI);

			if (valI > range) {
				// System.out.println();
				maxVal = maxVal < valI ? valI : maxVal;
				maxPos = maxVal == valI ? i : maxPos;
				// System.out.println(maxVal);
				// System.out.println(maxPos);
				// System.out.println();
			}
		}

		return maxPos;
	}

	private boolean getMark(double px1, double py1, double px2, double py2,
			double px3, double py3) {
		double k = (py2 - py1) / (px2 - px1);
		double b = py1 - (k * px1);
		double yexpect = (k * px3) + b;
		return (py3 - yexpect) > 0;
	}

}

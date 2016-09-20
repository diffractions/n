package service;

import java.util.ArrayList;
import java.util.Collection;

import flanagan.analysis.CurveSmooth;

public class Crossing {
	double range;

	public Crossing(int tyu) {
		super();
		this.range = (double) tyu / 100;
		// System.out.println("range = " + this.range);

	};

	// public static void main(String[] args) {
	//
	// }

	// public static void main(String[] args) {

	// int p1x = 0;
	// int p1y = 0;
	// int p2x = 3;
	// int p2y = 0;

	// int p3x = 2;
	// int p3y = 1;

	// System.out.println(new Crossing(1)
	// .getMark(p1x, p1y, p2x, p2y, p3x, p3y));
	// int p4x = 0;
	// int p4y = 3;

	// System.out.println(new Crossing().crossing(p1x, p1y, p2x, p2y, p3x,
	// p3y, p4x, p4y));
	// System.out.println(new Crossing().crossing(0.3, 0.05, 0.6, 0.1, 0.5,
	// 0.15, 0.4, 0.1));

	// double[][] d = new double[][] { new double[] { 0.0, 0.0 },
	// new double[] { 0.1, 0.05 }, new double[] { 0.2, 0.1 },
	// new double[] { 0.3, 0.05 }, new double[] { 0.4, 0.1 },
	// new double[] { 0.5, 0.15 }, new double[] { 0.6, 0.1 } };

	// System.out.println(Arrays.deepToString(new Crossing(0).getExtr(d)));
	// System.out.println(Arrays.deepToString(new
	// Crossing().findMinExtr(d)));

	// System.out.println(new Crossing().height(p1x, p1y, p2x, p2y, p3x,
	// p3y));

	// }

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



		Collection<Integer> extr = extr(table, null, 0, table.length - 1);
		// System.out.println(extr);
		extr.add(table.length - 1);

		double[][] res = new double[extr.size()][];
		int p = 0;
		for (int i : extr) {
			res[p++] = table[i];
		}
		return res;
	}

	private Collection<Integer> extr(double[][] table, Boolean mark, int start,
			int end) {
		Collection<Integer> res = null;
		int extr = getExtr(table, start, end);
		// System.out.println("extr = " + extr + " start = " + start + " end = "
		// + end);

		if (extr != -1) {
			res = new ArrayList<>();
			if (mark == null) {
				res.add(0);
				mark = getMark(table[start][0], table[start][1], table[end][0],
						table[end][1], table[extr][0], table[extr][1]);
			}

			res.add(extr);

			Collection<Integer> res1 = extr(table, !mark, start, extr);
			if (res1 != null)
				res.addAll(res1);

			Collection<Integer> res2 = extr(table, !mark, extr, end);
			if (res2 != null)
				res.addAll(res2);

		}

		return res;
	}

	private int getExtr(double[][] table, int start, int end) {
		int maxPos = -1;
		double maxVal = 0;

		for (int i = start + 1; i < end; i++) {
			double valI = height(table[start][0], table[start][1],
					table[end][0], table[end][1], table[i][0], table[i][1]);

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

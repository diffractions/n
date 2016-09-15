package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Crossing {
	public static void main(String[] args) {

		// int p1x = 0;
		// int p1y = 0;
		// int p2x = 2;
		// int p2y = 2;
		//
		// int p3x = 1;
		// int p3y = 0;
		// int p4x = 0;
		// int p4y = 3;
		// System.out.println(new Crossing().crossing(p1x, p1y, p2x, p2y, p3x,
		// p3y, p4x, p4y));
		// System.out.println(new Crossing().crossing(0.3, 0.05, 0.6, 0.1, 0.5,
		// 0.15, 0.4, 0.1));

		double[][] d = new double[][] { new double[] { 0.0, 0.0 },
				new double[] { 0.1, 0.05 }, new double[] { 0.2, 0.1 },
				new double[] { 0.3, 0.05 }, new double[] { 0.4, 0.1 },
				new double[] { 0.5, 0.15 }, new double[] { 0.6, 0.1 } };
		System.out.println(Arrays.deepToString(new Crossing().findMinExtr(d)));

	}

	private double[][] findMinExtr(double[][] table) {

		if (table != null && table[0] != null) {
			ArrayList<double[]> minExtr = new ArrayList<>();
			double[] start = table[0];
			double[] lastpoint = start;
			int lastPos = 0;
			if (start[1] < table[1][1]) {
				minExtr.add(start);
			}
			
			for (int i = 1; i < table.length; i++) {

				// System.out.println("i = " + i + "; last = " + lastPos);
				for (int y = i - 1; y > lastPos + 1; y--) {
					// System.out.println("y = "
					// + y
					// + "; "
					// + crossing(lastpoint[0], lastpoint[1], table[i][0],
					// table[i][1], table[y][0], table[y][1],
					// table[y - 1][0], table[y - 1][1]));
					//
					// System.out
					// .println(lastpoint[0] + "  " + lastpoint[1]
					// + " -> " + table[i][0] + "  " + table[i][1]
					// + " <-> " + table[y][0] + "  "
					// + table[y][1] + " -> " + table[y - 1][0]
					// + "  " + table[y - 1][1]);

					if (crossing(lastpoint[0], lastpoint[1], table[i][0],
							table[i][1], table[y][0], table[y][1],
							table[y - 1][0], table[y - 1][1])) {
						lastpoint = table[i - 1];
						minExtr.add(table[i - 1]);
						lastPos = i - 1;
						break;
					}
				}
				// System.out.println();
			}

			if (table[table.length - 1][1] < table[table.length - 2][1]) {
				minExtr.add(table[table.length - 1]);
			}
			double[][] df = new double[minExtr.size()][];
			int s = 0;
			for (double[] g : minExtr) {
				df[s++] = g;
				// System.out.println(Arrays.toString(g));
			}

			return df;
		}
		return null;
	}

	private boolean crossing(double p1x, double p1y, double p2x, double p2y,
			double p3x, double p3y, double p4x, double p4y) {
		double ua = (((p4x - p3x) * (p1y - p3y)) - ((p4y - p3y) * (p1x - p3x)))
				/ (((p4y - p3y) * (p2x - p1x)) - ((p4x - p3x) * (p2y - p1y)));
		double ub = (((p2x - p1x) * (p1y - p3y)) - ((p2y - p1y) * (p1x - p3x)))
				/ (((p4y - p3y) * (p2x - p1x)) - ((p4x - p3x) * (p2y - p1y)));

		// BigDecimal ua1 = new BigDecimal(p4x)
		// .subtract(new BigDecimal(p3x))
		// .multiply(new BigDecimal(p1y).subtract(new BigDecimal(p3y)))
		// .subtract(
		//
		// new BigDecimal(p4y).subtract(new BigDecimal(p3y))
		// .multiply(
		// new BigDecimal(p1x)
		// .subtract(new BigDecimal(p3x))));
		//
		//
		// BigDecimal ua2 = new BigDecimal(p4y)
		// .subtract(new BigDecimal(p3y))
		// .multiply(new BigDecimal(p2x).subtract(new BigDecimal(p1x)))
		// .subtract(
		//
		// new BigDecimal(p4x).subtract(new BigDecimal(p3x))
		// .multiply(
		// new BigDecimal(p2y)
		// .subtract(new BigDecimal(p1y))));
		// double ua = ua1.doubleValue()/ua2.doubleValue();
		// System.out.println(ua );
		// System.out.println(ua1);
		// System.out.println(ua2);
		// System.out.println(ua1.doubleValue()/ua2.doubleValue());

		if (ua < 1e-10)
			ua = 0;
		if (ub < 1e-10)
			ub = 0;

		double x1 = p1x + (ua * (p2x - p1x));
		double y1 = p1y + (ua * (p2y - p1y));
		double x2 = p2x + (ub * (p1x - p2x));
		double y2 = p2y + (ub * (p1y - p2y));
		// System.out.println(new BigDecimal(ua));
		// System.out.println("ua = " + ua + "\nub = " + ub + "\nx1 " + x1
		// + "\ny1 " + y1 + "\nx2 " + x2 + "\ny2 " + y2);
		return !(Double.isNaN(x1) | Double.isNaN(y1))
				&& (x1 != p1x && y1 != p1y) && (x1 != p2x && y1 != p2y)
		// && (x1 != p3x && y1 != p3y) && (x1 != p4x && y1 != p4y)
		;

	}
}

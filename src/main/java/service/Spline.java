//package service;
//
//public class Spline {
//
//	public double[] getSpline(double[][] table, int[] spline, int coll) {
//
//		double[] result = new double[table.length];
//
//		int pos = 0;
//		int from = spline[pos];
//		int to = spline[pos];
//		double coef = 0;
//
//		for (int i = from; i <= spline[spline.length - 1]; i++) {
//			if (to < i) {
//				from = to;
//				to = spline[++pos];
//				coef = (table[to][coll] - table[from][coll]) / (to - from);
//			}
//			result[i] = table[from][coll] + (coef * (i - from));
//		}
//		return result;
//
//	}
//
//	// public static void main(String[] args) {
//	// Spline s = new Spline();
//	// System.out.println(Arrays.toString(s.getSpline(
//	// new double[][] {
//	// new double[] { 1, 1 },
//	// new double[] { 2, 2 },
//	// new double[] { 2, 2 },
//	// new double[] { 3, 3 },
//	// new double[] { 3, 3 },
//	// new double[] { 4, 4 },
//	// new double[] { 5, 5 } },
//	//
//	// new int[] { 0, 2, 6 },
//	// 1)));
//	// }
//
//}
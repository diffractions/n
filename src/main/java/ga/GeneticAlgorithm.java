package ga;

import java.util.Arrays;

public class GeneticAlgorithm {

	public static void main(String[] args) {
		// double xmin = 0;
		// double xmax = 1.5;
		// double nmin = 2;
		// double nmax = 3;
		//
		// for(int in = 0 ; in < 100; in++){
		//
		// }

		// System.out.println();

		// double x = 1.008;
		// double n = 3.040;
		double ns = 1.51;

		// getR(x, n, ns);
		// getR(1.008, 2.996, ns);
		// getR(1.008, 3.040, ns);
		// getR(0.985, 3.116, ns);
		// getR(0.986, 3.160, ns);
		// getR(0.987, 3.200, ns);
		// getR(0.987, 3.238, ns);
		// getR(0.972, 3.291, ns);
		// getR(0.947, 3.346, ns);
		// getR(0.917, 3.386, ns);
		// getR(0.854, 3.441, ns);
		// getR(0.758, 3.489, ns);
		// getR(0.617, 3.538, ns);
		// getR(0.429, 3.585, ns);
		// getR(0.228, 3.632, ns);
		// getR(0.075, 3.682, ns);;

		// getR(0.228, 3.634, ns);
		// getR(0.234, 3.632, ns);
		// getR(1.008, 2.995, ns);

		// double l1 = 875;
		// double l2 = 822;
		// double n1 = 2.398;
		// double n2 = 2.414;
		//
		// getD(l1, l2, n1, n2);

		double t0a = getTa(new double[] { 1280, 1271, 1236, 1277, 1327, 1273,
				1322, 1319, 1059, 1443, 1251, 1252, 1259, 1311, 1246, 1289,
				1219, 1235 });
		System.out.println("t0a = " + t0a);

		double n0 = 2.529;
		double l = 611;

		double m = getM(t0a, n0, l);
		double t1 = getT1(n0, l, m);
		double t1a = getTa(new double[] { 1277, 1277, 1277, 1274, 1277, 1277,
				1279, 1281, 1269, 1276, 1274, 1273, 1273, 1274, 1273, 1274,
				1272 });

		System.out.println("t1a = " + t1a);
		double nref = getNref(t1a, l, m);

		System.out.println("nref = " + nref);

	}

	private static double getTa(double arr[]) {
		double sum = 0;
		double count = 0;
		for (double i : arr) {
			sum += i;
			count++;

		}
		double t0a = sum / count;
		return t0a;
	}

	private static double getNref(double t0, double l, double m) {
		double nref = (m * l) / (4 * t0);
		return nref;
	}

	private static double getT1(double n, double l, double m) {
		System.out.println("t1 = " + (m * l) / (n * 4));
		return (m * l) / (n * 4);
	}

	private static double getM(double meanD, double n, double l) {
		double m0 = 4 * n * meanD / l;

		System.out.println("n = " + n + "\tl = " + l + "\nM0 =" + m0);
		System.out.println("M = " + Math.rint(m0));
		return Math.rint(m0);
	}

	private static void getD(double l1, double l2, double n1, double n2) {
		double d = (l1 * l2) / (4 * (l1 * n2 - l2 * n1));
		System.out.println("L1 = " + l1 + "\t n1 =  " + n1 + "\nL2 = " + l2
				+ "\t n2 =  " + n2 + "\n" + "d = " + d);
	}

	private static void getR(double x, double n, double ns) {
		double a = n - 1;
		double b = n + 1;
		double c = n - ns;
		double d = n + ns;
		double e = n - Math.pow(ns, 2);
		double f = n + Math.pow(ns, 2);
		double g = 64 * ns * Math.pow((ns - 1), 2) * Math.pow(n, 4);

		double Rm = (Math.pow(((a * d) - (b * c * x)), 2) / Math.pow(
				((b * d) - (a * c * x)), 2))
				+ (g * Math.pow((x), 2) / (Math.pow(((b * d) - (a * c * x)), 2) * ((Math
						.pow(b, 3) * f) - (2 * a * b * c * d * x) + (Math.pow(
						a, 3) * e * Math.pow(x, 2)))));

		double RM = (Math.pow(((a * d) + (b * c * x)), 2) / Math.pow(
				((b * d) + (a * c * x)), 2))
				+ (g * Math.pow((x), 2) / (Math.pow(((b * d) + (a * c * x)), 2) * ((Math
						.pow(b, 3) * f) + (2 * a * b * c * d * x) + (Math.pow(
						a, 3) * e * Math.pow(x, 2)))));

		// System.out.println("x = " + x + "\nn = " + n + "\nns = " + ns
		// + "\na = " + a + "\nb = " + b + "\nc = " + c + "\nd = " + d
		// + "\ne = " + e + "\nf = " + f + "\ng = " + g + "\nRm = " + Rm
		// + "\nRM = " + RM);

		System.out.println("x = " + x + "\nn = " + n + "\nRm = " + Rm
				+ "\nRM = " + RM + "\n-----------");
	}
}

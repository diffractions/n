package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IndFactory {

	public static Ind getInd(int[] n, int[] x, double xmin, double xmax,
			double nmin, double nmax, double ns, double RMm, double Rmm) {

		double xm1 = m(xmin, xmax, n);
		double nm1 = m(nmin, nmax, x);
		double fit = setFitness(xm1, nm1, ns, RMm, Rmm);

		return new Ind(n, x, xm1, nm1, fit);
	}

	public static Ind genRand(int size, double xmin, double xmax, double nmin,
			double nmax, double ns, double RMm, double Rmm, Random rand) {

		int arrx[] = new int[size];
		for (int i = 0; i < size; i++) {
			arrx[i] = (int) Math.rint(rand.nextDouble());
		}

		int arrn[] = new int[size];
		for (int i = 0; i < size; i++) {
			arrn[i] = (int) Math.rint(rand.nextDouble());
		}

		return getInd(arrn, arrx, xmin, xmax, nmin, nmax, ns, RMm, Rmm);

	}

	public static Ind invert(Ind ind, double xmin, double xmax, double nmin,
			double nmax, double ns, double RMm, double Rmm) {

		int arrn[] = new int[ind.getArrn().length];
		int arrx[] = new int[ind.getArrn().length];

		for (int i = 0; i < arrn.length; i++) {
			arrn[i] = ind.getArrn()[i] == 0 ? 1 : 0;
		}
		for (int i = 0; i < arrx.length; i++) {
			arrx[i] = ind.getArrx()[i] == 0 ? 1 : 0;
		}

		return getInd(arrn, arrx, xmin, xmax, nmin, nmax, ns, RMm, Rmm);

	}

	private static double setFitness(double xm1, double nm1, double ns,
			double RMm, double Rmm) {
		return Math.abs(getRM(xm1, nm1, ns) - RMm)
				+ Math.abs(getRm(xm1, nm1, ns) - Rmm);
	}

	private static double getRm(double x, double n, double ns) {
		double a = n - 1;
		double b = n + 1;
		double c = n - ns;
		double d = n + ns;
		double e = n - Math.pow(ns, 2);
		double f = n + Math.pow(ns, 2);
		double g = 64 * ns * Math.pow((ns - 1), 2) * Math.pow(n, 4);

		return (Math.pow(((a * d) - (b * c * x)), 2) / Math.pow(((b * d) - (a
				* c * x)), 2))
				+ (g * Math.pow((x), 2) / (Math.pow(((b * d) - (a * c * x)), 2) * ((Math
						.pow(b, 3) * f) - (2 * a * b * c * d * x) + (Math.pow(
						a, 3) * e * Math.pow(x, 2)))));

	}

	private static double getRM(double x, double n, double ns) {
		double a = n - 1;
		double b = n + 1;
		double c = n - ns;
		double d = n + ns;
		double e = n - Math.pow(ns, 2);
		double f = n + Math.pow(ns, 2);
		double g = 64 * ns * Math.pow((ns - 1), 2) * Math.pow(n, 4);

		return (Math.pow(((a * d) + (b * c * x)), 2) / Math.pow(((b * d) + (a
				* c * x)), 2))
				+ (g * Math.pow((x), 2) / (Math.pow(((b * d) + (a * c * x)), 2) * ((Math
						.pow(b, 3) * f) + (2 * a * b * c * d * x) + (Math.pow(
						a, 3) * e * Math.pow(x, 2)))));

	}

	private static double m(double min, double max, int[] ind) {
		int N = ind.length - 1;
		return min + (((max - min) / (Math.pow(2, N) - 1)) * (sum(N, ind)));
	}

	static double sum(int N, int[] arr) {
		double sum = 0;
		for (int i = 0; i <= N; i++) {
			sum += (Math.pow(2, i) * arr[i]);
		}
		return sum;

	}

	public static Ind crossover(ArrayList<Ind> ind, int pos1, int pos2,
			int pos, double xmin, double xmax, double nmin, double nmax,
			double ns, double RMm, double Rmm) {

		int[] arrx1 = Arrays.copyOf(ind.get(pos1).getArrx(), ind.get(pos1)
				.getArrx().length);
		int[] arrn1 = Arrays.copyOf(ind.get(pos1).getArrn(), ind.get(pos1)
				.getArrn().length);
		int[] arrx2 = Arrays.copyOf(ind.get(pos2).getArrx(), ind.get(pos2)
				.getArrx().length);
		int[] arrn2 = Arrays.copyOf(ind.get(pos2).getArrn(), ind.get(pos2)
				.getArrn().length);

		return getInd(generateArr(arrn1, arrn2, pos),
				generateArr(arrx1, arrx2, pos), xmin, xmax, nmin, nmax, ns,
				RMm, Rmm);

	}

	private static int[] generateArr(int[] arr1, int[] arr2, int pos) {
		int arr[] = new int[arr1.length];

		for (int i = 0; i <= pos; i++) {
			arr[i] = arr1[i];
		}

		for (int i = pos; i <= arr.length; i++) {
			arr[i - 1] = arr2[i - 1];
		}
		return arr;
	}

}

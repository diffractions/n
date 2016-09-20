package service;

import java.util.Arrays;

public class Fitting {

	int colNumb = 1;

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(new Fitting(4)
				.getFitt(new double[][] { new double[] { 0, 0.1 },
						new double[] { 1, 0.4 }, new double[] { 2, 0.2 },
						new double[] { 3, 0.4 }, new double[] { 4, 0.5 } })));
	}

	private final int ave;

	/**
	 * @param ave
	 */
	public Fitting(int ave) {
		this.ave = ave;
	}

	public double[][] getFitt(double[][] table) {
		double[][] res = new double[table.length][2];

		for (int pos = 0; pos < res.length; pos++) {
			res[pos][0] = table[pos][0];
			res[pos][colNumb] = getSum(table, pos);

		}

		return res;
	}

	private double getSum(double[][] table, int pos) {
		double sum = 0;
		int count = (1 + (ave * 2));

		// System.out.print("pos = " + pos);
		if ((table.length - ave) <= pos && pos - ave >= 0) {

			for (int i = pos - ave; i < table.length; i++) {
				sum += table[i][colNumb];
			}
			count = 1 + (table.length - pos) + ave;
			// System.out.println("; n = 3, sum " + sum + "; count = " + count);

		} else if (pos < ave && pos + ave < table.length) {

			for (int i = 0; i < pos + ave; i++) {
				sum += table[i][colNumb];
			}
			count = 1 + (pos + ave);

			// System.out.println("; n = 1, sum " + sum + "; count = " + count);
		} else if (pos >= ave && (table.length - ave) > pos) {

			for (int i = 1; i <= ave; i++) {
				sum += table[pos - i][colNumb];
				sum += table[pos + i][colNumb];
			}
			// System.out.println("; n = 2, sum " + sum + "; count = " + count);
		} else {
			count = table.length + 1;

			for (int i = 0; i < table.length; i++) {
				sum += table[i][colNumb];
			}

			System.out.println("; n = 4, sum " + sum + "; count = " + count);
		}

		sum += (table[pos][colNumb]);
		return (sum / count);
	}

}

package service;

public class Fitting {

	int colNumb = 1;

	public static void main(String[] args) {
		System.out.println(Math.pow(3, 2));
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

		// System.arraycopy(table, 0, ret, 0, table.length-1);
		// for (int i = (ave * 2); i <= table.length - (ave * 2) - 1; i++) {
		// double sum = getSum(table, i);
		// ret[i - (ave * 2)][0] = table[i][0];
		// ret[i - (ave * 2)][tableCol] = sum;
		// System.out.println(sum);
		// System.out.println();
		// }

		 for(int pos = 0; pos< ave; pos++){
		 res[pos][0]=table[pos][0];
		 res[pos][colNumb]=table[pos][colNumb];
		
		 res[res.length-pos-1][0]=table[res.length-1-pos][0];
		 res[res.length-pos-1][colNumb]=table[res.length-1-pos][colNumb];
		 }

		for (int pos = ave; pos < res.length-ave; pos++) {
			res[pos][0] = table[pos][0];
			res[pos][colNumb] = getSum(table, pos);

		}

		return res;
	}

	private double getSum(double[][] table, int pos) {
		double sum = 0;
//		int mult = 1;
//		System.out.println("+++++++++");

//		System.out.println("pos = " + pos);
		for (int i = 1; i <= ave; i++) {
			sum += table[pos - i][colNumb]  ;
//			sum += table[pos - i][colNumb] * mult;
//			System.out.println("pos-i = " + (pos - i));
//			System.out.println("pos + i = " + (pos + i));
			sum += table[pos + i][colNumb]  ;
//			sum += table[pos + i][colNumb] * mult++;

		}
//		if (ave == 1) {
//			System.out.println(1);
			sum += (table[pos][colNumb]);
//		} else {

//			System.out.println(0);
//			sum += (table[pos][colNumb]  );
//		}

//		System.out.println("sum = " + sum);


//		System.out.println("------------");
		return (sum /(1+(ave*2)));
	}

}

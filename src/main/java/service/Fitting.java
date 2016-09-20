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

		/*
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
*/

		for (int pos = 0; pos < res.length; pos++) {
			res[pos][0] = table[pos][0];
			res[pos][colNumb] = getSum(table, pos);

		}

		return res;
	}
/*
	private double getSum2 (double [][] table, int pos){
		double sum = 0;
		
		if(pos>=ave && (table.length-ave)>pos)
		for (int i = 1; i <= ave; i++) {
			sum += table[pos - i][colNumb]  ;
			sum += table[pos + i][colNumb]  ;

		}
else if((table.length-ave)<=pos){}
else if((table.length-ave)<=pos)
	}

		private double meanMin(double [][] table, int pos){
		double sum = 0;
		for(int i = 0; i<= pos+ave; i++){
			sum+=table[i][colNumb];
		}
		return sum/(double)(pos+ave+1);
	}
	}

		private double meanMax(double [][] table, int pos){
		double sum = 0;
		for(int i = pos-ave; i< table.length; i++){
			sum+=table[i][colNumb];
		}
		return sum/(double)(table.length-(pos+ave+1));
	}*/
	private double getSum(double[][] table, int pos) {
 		double sum = 0;
		
		if(pos>=ave && (table.length-ave)>pos)
		for (int i = 1; i <= ave; i++) {
			sum += table[pos - i][colNumb]  ;
			sum += table[pos + i][colNumb]  ;

		}

		else if(pos<ave)
		for (int i = pos; i < pos+ave; i++) {
			sum += mean(table, 0, i);
			sum += table[i][colNumb]  ;
		}

//		else if((table.length-ave)<pos)
//		for (int i = pos; i < table.length; i++) {
//			sum += mean(table, i, table.length-i-1);
//			sum += table[i][colNumb]  ;
//		}

		else if((table.length-ave)<=pos)
		for (int i = pos; i > pos-ave; i--) {
			
		//System.out.println("========");
		//System.out.println(pos + " " + i);
			sum += mean(table, pos, table.length-pos-1);
			sum += table[i][colNumb]  ;
		}


		//for (int i = pos-ave; i < pos; i++) {
		//	sum += mean(table, pos, table.length-pos-1);
		//	sum += table[pos-i][colNumb]  ;
		//}

		sum += (table[pos][colNumb]);

		//System.out.println("========");
		return (sum /(1+(ave*2)));
	}


	private double mean(double [][] table, int pos, int count){
		double sum = 0;
		//System.out.println("--------");
		//System.out.println("count = " + count);
		//System.out.println("pos1 = " + pos);
		//System.out.println("pos2 = " + (pos+ count));
		for(int i = pos; i<= pos+count; i++){
			sum+=table[i][colNumb];
		}
		return sum/(double)(count+1);
	}

/*

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
*/
}

package  service;

public class Spline{

	public double[] getSpline(double[][] table, int[] spline, int coll){
	
		double[] result = new double[table.length];
		
		int from = 0;
		int pos = 0;
		int to = spline[pos];
		double coef = (table[to][coll]-table[from][coll])/(to-from);


		for (int i = 1; i < table.length; i++){
			if(to <= i){
				from= to;
				to=spline[pos++];
				coef = (table[to][coll]-table[from][coll])/(to-from);	
			}
			result[i]  = result[from]+(coef*(i-from));
		}
		return result;
	
	}


}
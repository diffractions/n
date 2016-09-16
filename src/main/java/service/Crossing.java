package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Crossing {
	int tyu;
	public Crossing(int tyu){super();this.tyu=tyu;};
	public static void main(String[] args) {

		 int p1x = 0;
		 int p1y = 0;
		 int p2x = 3;
		 int p2y = 0;
		
		 int p3x = 2;
		 int p3y = 1;

		 int p4x = 0;
		 int p4y = 3;

		// System.out.println(new Crossing().crossing(p1x, p1y, p2x, p2y, p3x,
		// p3y, p4x, p4y));
		// System.out.println(new Crossing().crossing(0.3, 0.05, 0.6, 0.1, 0.5,
		// 0.15, 0.4, 0.1));

		double[][] d = new double[][] { new double[] { 0.0, 0.0 },
				new double[] { 0.1, 0.05 }, new double[] { 0.2, 0.1 },
				new double[] { 0.3, 0.05 }, new double[] { 0.4, 0.1 },
				new double[] { 0.5, 0.15 }, new double[] { 0.6, 0.1 } };
		//System.out.println(Arrays.deepToString(new Crossing().findMinExtr(d)));

		//System.out.println(new Crossing().height(p1x, p1y, p2x, p2y, p3x, p3y));

	}

	public double[][][] findMinExtr(double[][] table) {

		if (table != null && table[0] != null) {
			ArrayList<double[]> minExtr = new ArrayList<>();
			ArrayList<Integer> minPos = new ArrayList<>();
			ArrayList<double[]> maxExtr = new ArrayList<>();

			double[] start = table[0];
			double[] lastpoint = start;
			minExtr.add(start);
			minPos.add(0);
			minExtr.add(start);
			minPos.add(0);
			int lastPos = 0;
/*			
			for (int i = 1; i < table.length; i++) {
				for (int y = i - 1; y > lastPos + 1; y--) {
					if (crossing(lastpoint[0], lastpoint[1], table[i][0],
							table[i][1], table[y][0], table[y][1],
							table[y - 1][0], table[y - 1][1])) {
						lastpoint = table[i - 1];
						minExtr.add(table[i - 1]);
						minPos.add(i - 1);
						lastPos = i - 1;
						break;
					}
				}
			}
*/
			for (int i = 1; i < table.length; i++) {
				for (int y = i - 1; y > lastPos + 1; y--) {
					if (crossing(lastpoint[0], lastpoint[1], table[i][0],
							table[i][1], table[y][0], table[y][1],
							table[y - 1][0], table[y - 1][1])) {

						lastpoint = table[i-1];
						minExtr.add(table[i - 1]);
						minPos.add(i - 1);
						lastPos = i - 1;
						break;
					}
					minExtr.set(minExtr.size()-1,table[i]);
					minPos.set(minPos.size()-1,i );
					//lastpoint = table[i];
				}
			}



		
			for(int o = 1; o<minPos.size(); o++){
				int a = minPos.get(o-1);
				int b = minPos.get(o);
				double max = 0;
				int pos = o;
				for(int u = a+1; u<b; u++){
					double nm = height(table[a][0],table[a][1],table[b][0],table[b][1],table[u][0],table[u][1]);
					max = max<nm?nm:max;
					pos = max==nm ? u: pos;
				}
				maxExtr.add(table[pos]);
				//System.out.println(max);
				//System.out.println(pos);
			}
			

			double[][] dn = new double[maxExtr.size()][];
			int sn = 0;
			for (double[] g : maxExtr) {
				dn[sn++] = g;
				// System.out.println(Arrays.toString(g));
			}
			

			double[][] df = new double[minExtr.size()][];
			int s = 0;
			for (double[] g : minExtr) {
				df[s++] = g;
				// System.out.println(Arrays.toString(g));
			}

			return new double[][][]{df,dn};
			//return new double[][][]{df};
		}
		return null;
	}

	private boolean crossing(double p1x, double p1y, double p2x, double p2y,
			double p3x, double p3y, double p4x, double p4y) {

		if(
			Math.sqrt((((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y))))/tyu<Math.sqrt((((p3x-p4x)*(p3x-p4x))+((p3y-p4y)*(p3y-p4y))))
		) return false;




		double ua = (((p4x - p3x) * (p1y - p3y)) - ((p4y - p3y) * (p1x - p3x)))
				/ (((p4y - p3y) * (p2x - p1x)) - ((p4x - p3x) * (p2y - p1y)));
		double ub = (((p2x - p1x) * (p1y - p3y)) - ((p2y - p1y) * (p1x - p3x)))
				/ (((p4y - p3y) * (p2x - p1x)) - ((p4x - p3x) * (p2y - p1y)));

		//if (ua < 1e-10)
		//	ua = 0;
		//if (ub < 1e-10)
		//	ub = 0;

		double x1 = p1x + (ua * (p2x - p1x));
		double y1 = p1y + (ua * (p2y - p1y));
		double x2 = p2x + (ub * (p1x - p2x));
		double y2 = p2y + (ub * (p1y - p2y));
		 System.out.println("ua = " + ua + "\nub = " + ub + "\nx1 " + x1
		 + "\ny1 " + y1 + "\nx2 " + x2 + "\ny2 " + y2);
		return !(Double.isNaN(x1) | Double.isNaN(y1))
				&& (x1 != p1x && y1 != p1y) && (x1 != p2x && y1 != p2y)
		//&& (x1 != p3x && y1 != p3y) && (x1 != p4x && y1 != p4y)
		
		;

	}
	private double height (double p1x, double p1y, double p2x, double p2y,
			double p3x, double p3y){
		double a = Math.sqrt((p1x-p2x)*(p1x-p2x)+(p1y-p2y)*(p1y-p2y));
		double b = Math.sqrt((p1x-p3x)*(p1x-p3x)+(p1y-p3y)*(p1y-p3y));
		double c = Math.sqrt((p3x-p2x)*(p3x-p2x)+(p3y-p2y)*(p3y-p2y));

		double p = (a+b+c)/2;

		double h = 2*Math.sqrt(p*(p-a)*(p-b)*(p-c))/a;

		//System.out.println("a = " + a + "\nb = " + b +"\nc = " + c);
		return h;
	}

/*	private double height (double p1x, double p1y, double p2x, double p2y,
			double p3x, double p3y){
		//double a = Math.sqrt((p1x-p2x)*(p1x-p2x)+(p1y-p2y)*(p1y-p2y));
		//double b = Math.sqrt((p1x-p3x)*(p1x-p3x)+(p1y-p3y)*(p1y-p3y));
		//double c = Math.sqrt((p3x-p2x)*(p3x-p2x)+(p3y-p2y)*(p3y-p2y));

		//double p = (a+b+c)/2;

		//double h = 2*Math.sqrt(p*(p-a)*(p-b)*(p-c))/a;

		//System.out.println("a = " + a + "\nb = " + b +"\nc = " + c);
		return Math.sqrt(((p1x-p3x)*(p1x-p3x))+((p1x-p3x)*(p1x-p3x)))+Math.sqrt(((p2x-p3x)*(p2x-p3x))+((p2x-p3x)*(p2x-p3x)));

		
	}*/

	public double [][][] finds(double d [][]){
		int start = 0;
		int end = d.length;
		
		ArrayList<Integer> result = new ArrayList<>();
		Integer i;
		if((i=extr(d, start,end))!=null){
			result.add(i);
		}	

	}




	public Integer extr (double d [][], int start, int end){

		double max = 0;
		int res = 0;
		for(int i = start+1; i< end-1; i++){
			int maxi = height(d[start][0],d[start][1],d[end-1][0],d[end-1][1],d[i][0],d[i][1]);
			max = max<maxi? maxi : max;
			res = max<maxi? i : res;
		}
		if(max>tyu)return res;
		else return null;		
	}


























	public double [][][] finds(double d [][], int start, int end){
		int start = 0;
		int end = d.length;
		ArrayList<Integer> result = new ArrayList<>();
		double max = 0;
		int res = 0;
		for(int i = start+1; i< end-1; i++){
			int maxi = height(d[start][0],d[start][1],d[end-1][0],d[end-1][1],d[i][0],d[i][1]);
			max = max<maxi? maxi : max;
			res = max<maxi? i : res;
		}
		if(max>tyu)result.add(res);
		
	}
	
	


}

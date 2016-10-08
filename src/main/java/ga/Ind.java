package ga;

import java.util.Arrays;
import java.util.Comparator;
 
public class Ind implements Comparator<Ind>, Comparable<Ind> {
//	 @Override
//	 public String toString() {
//	 return "Ind [arrn=" + Arrays.toString(arrn) + ", arrx="
//	 + Arrays.toString(arrx) + ", fitness =" + fitness + "]";
//	 }
 
	 @Override
	 public String toString() {
	 return "[\tfitness=\t" + fitness + "\t,\tn = \t" + nf + "\t,\tx = \t" + xf + "\t]";
	 }

//	@Override
//	public String toString() {
//		return "[f=" + fitness + "]";
//	}

	private static Ind comp = new Ind();

	public static Comparator<Ind> getComparator() {
		return comp;
	}

	private double xf;
	private double nf;
	private int arrn[];
	private int arrx[];
	private double fitness;

	public Ind(int[] arrn, int[] arrx, double xm1, double nm1, double fitness) {
		this.arrn = arrn;
		this.arrx = arrx;
		this.xf = xm1;
		this.nf = nm1;
		this.fitness = fitness;

	}

	private Ind() {

	}

	public double getXf() {
		return xf;
	}

	public double getNf() {
		return nf;
	}

	@Override
	public int compare(Ind o1, Ind o2) {
		return o1.compareTo(o2);
	}

	@Override
	public int compareTo(Ind o) {

//		System.out.println(this.getFitness() - o.getFitness());
		return this.getFitness() - o.getFitness() == 0.0 ? 0 : this
				.getFitness() - o.getFitness() < 0.0 ? -1 : 1;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Ind)) {
			return false;
		}
		Ind other = (Ind) obj;
		if (!Arrays.equals(arrn, other.arrn)) {
			return false;
		}
		if (!Arrays.equals(arrx, other.arrx)) {
			return false;
		}
		return true;
	}

	public int[] getArrn() {
		return arrn;
	}

	public int[] getArrx() {
		return arrx;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arrn);
		result = prime * result + Arrays.hashCode(arrx);
		return result;
	}

}

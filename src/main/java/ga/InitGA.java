package ga;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
 
import org.apache.log4j.Logger;

public class InitGA {

	final static double[][] k = new double[][] {
			new double[] { 795, 0.07657, 0.3728 },  
//			new double[] { 413.45,	0.155,	0.237}

	};

	public static Logger log = Logger.getLogger("FILE"); 
	public static void main(String[] args) throws InterruptedException {
 
		start(k);

	}






























	public static void start(final double[][] k1, int a, int b) throws InterruptedException {
		log.info("START");
		TreeMap<Double, double[]> m = fillCmap(k1);
		double[][] c = getCarr(m);

		Map<Double, Set<Ind>> res = new HashMap<Double, Set<Ind>>() {
 
			private static final long serialVersionUID = 1L;

			@Override
			public String toString() {
				String ret = "";
				for (Double t : keySet()) {
					ret += (t + "\tRM\t" + printSetR(t) + "\tRm\t" + printSetr(t) + printSet(get(t)) + "\n");
				}
				return ret;
			}

			private String printSet(Set<Ind> s) {
				String ret = "";
				for (Ind f : s) {
					ret += ("\tn\t" + f.getNf() + "\tx\t" + f.getXf() + "\t;\t");
				}
				return ret;
			}

			private double printSetR(double y) {

				for (double[] f : k1) {
					if (f[0] == y)
						return f[2];
				}
				return 0;
			}

			private double printSetr(double y) {

				for (double[] f : k1) {
					if (f[0] == y)
						return f[1];
				}
				return 0;
			}
		};

		while (c.length > 0) {
			Map<Double, Set<Ind>> s_res = GA.start_try(c,a,b);
			res.putAll(s_res);

			m = fillCmap(c);
			for (double i : s_res.keySet())
				m.remove(i);
			c = getCarr(m);
//			System.out.println(res);
			log.info(res);
			log.info("-----------------------");

		}
		System.out.println(res);
	}


	public static void stop(){};













































	public static void start(final double[][] k1) throws InterruptedException {
		log.info("START");
		TreeMap<Double, double[]> m = fillCmap(k1);
		double[][] c = getCarr(m);

		Map<Double, Set<Ind>> res = new HashMap<Double, Set<Ind>>() {
 
			private static final long serialVersionUID = 1L;

			@Override
			public String toString() {
				String ret = "";
				for (Double t : keySet()) {
					ret += (t + "\tRM\t" + printSetR(t) + "\tRm\t" + printSetr(t) + printSet(get(t)) + "\n");
				}
				return ret;
			}

			private String printSet(Set<Ind> s) {
				String ret = "";
				for (Ind f : s) {
					ret += ("\tn\t" + f.getNf() + "\tx\t" + f.getXf() + "\t;\t");
				}
				return ret;
			}

			private double printSetR(double y) {

				for (double[] f : k1) {
					if (f[0] == y)
						return f[2];
				}
				return 0;
			}

			private double printSetr(double y) {

				for (double[] f : k1) {
					if (f[0] == y)
						return f[1];
				}
				return 0;
			}
		};

		while (c.length > 0) {
			Map<Double, Set<Ind>> s_res = GA.start_try(c);
			res.putAll(s_res);

			m = fillCmap(c);
			for (double i : s_res.keySet())
				m.remove(i);
			c = getCarr(m);
//			System.out.println(res);
			log.info(res);
			log.info("-----------------------");

		}
		System.out.println(res);
	}

	private static double[][] getCarr(TreeMap<Double, double[]> m) {
		double[][] c = new double[m.size()][];
		int i = 0;
		for (double[] t : m.values()) {
			c[i] = t;
			i++;
		}
		return c;
	}

	private static TreeMap<Double, double[]> fillCmap(double[][] k) {
		TreeMap<Double, double[]> m = new TreeMap<>();
		for (double[] t : k) {
			m.put( t[0], t);
		}
		return m;
	}

}

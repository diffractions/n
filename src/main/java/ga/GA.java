package ga;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit; 
 
import org.apache.log4j.Logger;

public class GA implements Runnable {

	static double error = 5e-5;

	static boolean isRandom = true;

	static double in_xmin = 0.0;
	static double in_xmax = 1.5;
	static double in_nmin = 1.5;
	static double in_nmax = 4.0;

	double xmin = in_xmin;
	double xmax = in_xmax;
	double nmin = in_nmin;
	double nmax = in_nmax;

	double ns;

	double RMm;
	double Rmm;

	static double best_coef_max = 1.0;
	static double best_coeff_min = 0.1;
	// from 0.1 to 0.111111(1)
	static double best_coeff_impl = 0.1;
	double best_coeff;

	// int init_rand = 41;
	Random rand = new Random(/* init_rand */);

	int arr_length;
	int ind_count;

	int generaredFrom;
	int generaredTo;

	int mutateFrom;
	int mutateTo;

	// >=1
	int crossoverFrom;
	int crossoverTo;
	int crossoverArrPos;

	int mean_to_next;

	ThreadGroup tg;
	SortedSet<Ind> set;
	CountDownLatch cdl;

	public GA(int arr_length, int ind_count, double RMm, double Rmm, double ns,
			ThreadGroup tg, SortedSet<Ind> set, CountDownLatch cdl) {

		this.arr_length = arr_length;
		this.ind_count = ind_count;

		int generared = ind_count / 3;
		this.generaredFrom = ind_count - generared;
		this.generaredTo = ind_count;

		int mutate = ind_count / 3;
		this.mutateFrom = (ind_count - generared - mutate);
		this.mutateTo = ind_count - generared;

		this.crossoverFrom = 2;
		this.crossoverTo = ind_count;
		this.crossoverArrPos = (int) (arr_length / 1.7);

		this.best_coeff = best_coef_max;
		this.set = set;

		this.mean_to_next = (ind_count / 5) + 1;

		this.cdl = cdl;
		this.tg = tg;

		this.RMm = RMm;
		this.Rmm = Rmm;

		this.ns = ns;
	}

	public static Logger log = Logger.getLogger("FLIE");
	public static void main(String[] args) throws InterruptedException {
		


		 double[][] k = new double[][] {
			new double[] { 413.45,	0.155,	0.237}};
		
		start_try(k);

	}



	public static Map<Double, Set<Ind>> start_try(double[][] k, int a, int b)

			throws InterruptedException { 
		int start_arr_length = a;
		int incr_count_arr_length = b;

		Map<Double, Set<Ind>> ret = new HashMap<>();
		
		for (double[] t : k) {
			ConcurrentSkipListSet<Ind> set = new ConcurrentSkipListSet<Ind>();
			ThreadGroup tg = new ThreadGroup("" + t[2] + " " + t[1]);
			CountDownLatch cdl = new CountDownLatch(incr_count_arr_length);

			for (int count = start_arr_length, length = start_arr_length; count <= start_arr_length
					+ incr_count_arr_length - 1
					& length <= start_arr_length + incr_count_arr_length - 1; count++, length++)

				new Thread(tg, new GA(length, count, t[2], t[1], 1.5, tg, set,
						cdl), "TN - " + count).start();

			cdl.await(30, TimeUnit.SECONDS);
			// cdl.await(200, TimeUnit.MILLISECONDS);
			tg.interrupt();
			// System.out.println(t[0] + "\t" + t[1] + " :\t " + set);
			// System.out.println(t[3] + " :\t " + set);
			System.out.println(k.length);
			// set = null;
			tg = null;
			cdl = null;
			if (set.size() > 0) {
				System.out.println(t[0] + " :\t " + set);
				ret.put( t[0], set);
			}
		}

		return ret;
	}

	public static Map<Double, Set<Ind>> start_try(double[][] k)

			throws InterruptedException { 
		int start_arr_length = 5;
		int incr_count_arr_length = 35;

		Map<Double, Set<Ind>> ret = new HashMap<>();
		
		for (double[] t : k) {
			ConcurrentSkipListSet<Ind> set = new ConcurrentSkipListSet<Ind>();
			ThreadGroup tg = new ThreadGroup("" + t[2] + " " + t[1]);
			CountDownLatch cdl = new CountDownLatch(incr_count_arr_length);

			for (int count = start_arr_length, length = start_arr_length; count <= start_arr_length
					+ incr_count_arr_length - 1
					& length <= start_arr_length + incr_count_arr_length - 1; count++, length++)

				new Thread(tg, new GA(length, count, t[2], t[1], 1.5, tg, set,
						cdl), "TN - " + count).start();

			cdl.await(30, TimeUnit.SECONDS);
			// cdl.await(200, TimeUnit.MILLISECONDS);
			tg.interrupt();
			// System.out.println(t[0] + "\t" + t[1] + " :\t " + set);
			// System.out.println(t[3] + " :\t " + set);
			System.out.println(k.length);
			// set = null;
			tg = null;
			cdl = null;
			if (set.size() > 0) {
				System.out.println(t[0] + " :\t " + set);
				ret.put( t[0], set);
			}
		}

		return ret;
	}

	@Override
	public void run() {

		ArrayList<Ind> t = new ArrayList<Ind>();

		for (int r = 0; r < ind_count; r++) {
			Ind s = IndFactory.genRand(arr_length, xmin, xmax, nmin, nmax, ns,
					RMm, Rmm, rand);
			t.add(s);
		}

		l: {
			while (!Thread.currentThread().isInterrupted()) {

				for (int c = 0; c < 100; c++) {

					Collections.sort(t, Ind.getComparator());

					// t.sort(Ind.getComparator());

					if (t.get(0).getFitness() < error) {
						tg.interrupt();
						break l;
					}

					generate(t);
					mutate(t);
					// crossover(t);

				}

				updateBestCoeff(t);

			}
		}

		// System.out.println(ind_count + ", RMm =  " + RMm + ", Rmm  = " + Rmm
		// + ";\t" + t.get(0));

		if (t.get(0).getFitness() < (error * 5) && t.get(0).getXf() < in_xmax
				&& t.get(0).getNf() < in_nmax && t.get(0).getXf() > in_xmin
				&& t.get(0).getNf() > in_nmin)
			set.add(t.get(0));

		cdl.countDown();

	}

	private void generate(ArrayList<Ind> t) {
		for (int i = generaredFrom; i < generaredTo; i++) {

			int sectionElement = i;
			if (isRandom)
				sectionElement = (generaredFrom)
						+ rand.nextInt(generaredTo - generaredFrom);
			t.set(sectionElement, IndFactory.genRand(arr_length, xmin, xmax,
					nmin, nmax, ns, RMm, Rmm, rand));

		}
	}

	private void mutate(ArrayList<Ind> t) {
		for (int i = mutateFrom; i < mutateTo; i++) {
			int mutateElement = i;
			if (isRandom)
				mutateElement = (mutateFrom)
						+ rand.nextInt(mutateTo - mutateFrom);

			t.set(mutateElement, IndFactory.invert(t.get(mutateElement), xmin,
					xmax, nmin, nmax, ns, RMm, Rmm));
		}
	}

	private void crossover(ArrayList<Ind> t) {
		for (int i = crossoverFrom; i < crossoverTo; i++) {
			int crossoverElement = i;
			if (isRandom)
				crossoverElement = (crossoverFrom)
						+ rand.nextInt(crossoverTo - crossoverFrom);

			t.set(crossoverElement, IndFactory.crossover(t,
					crossoverElement - 1, crossoverElement, crossoverArrPos,
					xmin, xmax, nmin, nmax, ns, RMm, Rmm));

		}

	}

	private void updateBestCoeff(ArrayList<Ind> t) {
		double nminc = 0;
		double nmaxc = 0;
		double xminc = 0;
		double xmaxc = 0;
		for (int i = 0; i < mean_to_next; i++) {
			nminc += ((t.get(i).getNf() - (best_coeff * t.get(i).getNf())) / mean_to_next);
			nmaxc += ((t.get(i).getNf() + (best_coeff * t.get(i).getNf())) / mean_to_next);
			xminc += ((t.get(i).getXf() - (best_coeff * t.get(i).getXf())) / mean_to_next);
			xmaxc += ((t.get(i).getXf() + (best_coeff * t.get(i).getXf())) / mean_to_next);
		}

		if (nminc == nmin | nmaxc == nmax | xminc == xmin | xmaxc == xmax) {
			crossover(t);
			// generate(t);
			best_coeff = best_coeff_min
					+ ((best_coeff - best_coeff_min) - ((best_coeff - best_coeff_min) * best_coeff_min))
					* (1 + best_coeff_impl);

		}

		nmin = nminc;
		nmax = nmaxc;
		xmin = xminc;
		xmax = xmaxc;
	}
}

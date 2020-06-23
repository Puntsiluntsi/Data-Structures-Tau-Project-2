import java.time.Duration;
import java.time.Instant;
import java.util.Random;

// import IHashTable.KeyAlreadyExistsException;
// import IHashTable.TableIsFullException;

public class Experiment_Amit {
	public static void main(String args[]) {
		int m, n;
		long p;
		Random rand = new Random();
		Instant start, finish;
		double runningTime;
		LPHashTable tableLP;
		AQPHashTable tableAQP;
		QPHashTable tableQP;
		DoubleHashTable tableD;

		// Q 3.b
		System.out.println("====================================================");
		System.out.println("STARTING Q 3.b");
		System.out.println("====================================================");
		m = 6571;
		p = 1000000007;
		int excepQP=0, excepAQP=0;

		long[] d = new long[m];
		//System.out.println("Start inserting for QPHashTable: ");
		for (int t = 0; t < 100; t++) {

			QPHashTable table3 = new QPHashTable(m, p);

			// The random series
			for (int i = 0; i < m; i++) {
				d[i] = (100 * i) + rand.nextInt(100);
			}

			for (int i = 0; i < m; i++) {
				try {
					table3.Insert(new HashTableElement(d[i], d[i]));
				} catch (Exception e) {
					excepQP++;
					
					
				}
			}
		}
		
		long[] d2 = new long[m];
		//System.out.println("Start inserting for AQPHashTable: ");
		for (int k = 0; k < 100; k++) {

			AQPHashTable table3_2 = new AQPHashTable(m, p);

			// The random series
			for (int i = 0; i < m; i++) {
				d2[i] = (100 * i) + rand.nextInt(100);
			}

			for (int i = 0; i < m; i++) {
				try {
					table3_2.Insert(new HashTableElement(d2[i], d2[i]));
				} catch (Exception e) {
					excepAQP++;
				}
			}
		}
	System.out.println("number of exceptions in QP: "+excepQP+"\nnumber of exceptions in AQP: "+excepAQP);	


		// Q 4.a
		System.out.println("====================================================");
		System.out.println("STARTING Q 4.a");
		System.out.println("====================================================");
		m = 10000019;
		p = 1000000007;
		n = (int) (m / 2);
		long[] a = new long[n];

		// The random series
		for (int i = 0; i < n; i++) {
			a[i] = (100 * i) + rand.nextInt(100);
		}

		// LP test
		start = Instant.now();
		tableLP = new LPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(a[i], a[i]);
			try {
				tableLP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of LP is: " + runningTime);

		// QP test
		start = Instant.now();
		tableQP = new QPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(a[i], a[i]);
			try {
				tableQP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of QP is: " + runningTime);

		// AQP test
		start = Instant.now();
		tableAQP = new AQPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(a[i], a[i]);
			try {
				tableAQP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of AQP is: " + runningTime);

		// DoubleHashTable test
		start = Instant.now();
		tableD = new DoubleHashTable(m, p);
		for (int i = 0; i < n; i++) {
			
			HashTableElement element = new HashTableElement(a[i], a[i]);
			try {
				tableD.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of DoubleHashTable is: " + runningTime);

		// Q 4.b
		System.out.println("====================================================");
		System.out.println("STARTING Q 4.b");
		System.out.println("====================================================");
		m = 10000019;
		p = 1000000007;
		n = (int) ((19 * m) / 20);
		long[] b = new long[n];

		// The random series
		for (int i = 0; i < n; i++) {
			b[i] = (100 * i) + rand.nextInt(100);
		}

		// LP test
		start = Instant.now();
		tableLP = new LPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(b[i], b[i]);
			try {
				tableLP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of LP is: " + runningTime);
/**
		// QP test
		start = Instant.now();
		tableQP = new QPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(b[i], b[i]);
			try {
				tableQP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of QP is: " + runningTime);
**/
		// AQP test
		start = Instant.now();
		tableAQP = new AQPHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(b[i], b[i]);
			try {
				tableAQP.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of AQP is: " + runningTime);

		// DoubleHashTable test
		start = Instant.now();
		tableD = new DoubleHashTable(m, p);
		for (int i = 0; i < n; i++) {
			HashTableElement element = new HashTableElement(b[i], b[i]);
			try {
				tableD.Insert(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		finish = Instant.now();
		runningTime = Duration.between(start, finish).toMillis();
		System.out.println("The running time of DoubleHashTable is: " + runningTime);

		
		// Q 5
		System.out.println("====================================================");
		System.out.println("STARTING Q 5");
		System.out.println("====================================================");
		m = 10000019;
		p = 1000000007;
		n = (int) (m / 2);
		long[] c = new long[n];
		start = Instant.now();

		int deleted =0;
		// OAHashTable table5 = new DoubleHashTable(m, p);
		OAHashTable table5 = new DoubleHashTable(m, p);
		for (int times = 1; times <= 6; times++) {
			// System.out.println(times);
			if (times == 1 || times == 4) {
				start = Instant.now();
			}
			// a. The random series
			for (int i = 0; i < n; i++) {
				c[i] = (100 * i) + rand.nextInt(100);
			}


			deleted=0;
			for (int i = 0; i < table5.getTableLength(); i++) {
				if (table5.table[i] == OAHashTable.deleted)
					deleted+=1;
			}
			// System.out.println("deleted: "+deleted);

			// System.out.println("size: "+table5.currSize);
			// System.out.println(times+" - started inserting ------------------------------------ ");
			// b. inserting
			for (int i = 0; i < n; i++) {
				// if (times >= 2 && i % 1000000 == 0) System.out.println("Inserting "+i);
				// if (times >= 3) System.out.println("Inserting "+i);
				// if (times >= 2 && i > 4*n/5) table5.debug=true;
				try {
					table5.Insert(new HashTableElement(c[i], c[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// System.out.println(times+" - finished inserting ------------------------------------ ");
			// System.out.println("size: "+table5.currSize);
			// System.out.println(times+" - started deleting ------------------------------------ ");
			// c. deleting
			for (int i = 0; i < n; i++) {
				try {
					table5.Delete(c[i]);
				} catch (Exception e) {
				}
			}
			// System.out.println(times+" - finished deleting ------------------------------------ ");
			// System.out.println("size: "+table5.currSize);
			if (times == 3 || times == 6) {
				finish = Instant.now();
				runningTime = Duration.between(start, finish).toMillis();
				System.out.println("The running time after iteration " + times + " is " + runningTime);
			}
		}
	}
}

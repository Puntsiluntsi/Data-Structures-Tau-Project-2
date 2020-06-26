import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// import IHashTable.KeyAlreadyExistsException;
// import IHashTable.TableIsFullException;

public class Experiment_Amit {
    public static void main(String args[]) {
        int m, n;
        long p;
        Random rand = new Random();
        Instant start, finish;
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
        int exceptionCountQP = totalTableIsFullExceptionCount(new QPHashTable(m,p),m,100);
        int exceptionCountAQP = totalTableIsFullExceptionCount(new AQPHashTable(m,p),m,100);

        System.out.println("number of exceptions in QP: " + exceptionCountQP + "\nnumber of exceptions in AQP: " + exceptionCountAQP);


        // Q 4.a
        System.out.println("====================================================");
        System.out.println("STARTING Q 4.a");
        System.out.println("====================================================");
        m = 10000019;
        p = 1000000007;
        n = m/2;
        long[] a = new long[n];

        // The random series
        for (int i = 0; i < n; i++) {
            a[i] = (100*i) + rand.nextInt(100);
        }

        // LP test
        Duration dur = insertsDuration(new LPHashTable(m,p),n);
        System.out.println(dur);
        long runningTime4aLP = insertsDuration(new LPHashTable(m,p),n).toMillis();

        System.out.println("The running time of LP is: " + runningTime4aLP);

        long runningTime4aQP = insertsDuration(new QPHashTable(m,p),n).toMillis();

        System.out.println("The running time of QP is: " + runningTime4aQP);

        long runningTime4aAQP = insertsDuration(new AQPHashTable(m,p),n).toMillis();
        System.out.println("The running time of AQP is: " + runningTime4aAQP);

        long runningTime4aDH = insertsDuration(new DoubleHashTable(m,p),n).toMillis();
        System.out.println("The running time of DoubleHashTable is: " + runningTime4aDH);

        // Q 4.b
        System.out.println("====================================================");
        System.out.println("STARTING Q 4.b");
        System.out.println("====================================================");
        m = 10000019;
        p = 1000000007;
        n = (int) ((19*m)/20);
        long[] b = new long[n];

        // The random series
        for (int i = 0; i < n; i++) {
            b[i] = (100*i) + rand.nextInt(100);
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
        long runningTime = Duration.between(start, finish).toMillis();
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
        n = (int) (m/2);
        long[] c = new long[n];
        start = Instant.now();

        int deleted = 0;
        // OAHashTable table5 = new DoubleHashTable(m, p);
        OAHashTable table5 = new DoubleHashTable(m, p);
        for (int times = 1; times <= 6; times++) {
            // System.out.println(times);
            if (times == 1 || times == 4) {
                start = Instant.now();
            }
            // a. The random series
            for (int i = 0; i < n; i++) {
                c[i] = (100*i) + rand.nextInt(100);
            }


            deleted = 0;
            for (int i = 0; i < table5.getTableLength(); i++) {
                if (table5.table[i] == OAHashTable.deleted)
                    deleted += 1;
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

    private static int totalTableIsFullExceptionCount(IHashTable table, int seriesLength, int repeat) {
        int sum=0;
        for (int i = 0; i < repeat; i++) {
            sum += insertsTableIsFullExceptionCount(table, seriesLength);
        }
        return sum;
    }

    private static int insertsTableIsFullExceptionCount(IHashTable table, int seriesLength) {

        int cnt=0;
        HashTableElement[] elements = randomElementSeries(seriesLength);
        for (int i = 0; i < seriesLength; i++) {
            try {
                table.Insert(elements[i]);
            } catch (IHashTable.TableIsFullException e) {
                cnt++;
            } catch (IHashTable.KeyAlreadyExistsException e) {
                throw new Error("KeyAlreadyExistsException in 3.b", e);
            }
        }
        return cnt;
    }


    private static Duration insertsAndDeletesDuration(IHashTable table, int seriesLength) {
        long[] keys = randomKeySeries(seriesLength);
        HashTableElement[] elements = elementArrayFromKeyArray(keys);
        Instant start = Instant.now();
        insertElementsFromArray(table, elements);
        deleteKeysFromArray(table, keys);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    private static Duration insertsDuration(IHashTable table, int seriesLength) {
        HashTableElement[] elements = randomElementSeries(seriesLength);
        Instant start = Instant.now();
        insertElementsFromArray(table, elements);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    private static void insertElementsFromArray(IHashTable table, HashTableElement[] arr) {
        for (int i = 0; i < arr.length; i++) {
            table.Insert(arr[i]);
        }
    }

    private static void deleteKeysFromArray(IHashTable table, long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            table.Delete(arr[i]);
        }
    }


    private static long randKey(int i, Random rand) {
        long res = (long) i*100 + rand.nextInt(100);
        assert res >= 0;
        return res;
    }

    private static boolean areDistinct(long arr[]) {
        List<Long> l = Arrays.stream(arr).mapToObj(x->x).collect(Collectors.toList());
        Set<Long> s = new HashSet<Long>(l);
        return s.size() == arr.length;
    }
    private static long[] randomKeySeries(int len) {
        Random rand = new Random();
        long[] ret = IntStream.range(0, len).mapToLong(i -> randKey(i, rand)).toArray();
        assert areDistinct(ret);
        return ret;
    }

    //	private static HashTableElement hteFromKeyArr(long[] keyArr, int i) {
    //		return new HashTableElement(keyArr[i], keyArr[i]);
    //	}

    private static HashTableElement[] elementArrayFromKeyArray(long[] keyArr) {
        return Arrays.stream(keyArr).mapToObj(key -> new HashTableElement(key, key)).toArray(HashTableElement[]::new);
    }

    private static HashTableElement[] randomElementSeries(int len) {
        return elementArrayFromKeyArray(randomKeySeries(len));
    }

}

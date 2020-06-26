import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// import IHashTable.KeyAlreadyExistsException;
// import IHashTable.TableIsFullException;

public class Experiment {
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
        final int m1 = 6571;
        final long p1 = 1000000007;
        int exceptionCountQP = totalTableIsFullExceptionCount(()->new QPHashTable(m1,p1),m1,100);
        int exceptionCountAQP = totalTableIsFullExceptionCount(()->new AQPHashTable(m1,p1),m1,100);

        System.out.println("number of exceptions in QP: " + exceptionCountQP + "\nnumber of exceptions in AQP: " + exceptionCountAQP);


        // Q 4.a
        System.out.println("====================================================");
        System.out.println("STARTING Q 4.a");
        System.out.println("====================================================");
        m = 10000019;
        p = 1000000007;
        n = m/2;

        for (OAHashTable table: new OAHashTable[]{new LPHashTable(m,p),new QPHashTable(m,p), new AQPHashTable(m,p), new DoubleHashTable(m,p)}){
            long runningTime4a = insertsDuration(table,n).toMillis();
            System.out.printf("The running time of %s is: %d\n",table.getClass().getName(),runningTime4a);
        }

        // Q 4.b
        System.out.println("====================================================");
        System.out.println("STARTING Q 4.b");
        System.out.println("====================================================");
        n = (19*m)/20;
        for (OAHashTable table: new OAHashTable[]{new LPHashTable(m,p)/*,new QPHashTable(m,p)*/, new AQPHashTable(m,p), new DoubleHashTable(m,p)}){
            long runningTime4a = insertsDuration(table,n).toMillis();
            System.out.printf("The running time of %s is: %d\n",table.getClass().getName(),runningTime4a);
        }




        // Q 5
        System.out.println("====================================================");
        System.out.println("STARTING Q 5");
        System.out.println("====================================================");
        m = 10000019;
        p = 1000000007;
        n = m/2;

        DoubleHashTable table = new DoubleHashTable(m, p);
        long runningTimeFirst3 = totalInsertsAndDeletesDuration(table, n, 3).toMillis();
        long runningTimeLast3 = totalInsertsAndDeletesDuration(table, n, 3).toMillis();

        System.out.printf("first 3 iterations: %d\nlast 3 iterations: %d\n", runningTimeFirst3, runningTimeLast3);

    }

    private static int totalTableIsFullExceptionCount(Supplier<IHashTable> tableGenerator, int seriesLength, int repeat) {
        int sum=0;
        for (int i = 0; i < repeat; i++) {
            sum += insertsTableIsFullExceptionCount(tableGenerator.get(), seriesLength);
        }
        return sum;
    }

    private static Duration totalInsertsAndDeletesDuration(IHashTable table, int seriesLength, int repeat) {
        Duration totalDuration = Duration.ZERO;
        for (int i = 0; i < repeat; i++) {
            totalDuration = totalDuration.plus(insertsAndDeletesDuration(table, seriesLength));
        }
        return totalDuration;
    }

    private static int insertsTableIsFullExceptionCount(IHashTable table, int seriesLength) {

        int cnt=0;
        HashTableElement[] elements = randomElementSeries(seriesLength);
        for (int i = 0; i < seriesLength; i++) {
            try {
                table.Insert(elements[i]);
            } catch (IHashTable.TableIsFullException e) {
                cnt++;
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
        long res = ((long) i)*100 + rand.nextInt(100);
        assert res >= 0;
        return res;
    }

    private static boolean areDistinct(long arr[]) {
        List<Long> l = Arrays.stream(arr).mapToObj(x->x).collect(Collectors.toList());
        Set<Long> s = new HashSet<Long>(l);
        return s.size() == arr.length;
    }

    private static long[] randomKeySeries(int seriesLength) {
        Random rand = new Random();
        long[] ret = IntStream.range(0, seriesLength).mapToLong(i -> randKey(i, rand)).toArray();
        //assert areDistinct(ret);
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

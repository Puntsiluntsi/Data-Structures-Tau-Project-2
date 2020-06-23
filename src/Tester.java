import java.util.Random;

public class Tester {
    public static void main(String[] args) throws Exception{
		Random rand = new Random();
        int m,p,n;
        m = 10000019;
		p = 1000000007;
        n = (int) (m / 2);
        OAHashTable table = new DoubleHashTable(m, p);

        long[] c = new long[n];
        for (int i = 0; i < n; i++)
            c[i] = (100 * i) + rand.nextInt(100);

        // SET EVERYTHING TO DELETED
        System.out.println("BEFORE");
        for (int i = 0; i < m; i++)
            table.table[i] = table.deleted;
        System.out.println("AFTER");

        // INSERTING
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.println("Inserting "+i);
            // if (i > 0) table.debug=true;
            try {
                // table.Insert(new HashTableElement(c[i], c[i]));
                for (int j = 0; j < m; j++)
                    table.table[i] = OAHashTable.deleted;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // DELETING
        for (int i = 0; i < n; i++) {
            try {
                table.Delete(c[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // int m=6; long p = 23;
        // IHashTable[] arr = new IHashTable[] {new LPHashTable(m,p), new QPHashTable(m,p), new AQPHashTable(m,p), new DoubleHashTable(m,p)};
        // IHashTable[] arr = new IHashTable[] {new QPHashTable(m,p)};
        // for (IHashTable hashtable : arr) {}
    }
}
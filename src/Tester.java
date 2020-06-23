import java.util.Random;
import java.time.Duration;
import java.time.Instant;


public class Tester {
    public static void main(String[] args) throws Exception {
        int m = 10000000;
        int p = 1000000007;
        Random rand = new Random();
		Instant start, finish;
        ModHash baseHash = ModHash.GetFunc(m, p);
        ModHash stepHash = ModHash.GetFunc(m - 1, p);
        int times = 50;
        int x=0, step=0;
        
        start = Instant.now();
        System.out.println("Started AQP");
        for (int j = 0; j < times; j++)
            for (int i = 0; i < m; i++)
                x = (int) Math.floorMod((long) baseHash.Hash(i)+((i & 1) == 0 ? i*i : -i*i), m);
        finish = Instant.now();
        System.out.println("it took "+Duration.between(start, finish).toMillis()/times);


        System.out.println("Started Double FloorMod");
        start = Instant.now();
        for (int j = 0; j < times; j++)
            for (int i = 0; i < m; i++) {
                step = stepHash.Hash(i);
                x = (int) Math.floorMod((long) baseHash.Hash(x)+step, m);
            }
        finish = Instant.now();
        System.out.println("it took "+Duration.between(start, finish).toMillis()/times);

        System.out.println("Started Double Mod");
        start = Instant.now();
        for (int j = 0; j < times; j++)
            for (int i = 0; i < m; i++) {
                step = stepHash.Hash(i);
                x = (int) ((long) baseHash.Hash(x)+step) % m;
            }
        finish = Instant.now();
        System.out.println("it took "+Duration.between(start, finish).toMillis()/times);
                // x = (int) Math.floorMod(baseHash.Hash(i)+((i & 1) == 0 ? i*i : -i*i), m);

        //        m = 419;
        //        p = 1549;
        //        for (IHashTable hashtable : new IHashTable[]
        //                {new LPHashTable(m, p), /*new QPHashTable(m,p),*/ new AQPHashTable(m, p), new DoubleHashTable(m, p)}) {
        //            for (int i = 0; i < m - 50; i++) {
        //                hashtable.Insert(new HashTableElement(i, i));
        //            }
        //            hashtable.Delete(6);
        //            //            HashTableElement hte = hashtable.Find(6);
        //            HashTableElement hte = hashtable.Find(7);
        //            System.out.println(hte == null ? hte : hte.GetValue());
        //        }
        //

    }
}
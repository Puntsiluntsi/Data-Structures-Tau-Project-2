import java.util.Random;

public class Tester{
    public static void main(String[] args) throws Exception{
        Random rand = new Random();
        int m = 10000000;
        int p = 1000000007;
        ModHash baseHash = ModHash.GetFunc(m, p);
        ModHash stepHash = ModHash.GetFunc(m - 1, p);
        int times = 100000000;
        int x=0;
        
        System.out.println("Started AQP");
        for (int j = 0; j < times; j++)
            for (int i = 0; i < m; i++)
                x = (int) Math.floorMod(baseHash.Hash(i)+((i & 1) == 0 ? i*i : -i*i), m);
                System.out.println(x);

        System.out.println("Started Double");
        for (int j = 0; j < times; j++)
            for (int i = 0; i < m; i++)
                x = stepHash.Hash(i);
                // x = (int) Math.floorMod(baseHash.Hash(i)+((i & 1) == 0 ? i*i : -i*i), m);


        
        // int m=6; long p = 23;
        // IHashTable[] arr = new IHashTable[] {new LPHashTable(m,p), new QPHashTable(m,p), new AQPHashTable(m,p), new DoubleHashTable(m,p)};
        // IHashTable[] arr = new IHashTable[] {new QPHashTable(m,p)};
        // for (IHashTable hashtable : arr) {}
    }
}
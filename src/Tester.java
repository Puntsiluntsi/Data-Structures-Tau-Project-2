public class Tester {
    public static void main(String[] args) throws Exception{
        int m,p,n;
        m = 10000019;
		p = 1000000007;
        n = (int) (m / 2);

        OAHashTable table = new LPHashTable(m, p);
        
        // int m=6; long p = 23;
        // IHashTable[] arr = new IHashTable[] {new LPHashTable(m,p), new QPHashTable(m,p), new AQPHashTable(m,p), new DoubleHashTable(m,p)};
        // IHashTable[] arr = new IHashTable[] {new QPHashTable(m,p)};
        // for (IHashTable hashtable : arr) {}
    }
}
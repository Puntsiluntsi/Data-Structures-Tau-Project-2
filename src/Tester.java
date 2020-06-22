public class Tester {
    public static void main(String[] args) throws Exception{
        int m=20; long p = 23;
        for (IHashTable hashtable : new IHashTable[]
        {new LPHashTable(m,p), new QPHashTable(m,p), new AQPHashTable(m,p), new DoubleHashTable(m,p)})
        {
            hashtable.Insert(new HashTableElement(0, 0));
            System.out.println("o");
        } 
    }
}
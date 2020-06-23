import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ModHash {

    private long a, b, p;
    private int m;

    public ModHash(long a, long b, int m, long p) {
        assert 1 <= a && a < p && 0 <= b && b < p && m <= p;
        this.a = a;
        this.b = b;
        this.m = m;
        this.p = p;
    }

    public static ModHash GetFunc(int m, long p) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new ModHash(rand.nextLong(1, p), rand.nextLong(p), m, p);
    }

    public int Hash(long key) {
        return (int) ((a*key + b) % p) % m;
    }
}

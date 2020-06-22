import java.util.Random;

public class DoubleHashTable extends OAHashTable {

    private ModHash baseHash;
    private ModHash stepHash;

    public DoubleHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
        stepHash = ModHash.GetFunc(m - 1, p);
    }

    @Override
    public int Hash(long x, int i) {
        return (int) (((long) baseHash.Hash(x) + i*(stepHash.Hash(x) + 1)) % getTableLength());
        // if m is small enough to not cause integer overflow the long (and int) cast can be omitted:
        // return (baseHash.Hash(x) + i*(stepHash.Hash(x)+1))% getTableLength()
    }

}

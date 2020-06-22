import java.util.Random;

public class DoubleHashTable extends OAHashTable {

    private ModHash baseHash;
    private ModHash stepHash;

    public DoubleHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
        // TODO: realize how to make a stepHash function that always returns a number coprime to m,
        // as seen in the presentation (now m isn't necessarily prime, I think).
    }

    @Override
    public int Hash(long x, int i) {
        return (int) (((long) baseHash.Hash(x) + i*(stepHash.Hash(x) + 1)) % getTableLength());
        // if m is small enough to not cause integer overflow the long (and int) cast can be omitted:
        // return (baseHash.Hash(x) + i*(stepHash.Hash(x)+1))% getTableLength()
    }

}

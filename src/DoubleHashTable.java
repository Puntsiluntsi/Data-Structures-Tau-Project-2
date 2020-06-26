import java.util.Random;

public class DoubleHashTable extends OAHashTable {

    private ModHash stepHash;

    public DoubleHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
        stepHash = ModHash.GetFunc(m - 1, p);
    }

    @Override
    public int Hash(long x, int i) {
        return hashByStepFromBase(x, (long)i*(stepHash.Hash(x)+1));
        // long cast is necessary because the expression can overflow int boundaries
        // (e.g. when both i and the step equal m-1, and m-1 has more than 16 bits,
        // causing (m-1)*(m-1) to overflow past 32 bits).
    }

}

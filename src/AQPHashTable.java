import java.util.Random;

public class AQPHashTable extends OAHashTable {

    private ModHash baseHash;
    public AQPHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
    }

    @Override
    public int Hash(long x, int i) {
        int currStepSize = i * i;
        return (int) ((long)baseHash.Hash(x) + ((i&1)==0 ? currStepSize : -currStepSize));
    }
}

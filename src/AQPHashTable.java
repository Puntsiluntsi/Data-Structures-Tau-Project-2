public class AQPHashTable extends OAHashTable {

    public AQPHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
    }

    @Override
    public int Hash(long x, int i) {
        int currStepSize = i * i;
        return hashByStepFromBase(x, ((i & 1) == 0 ? currStepSize : -currStepSize));
    }
}

public class DoubleHashTable extends OAHashTable {

    private ModHash stepHash;

    public DoubleHashTable(int m, long p) {
        super(m);
        baseHash = ModHash.GetFunc(m, p);
        stepHash = ModHash.GetFunc(m - 1, p);
    }

    @Override
    public int Hash(long x, int i) {
        return hashByStepFromBase(x,i*(stepHash.Hash(x)+1));
    }

}

public class LPHashTable extends OAHashTable {

	public LPHashTable(int m, long p) {
		super(m);
		baseHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return hashByStepFromBase(x,i);
	}
	
}

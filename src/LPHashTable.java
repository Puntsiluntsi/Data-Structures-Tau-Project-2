import java.util.Random;

public class LPHashTable extends OAHashTable {

	private ModHash baseHash;
	public LPHashTable(int m, long p) {
		super(m);
		baseHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (int) (((long)baseHash.Hash(x) + i*i)%getTableLength());
	}
	
}

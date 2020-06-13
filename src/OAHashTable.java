import java.util.HashSet;

public abstract class OAHashTable implements IHashTable {

    private HashTableElement[] table;
    private static final HashTableElement deleted = new HashTableElement(-1, -1);
    private int currSize=0;

    public OAHashTable(int m) {
        this.table = new HashTableElement[m];
    }


    private int findIndex(long key) {
        for (int i = 0; i < table.length; i++) {
            int ind = Hash(key, i);
            if (table[ind] == null) {
                return -1;
            } else if (table[ind] == deleted) {
                continue;
            } else if (table[ind].GetKey() == key) {
                return ind;
            }
        }
        return -1; // search sequence is full and doesn't contain key.
    }

    @Override
    public HashTableElement Find(long key) {
        // TODO implement find
        int ind = findIndex(key);
        if (ind == -1) {
            return null;
        }
        return table[ind];
    }

    @Override
    public void Insert(HashTableElement hte) throws TableIsFullException, KeyAlreadyExistsException {
        long key = hte.GetKey();
        int firstDeletedIndex;
        boolean sawDeleted = false;
        for (int i = 0; i < table.length; i++) {
            int ind = Hash(key, i);
            if (table[ind] == null) {
                table[ind] = hte;
                currSize++;
                return;
            } else if (table[ind] == deleted) {
                // we can not immediately insert at this position,
                // since key can still be already in the table later in the search sequence.
                if (!sawDeleted) {
                    firstDeletedIndex = ind;
                    sawDeleted = true;
                }
            } else if (table[ind].GetKey() == key) {
                throw new KeyAlreadyExistsException(hte);
            }
        }
        if (sawDeleted) {
            table[firstDeletedIndex] = hte;
            currSize++;
        } else {
            assert (currSize == table.length);
            // assert that we throw TableIsFullException only when the table is truly full.
            // TODO: if we're sure after testing that the
            //  sequence is full IFF the table is full, we can
            //  decide to either check only by currSize at the
            //  start of the method or to delete the field
            //  currSize and check only if the sequence full
            //  (as done before).

            throw new TableIsFullException(hte);
        }

    }


    @Override
    public void Delete(long key) throws KeyDoesntExistException {
        int ind = findIndex(key);
        if (ind == -1) {
            throw new KeyDoesntExistException(key);
        }
        table[ind] = deleted;
    }

    /**
     * @param x - the key to hash
     * @param i - the index in the probing sequence
     * @return the index into the hash table to place the key x
     */
    public abstract int Hash(long x, int i);
}

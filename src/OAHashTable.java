public abstract class OAHashTable implements IHashTable {

    public HashTableElement[] table;
    private int tableLength;
    public static final HashTableElement deleted = new HashTableElement(-1, -1);
    public int currSize;
    protected ModHash baseHash;

    public boolean debug = false;

    public OAHashTable(int m) {
        this.tableLength=m;
        this.table = new HashTableElement[tableLength];
        this.currSize=0;
    }

    /**
     * Finds the index of an element with a specified key in the table.
     * @param key - the key of the element to find its index.
     * @return the index in the table of the element with the specified key if exists,
     *         else -1.
     */
    private int findIndex(long key) {
        for (int i = 0; i < tableLength; i++) {
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

    /**
     * Finds the element with a specified key in the table.
     * @param key - the key of the element to find.
     * @return the element with the specified key if exists, else null.
     */
    @Override
    public HashTableElement Find(long key) {
        int ind = findIndex(key);
        if (ind == -1) {
            return null;
        }
        return table[ind];
    }

    /**
     * Inserts an element into the table.
     * @param hte - the element to insert.
     * @throws TableIsFullException if there is no space in the element's key search sequence.
     * @throws KeyAlreadyExistsException if the element's key is already in the table.
     */
    @Override
    public void Insert(HashTableElement hte) throws TableIsFullException, KeyAlreadyExistsException {
        

        long key = hte.GetKey();
        int firstDeletedIndex=-1; // -1 means we did not found a deleted mark.
        currSize++;  // We assume for now that we can insert the key, if we don't we update size later
        for (int i = 0; i < tableLength; i++) {
            if (debug) System.out.println(i);
            int ind = Hash(key, i);
            if (table[ind] == null) {
                if (firstDeletedIndex == -1) {
                    // We found an available place and did not encounter a deleted place
                    // before. therefore we will insert the element here.
                    table[ind] = hte;
                    return;
                }
                // We already found a deleted mark before, so we will insert there.
                table[firstDeletedIndex] = hte;
                return;

            } else if (table[ind] == deleted) {
                // we can not immediately insert at this position,
                // since key can still be already in the table later in the search sequence.
                if (firstDeletedIndex == -1)
                    firstDeletedIndex = ind;
            } else if (table[ind].GetKey() == key) {
                currSize--;  // We didn't manage to insert the key so we will decrease the size.
                throw new KeyAlreadyExistsException(hte);
            }
        }
        if (firstDeletedIndex != -1) {
            table[firstDeletedIndex] = hte;
            // after we checked that the key isn't already in the table we can
            // insert it instead of the first deleted component in the table.

        } else {
            currSize--;  // We didn't manage to insert the key so we will decrease the size.
            throw new TableIsFullException(hte);
        }

    }

    /**
     * Deletes an element with a specified key from the table.
     * @param key - the key of the element to delete.
     * @throws KeyDoesntExistException if the key doesn't exist in the table.
    */
    @Override
    public void Delete(long key) throws KeyDoesntExistException {
        int ind = findIndex(key);
        if (ind == -1) {
            throw new KeyDoesntExistException(key);
        }
        table[ind] = deleted;
        currSize--;
    }

    /**
     * @param x - the key to hash.
     * @param i - the index in the probing sequence.
     * @return the index into the hash table to place the key x.
     */
    public abstract int Hash(long x, int i);

    protected int getTableLength() {
        return tableLength;
    }

    /** returns the result of a hash function that is composed of the base hash
     * function + a certain increment modulo the length of the table
     * @param x - the key to hash.
     * @param step - the step by which to increment.
     * @return the result of the hash function described above.
     */
    protected int hashByStepFromBase(long x, long step) {
        return (int) Math.floorMod(baseHash.Hash(x)+step, tableLength);
    }
}

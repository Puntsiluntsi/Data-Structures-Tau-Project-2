//TODO END: remove these imports when finishing
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * Finds the index of an element with a specified key in the table,
     * searching starting at a specified probe number.
     * @param key - the key of the element to find its index.
     * @param startProbe - the probe number to start searching from in the probing sequence;
     *                     i.e the search is preformed on indices Hash(key,startProbe),...,Hash(key,m-1).
     * @return the index in the table of the element with the specified key if it is found in
     *         the probing sequence, else -1.
     */
    private int findIndexStartingFrom(long key, int startProbe) {
        assert (0 <= startProbe && startProbe < tableLength);
        for (int i = startProbe; i < tableLength; i++) {
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
     * Finds the index of an element with a specified key in the table.
     * @param key - the key of the element to find its index.
     * @return the index in the table of the element with the specified key if exists,
     *         else -1.
     */
    private int findIndex(long key) {
        return findIndexStartingFrom(key, 0);
    }

    /**
     * Checks if a specified key is to be found at its probe sequence,
     * starting from a specified probe number.
     * @param key - the key of the element to find its index.
     * @param startProbe - the probe number to start searching from in the probing sequence;
     *                     i.e the search is preformed on indices Hash(key,startProbe),...,Hash(key,m-1).
     * @return the index in the table of the element with the specified key if it is found in
     *         the probing sequence, else -1.
     */
    private boolean keyInSequenceStartingFrom(long key, int startProbe) {
        return findIndexStartingFrom(key, startProbe) != -1;
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
        currSize++;  // We assume for now that we can insert the key, if we don't we update size later
        for (int i = 0; i < tableLength; i++) {
            if (debug) System.out.println(i);
            int ind = Hash(key, i);
            if (table[ind] == null) {
                    table[ind] = hte;
                    return;
            } else if (table[ind] == deleted) {
                if (keyInSequenceStartingFrom(key, i + 1)) {
                    throw new KeyAlreadyExistsException(hte);
                } else {
                    table[ind]=hte;
                    return;
                }
            } else if (table[ind].GetKey() == key) {
                throw new KeyAlreadyExistsException(hte);
            }
        }
        throw new TableIsFullException(hte);
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
        return (int) Math.floorMod(baseHash.Hash(x)+step, getTableLength());
        //TODO: compare complexity of floorMod to normal mod
    }

    // TODO END: remove at the end
    @Override
    public String toString() {
        return IntStream.range(0, tableLength).filter(i -> table[i] != null).mapToObj(i -> "" + i + ": " + (table[i] == deleted ? "[DELETED]" : Long.toString(table[i].GetKey()))).collect(Collectors.joining(", "));
    }
}

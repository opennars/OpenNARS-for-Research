
package nars.storage;

/**
 * A pseudo-random number generator, used in Bag.
 */
class Distributor {
    /**
     * Random sequence of valid numbers
     */
    private int order[];
    /**
     * Capacity of the array
     */
    private int capacity;
    
    /**
     * For any number N < range, there is N+1 copies of it in the array.
     * 
     * @param range Range of valid numbers
     */
    public Distributor(int range) {
        int index, rank, time;
        capacity = (range * (range + 1)) / 2;
        order = new int[capacity];
        for (index = 0; index < capacity; index++)
            order[index] = -1;
        for (rank = range; rank > 0; rank--)
            for (time = 0; time < rank; time++) {
                index = (((int) (capacity / rank)) + index) % capacity;
                while (order[index] >= 0)
                    index = (index + 1) % capacity;
                order[index] = rank - 1;
            }
    }
    
    /**
     * Get the next number according to the given index
     * 
     * @param index The current index
     * @return the random value
     */
    public int pick(int index)	{
        return order[index];
    }
    
    /**
     * Advance the index
     * 
     * @param index The current index
     * @return the next index
     */
    public int next(int index) {
        return (index + 1) % capacity;
    }
}
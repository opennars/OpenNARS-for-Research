
package nars.entity;

/**
 * An item is an object that can be put into a Bag,
 * and it participates in the resource competation of the system.
 */
public abstract class Item extends BudgetValue implements Cloneable {
    /**
     * The key of the Item, unique in a Bag
     */
    protected String key;   // uniquely define an Item in a bag
    
    protected Item() {}
    
    protected Item(BudgetValue v) {
        super(v);
    }
            
    /**
     * Get the current key
     * @return Current key value
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Set a new key value
     * @param k New key value
     */
    public void setKey(String k) {
        key = k;
    }

    /**
     * Get current BudgetValue
     * @return Current BudgetValue
     */
    public BudgetValue getBudget() {
        return this;
    }

    /**
     * Set new BudgetValue
     * @param v new BudgetValue
     */
    public void setBudget(BudgetValue v) {      // is this necessary?
        setPriority(v.getPriority());
        setDurability(v.getDurability());
        setQuality(v.getQuality());
    }
}


package nars.entity;

/**
 * A float value in [0, 1], with 4 digits accuracy.
 */
public class ShortFloat implements Cloneable {
    
    // the values are saved as short integers (-32768 to 32767, only 0 to 10000 used),
    // but used as float
    private short value;
    
    public ShortFloat(float v) {
        setValue(v);
    }
    
    // access value
    public float getValue() {
        return (float) (value * 0.0001);
    }

    public short getShortValue() {
        return value;
    }
    
    // set new value, rounded, with validity checking
    public void setValue(float v) {
        if ((v < 0) || (v > 1))
            System.out.println("!!! Wrong value: " + v);
        else
            value = (short) (v * 10000.0 + 0.5);
    }
    
    public boolean equals(Object that) {
        return ((that instanceof ShortFloat) && (value == ((ShortFloat) that).getShortValue()));
    }
    
    // full output
    public String toString() {
        if (value == 10000)
            return "1.0000";
        else {
            String s = String.valueOf(value);
            while (s.length() < 4)
                s = "0" + s;
            return "0." + s;
        }
    }
    
    // output with 2 digits, rounded
    public String toString2() {
        String s = toString();
        if (s.length() > 4)
            return s.substring(0, 4);
        else
            return s;
    }
}

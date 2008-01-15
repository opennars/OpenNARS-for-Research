
package nars.inference;

import nars.main.Parameters;

/**
 * Common functions on real numbers in [0,1].
 */
public class UtilityFunctions {

    // arithmetic average
    public static float aveAri(float... arr) {
        float sum = 0;
        for(int i=0; i<arr.length; i++)
            sum += arr[i];
        return sum / arr.length;
    }

    public static float or(float... arr) {
        float product = 1;
        for(int i=0; i<arr.length; i++)
            product *= (1 - arr[i]);
        return 1 - product;
    }
    
    public static float and(float... arr) {
        float product = 1;
        for(int i=0; i<arr.length; i++)
            product *= arr[i];
        return product;
    }
    
    // weight to confidence
    public static float w2c(float v) {
        return v / (v + Parameters.NEAR_FUTURE);
    }    
}


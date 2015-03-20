/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.matrix;

/**
 *
 * @author Thurman
 */ 
class ScalarMath {
    
    public static double product(double... factors) {
        double product = 1;
        for (double factor : factors) 
            product *= factor;
        return product;
    }
    
    public static double sum(double... addends) {
        double sum = 0;
        for (double addend : addends)
            sum += addend;
        return sum;
    }
} 

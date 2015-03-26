/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;

/**
 *
 * @author Guest
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] M = {
            {1, 2, 3}
        };
        
        double[] vec = {1, 2, 3};
        
        Vector v = new Vector(vec);
        
        Matrix A = new Matrix(M);
        
        System.out.println(A.order);
        System.out.println(v.order);
        System.out.println(A.plus(v));
        System.out.println(v.plus(A));
        Vector c = v.plus(A);
        
    }
    
}

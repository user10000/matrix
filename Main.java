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
        
        double[][] v = {{1, 2, 69}};
        Matrix B = new Matrix(v);
        
        Matrix A = new Matrix(M);
        
        System.out.println(A.order);
        System.out.println(B.order);
        System.out.println(A.plus(B));
        System.out.println(B.plus(A));
       
    }
    
}

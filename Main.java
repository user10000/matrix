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
        double[][] arr = MatrixMath.random(2,2);
        
        Matrix m = new Matrix(1, 1, 1);
        
        m = new Matrix(arr);
        System.out.println(m);
        System.out.println(DeterminantUtils.determinantRecursive(m));
    }
    
}

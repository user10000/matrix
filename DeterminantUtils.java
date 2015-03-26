/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 * Provides methods for calculating determinants, as required by other 
 * classes in the matrix package.
 * @author Guest
 */
class DeterminantUtils {
    private DeterminantUtils() {
    }
    
    
    public static double determinantRecursive(Matrix m) {
        return determinantRecursive(m, m.rows());
    }
    
    public static double determinantRecursive(Matrix m, int N) {
        if (N == 1) {
            return m.entry(1, 1);
        }
        
        else {
            Matrix minor;
            double det = 0;
            for (int i = 1 ; i <= m.rows() ; i++) {
                minor = m.minorMatrix(1, i);
                det += m.entry(1, i) * negOnePow(1 + i) * determinantRecursive(minor, minor.rows());
            }
            return det;
        }
    }

    public static double negOnePow(int exp) {
        return  ((1 & exp) << 1) - 1;
    }
    
    public static double determinantRowReduction(Matrix M) {
        return Double.NaN;
    }
}
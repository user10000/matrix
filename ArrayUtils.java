/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;

/**
 * Provides miscellaneous methods for manipulating arrays, as required by other
 * classes in the matrix package.
 *
 * @author Thurman
 */
class ArrayUtils {

    private ArrayUtils() {

    }

    /**
     * Returns an array filled with a specified element and of an specified
     * array size.
     *
     * @return an array filled with a constant element
     */
    public static double[][] fill(int row, int col, double entry) {
        double[][] fArr = new double[row][col];
        for (int r = 0 ; r < fArr.length ; r++) {
            for (int c = 0 ; c < fArr[r].length ; c++) {
                fArr[r][c] = entry;
            }
        }
        return fArr;
    }
    
    public static double[][] resize(double[][] arr, int m, int n) {
        double[][] tArr = new double[m][n];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, tArr[i], 0, Math.min(arr[i].length, n));
        }
        return tArr;
    }

    /**
     * Returns the first row's index having the maximum length in a 2D array.
     *
     * @param arr a 2D array
     * @return the index of the first instance of a longest row
     */
    public static int maxRow(double[][] arr) {
        int maxRowLength = arr[0].length;
        int maxRow = 0;
        for (int i = 1; i < arr.length; i++) {
            if (maxRowLength < arr[i].length) {
                maxRowLength = arr[i].length;
                maxRow = i;
            }
        }

        return maxRow;
    }

    public static void swap(double[][] arr, int index1, int index2) {
        double[] temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}

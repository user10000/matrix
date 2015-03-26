/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;

/**
 * Provides miscellaneous methods for manipulating arrays as required 
 * by other classes in the matrix package.
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
    public static double[] fill(int row, double entry) {
        double[] fArr = new double[row];
        for (int r = 0 ; r < fArr.length ; r++) {
                fArr[r] = entry;
        }
        return fArr;
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
    
    /**
     * Returns an array representing the resizing of another to the 
     * specified dimensions.
     * @param arr the array to resize
     * @param m the rows of the resized array
     * @param n the columns of the resized array
     * @return an array with a specified size containing the corresponding
     * elements in another array, or zero if they do not exist
     */
    public static double[][] resize(double[][] arr, int m, int n) {
        double[][] tArr = new double[m][n];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, tArr[i], 0, Math.min(arr[i].length, n));
        }
        return tArr;
    }
    
    public static double[] truncate(double[] arr, int m) {
        double[] tArr = new double[m];
        System.arraycopy(arr, 0, tArr, 0, m);
        return tArr;
    }
    
    /**
     * Returns the first row's index having the maximum length in a 2D array.
     *
     * @param arr a 2D array
     * @return the index of the first instance of a longest row
     */
    public static int maxRow(Object[]... arr) {
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
    
    /**
     * Swaps two objects in an array.
     * @param arr the array to swap in
     * @param index1 an index of an element to swap
     * @param index2 an index of an element to swap
     */
    public static void swap(Object[] arr, int index1, int index2) {
        Object temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    
    /**
     * Extends an array to another dimension.
     * @param arr the array to extend
     * @return the extended array
     */
    public static double[][] extend(double[] arr) {
        return new double[][] {arr.clone()};
    }
}

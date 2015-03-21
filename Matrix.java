/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 * A rectangular array of numbers.
 * @author Thurman
 */
public class Matrix {
    
    /**
     * The internal array storing the elements of the matrix.
     */
    double[][] matrix;
    
    /**
     * The number of rows of the array.
     */
    private int m;
    
    /**
     * The number of columns of the array.
     */
    private int n;
    
    
    private void initSize() {
        m = matrix.length;
        n = matrix[0].length;
    }
    
    /**
     * Constructs a matrix based on the specified array.
     * @param matrix a double 2D array representing a matrix
     */
    public Matrix(double[][] matrix) {
        this(matrix, )
    }
    
    /**
     * Constructs a zero matrix based the specified order.
     * @param m the rows of the matrix
     * @param n the columns of the matrix
     */
    public Matrix(int m, int n) {
        this.matrix = MatrixMath.zero(m, n);
        initSize();
    }
    
    /**
     * Constructs a matrix based on the specified array and order. 
     * 
     * <p>If the order is not equivalent to the array's order, truncation will
     * begin from the end row and column of the array.
     * @param matrix
     * @param m
     * @param n 
     */
    public Matrix(double[][] matrix, int m, int n) {
        matrix = truncate(matrix, m, n);
        matrix = fill(matrix);
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
    }
    
    /**
     * 
     * @param matrix
     * @return 
     */
    private double[][] fill(double[][] arr) {
        matrix = arr.clone();
        
        int maxRowLength = matrix[0].length;
        int maxRow = 0;
        
        for (int i = 1 ; i < matrix.length ; i++) {
            if (maxRowLength < matrix[i].length) {
                maxRowLength = matrix[i].length;
                maxRow = i;
            }
        }
        
        if (maxRowLength == matrix[0].length) return matrix;
        
        double[] temp = new double[maxRowLength];
        
        for (int i = 0 ; i < maxRow ; i++) { 
            if (matrix[i].length < maxRowLength) {
                System.arraycopy(matrix[i], 0, temp, 0, matrix[i].length);
                matrix[i] = temp.clone();
            }
        }
        
        return matrix;
    }
    
    /**
     * Returns the truncation of an array to the specified order.
     * @param arr the array to truncate
     * @param m the number of rows of the truncated array
     * @param n the number of columns of the truncated array
     * @return 
     */
    private double[][] truncate(double[][] arr, int m, int n) {
        double[][] tArr = new double[m][n];
        for (int i = 0 ; i < m ; i++) {
            System.arraycopy(arr[i], 0, tArr[i], 0, n);
        }
        return tArr;
    }
}

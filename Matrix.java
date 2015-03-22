/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import arr.Arrayer;
import java.util.Arrays;
import java.util.Objects;

/**
 * A rectangular array of numbers.
 *
 * @author Thurman
 */
public class Matrix {

    /**
     * The internal array storing the elements of the matrix.
     */
    double[][] matrix;

    /**
     * The order of the matrix (which contains its number of rows and columns).
     */
    Order r;

    /**
     * Constructs a matrix based on the specified array.
     *
     * @param matrix a double 2D array representing a matrix
     */
    public Matrix(double[][] matrix) {
        this(matrix, matrix.length, matrix[Arrayer.maxRow(matrix)].length);
    }

    /**
     * Constructs a zero matrix based the specified order.
     *
     * @param m the rows of the matrix
     * @param n the columns of the matrix
     */
    public Matrix(int m, int n) {
        this(MatrixMath.zero(m, n), m, n);
    }

    /**
     * Constructs a matrix filled with the specified entry and of the 
     * specified order.
     *
     * @param entry the entry to fill the matrix with
     * @param m the number of rows of the matrix
     * @param n the number of columns of the matrix
     */
    public Matrix(double entry, int m, int n) {
        this(Arrayer.fill(new double[m][n], entry), m, n);
    }

    /**
     * Constructs a matrix based on the specified array and order.
     *
     * <p>
     * If the order is not equivalent to the array's order, truncation will
     * begin from the end row and column of the array.
     *
     * @param matrix
     * @param m
     * @param n
     */
    public Matrix(double[][] matrix, int m, int n) {
        this(matrix, new Order(m, n));
    }

    public Matrix(double[][] matrix, Order r) {
        matrix = Arrayer.truncate(matrix, r.m, r.n);
        matrix = Arrayer.rectangular(matrix);
        this.matrix = matrix;
        this.r = r;
    }
    
    /**
     * Returns the number of rows of the matrix.
     * @return the number of columns of this matrix.
     */
    public int rows() {
        return r.m;
    }
    
    /**
     * Returns the number of columns of this matrix.
     * @return the number of columns of this matrix.
     */
    public int cols() {
        return r.n;
    }
    
    public Matrix.Order order() {
        return this.r;
    }
    
    public double entry(int m, int n) {
        return this.matrix[m - 1][m - 1];
    }
    
    public void add(Matrix m) {
        for (int i = 0 ; i < rows() ; i++) {
            for (int j = 0 ; j < cols() ; j++) {
                matrix[i][j] += m.matrix[i][j];
            }
        }
    }
    
    public void subtract(Matrix m) {
        for (int i = 0 ; i < rows() ; i++) {
            for (int j = 0 ; j < cols() ; j++) {
                matrix[i][j] -= m.matrix[i][j];
            }
        }
    }
    
    public void multiply(Matrix m) {
        double[][] nMatrix = new double[this.rows()][m.cols()];
        for (int i = 0; i < this.rows(); i++)
            for (int j = 0; j < m.cols(); j++)
                for (int k = 0; k < m.cols(); k++)
                    nMatrix[i][j] += (this.matrix[i][k] * m.matrix[k][j]);
        this.matrix = nMatrix;
    }
    
    public boolean orderEquals(Matrix m) {
        return this.order().equals(m.order());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Matrix)) return false;
        if (! this.orderEquals((Matrix) o)) return false;
        if (! (this.hashCode() == o.hashCode())) return false;
        for (int i = 0 ; i < rows() ; i++) {
            for (int j = 0 ; j < cols() ; j++) {
                if (Double.compare(this.matrix[i][j], ((Matrix) o).matrix[i][j]) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.matrix);
        hash = 23 * hash + Objects.hashCode(this.r);
        return hash;
    }
    
    /**
     * Represents the size of a matrix by its number of rows and 
     * columns.
     */
    public static class Order {

        /**
         * The number of rows of the matrix represented by this order.
         */
        final int m;

        /**
         * The number of columns of the matrix represented by this 
         * order.
         */
        final int n;
        
        /**
         * Constructs an order with the specified row and column sizes.
         * @param m the rows of the matrix
         * @param n the columns of the matrix
         */
        public Order(int m, int n) {
            this.m = m;
            this.n = n;
        }
        
        public boolean equals(Order o) {
            return this.m == o.m && this.n == o.n;
        }
        
        @Override
        public boolean equals(Object o) {
            if (! (o instanceof Order)) return false;
            return this.equals((Order) o);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * hash + this.m;
            hash = 17 * hash + this.n;
            return hash;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;
import java.util.Objects;

/**
 * A rectangular array of scalars.
 *
 * <p>
 * Note that index counting in matrices is traditionally one-based. This
 * convention is respected by all methods in this class.
 * 
 * <p>
 * This class's implementation of matrix manipulation (arithmetic and 
 *
 * @author Thurman
 */
public class Matrix {

    /**
     * The internal 2D array storing the elements of the matrix.
     */
    double[][] matrix;

    /**
     * The order of the matrix (which contains its number of rows and columns).
     */
    Order order;

    /**
     * Constructs a matrix based on the specified 2D array.
     *
     * @param arr a double 2D array representing a matrix
     */
    public Matrix(double[][] arr) {
        this(arr, arr.length, arr[ArrayUtils.maxRow(arr)].length);
    }
    
    /**
     * Constructs a zero matrix based the specified order.
     *
     * @param row the rows of the matrix
     * @param col the columns of the matrix
     */
    public Matrix(int row, int col) {
        this(new Order(row, col));
    }

    public Matrix(Order order) {
        this.matrix = new double[order.row][order.col];
        this.order = order;
    }
    
    /**
     * Constructs a matrix filled with the specified entry and of the specified
     * order.
     *
     * @param entry the entry to fill the matrix with
     * @param row the number of rows of the matrix
     * @param col the number of columns of the matrix
     */
    public Matrix(double entry, int row, int col) {
        this.matrix = ArrayUtils.fill(row, col, entry);
        order = new Order(row, col);
    }

    /**
     * Constructs a matrix based on the specified array and order.
     *
     * <p>
     * If the order is not equivalent to the array's order, truncation will
     * begin from the end row and column of the array.
     *
     * @param arr
     * @param row
     * @param col
     */
    public Matrix(double[][] arr, int row, int col) {
        this(arr, new Order(row, col));
    }

    /**
     * Constructs a matrix based on the specified array and order.
     *
     * <p>
     * If the order is not equivalent to the array's actual order, truncation
     * will begin from the end row and column of the array.
     *
     * @param arr
     * @param order
     */
    public Matrix(double[][] arr, Order order) {
        matrix = ArrayUtils.resize(arr, order.row, order.col);
        this.matrix = matrix;
        this.order = order;
    }
    
    /**
     * Constructs a matrix based on another matrix.
     * @param M the matrix to copy
     */
    public Matrix(Matrix M) {
        this.matrix = M.matrix.clone();
        this.order = new Order(M.order);
    }
    
    /**
     * Changes the matrix and order.
     *
     * <p>
     * It is <b>strongly</b> recommended that this method be used rather than
     * directly manipulating the array (except in the constructor).
     * 
     * <p>
     * Note that these methods are for internal use only.
     * @param M the matrix for this to change to
     */
    private void setMatrix(Matrix M) {
        this.matrix = M.matrix;
        this.order = M.order;
    }
    
    /**
     * Changes the matrix and order.
     *
     * <p>
     * It is <b>strongly</b> recommended that this method be used rather than
     * directly manipulating the array (except in the constructor).
     *
     * @param arr a double 2D array representing a matrix
     */
    private void setMatrix(double[][] arr) {
        setMatrix(arr, new Order(arr));
    }
    
    /**
     * Changes the matrix and order.
     *
     * <p>
     * It is <b>strongly</b> recommended that this method be used rather than
     * directly manipulating the array (except in the constructor).
     *
     * @param arr a double 2D array representing a matrix
     * @param row the number of rows of the new matrix
     * @param col the number of columns of the new matrix
     */
    private void setMatrix(double[][] arr, int row, int col) {
        setMatrix(arr, new Order(row, col));
    }
    
    /**
     * Changes the matrix and order.
     *
     * <p>
     * It is <b>strongly</b> recommended that this method be used rather than
     * directly manipulating the array (except in the constructor).
     *
     * @param arr a double 2D array representing a matrix
     */
    private void setMatrix(double[][] arr, Order order) {
        setMatrix(new Matrix(arr, order));
    }

    /**
     * Returns the number of rows of the matrix.
     *
     * @return the number of columns of this matrix.
     */
    public int rows() {
        return order.row;
    }

    /**
     * Returns the number of columns of this matrix.
     *
     * @return the number of columns of this matrix.
     */
    public int cols() {
        return order.col;
    }

    /**
     * Returns the order representing the size of this matrix.
     *
     * <p>
     * Note that although the fields of the order are publicly accessible, they
     * cannot be changed.
     *
     * @return the order representing the size of this matrix
     */
    public Order order() {
        return this.order;
    }

    /**
     * Returns the entry at the specified order.
     *
     * @param order the location of the entry
     * @return the targeted entry in the matrix
     */
    public double entry(Order order) {
        return entry(order.row, order.col);
    }

    /**
     * Returns the entry at the specified order.
     *
     * <p>
     * Note that index counting in matrices is traditionally one-based. This
     * convention is respected in all methods in this class.
     *
     * @param row the row of the entry
     * @param col the column of the entry
     * @return the targeted entry in the matrix
     */
    public double entry(final int row, final int col) {
        return this.matrix[row - 1][col - 1];
    }

    /**
     * Sets the entry at the specified order to a specified value.
     *
     * @param order the desired location for the entry
     * @param a the value to set the entry to
     */
    public void setEntry(Order order, double a) {
        setEntry(order.row, order.col, a);
    }

    /**
     * Sets the entry at the specified order toa specified value.
     *
     * @param row the desired row for the entry
     * @param col the desired column for the entry
     * @param a the value to set the entry to
     */
    public void setEntry(int row, int col, double a) {
        matrix[row - 1][col - 1] = a;
    }
    
    /**
     * Sets this matrix to the sum of itself and a specified matrix.
     *
     * @param M a matrix of the same order
     */
    public void add(Matrix M) {
        setMatrix(this.plus(M));
    }

    /**
     * Returns the sum of this and another matrix.
     * 
     * @param M a matrix of the same order
     * @return the sum of this and the argument matrix
     */
    public Matrix plus(Matrix M) {
        if (! this.orderEquals(M)) throw new IllegalArgumentException();
        Matrix sum = new Matrix(rows(), cols());
        for (int r = 0 ; r < rows() ; r++) {
            for (int c = 0 ; c < cols() ; c++) {
                sum.matrix[r][c] = this.matrix[r][c] + M.matrix[r][c];
            }
        }
        return sum;
    }
    
    /**
     * Sets this matrix to the difference of itself and a specified matrix.
     *
     * @param M a matrix of the same order
     */
    public void subtract(Matrix M) {
        setMatrix(this.minus(M));
    }
    
    /**
     * Returns the difference of this and another matrix.
     * 
     * @param M a matrix of the same order
     * @return the difference of this and the argument matrix 
     */
    public Matrix minus(Matrix M) {
        if (! this.orderEquals(M)) throw new IllegalArgumentException();
        Matrix diff = new Matrix(rows(), cols());
        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < cols(); c++) {
                diff.matrix[r][c] = this.matrix[r][c] - M.matrix[r][c];
            }
        }
        return diff;
    }
    
    /**
     * Sets this matrix to the product of itself and a specified matrix.
     *
     * @param M a matrix with the same row order as this matrix's column order
     */
    public void multiply(Matrix M) {
        setMatrix(this.times(M));
    }
    
    /**
     * Returns the product of this and another matrix.
     * @param M a matrix with the same row order as this matrix's column order
     * @return the product of this and another matrix
     */
    public Matrix times(Matrix M) {
        if (! this.orderEquals(M)) throw new IllegalArgumentException();
        Matrix product = new Matrix(this.rows(), M.cols());
        for (int r = 0; r < this.rows(); r++) {
            for (int c = 0; c < M.cols(); c++) {
                for (int i = 0; i < M.cols(); i++) {
                    product.matrix[r][c] += (this.matrix[r][i] * M.matrix[i][c]);
                }
            }
        }
        return product;
    }
    
    /**
     * Sets this matrix to the product of itself and a specified scalar.
     *
     * @param k a scalar
     */
    public void multiply(double k) {
        setMatrix(this.times(k));
    }
    
    /**
     * Sets this matrix to the product of itself and a specified scalar.
     *
     * @param k a scalar
     * @return the product of this and the scalar
     */
    public Matrix times(double k) {
        Matrix product = new Matrix(this.rows(), this.cols());
        for (int r = 0; r < this.rows(); r++) {
            for (int c = 0; c < this.cols(); c++) {
                    product.matrix[r][c] = k * this.matrix[r][c];               
            }
        }
        return product;
    }
    
    public Vector row(int row) {
        return new Vector(matrix[row - 1]);
    }
    
    /**
     * @
     * @param col
     * @return 
     */
    public Vector col(int col) {
        return null;
    }
    
    /**
     * Performs the first matrix row operation : swapping two rows.
     * @param row1 a row to swap
     * @param row2 a row to swap
     */
    public void r1(int row1, int row2) {
        ArrayUtils.swap(matrix, row1, row2);
    }
    
    /**
     * Performs the second matrix row operation : multiplying a row
     * by a (nonzero) constant.
     * @param row1 the row to multiply
     * @param k a constant to multiply by
     */
    public void r2(int row1, double k) {
        for (int c = 0 ; c < order.col ; c++) {
            matrix[row1][c] *= k;
        }
    }
    
    /**
     * Performs the third row operation : adding a (nonzero) multiple
     * of a row to another.
     * @param row1 the row to add to
     * @param row2 the row whose multiple is to be added
     * @param k a constant to multiply the addend by
     */
    public void r3(int row1, int row2, double k) {
        for (int c = 0; c < order.col; c++) {
            matrix[row1][c] += k * matrix[row2][c];
        }
    }
       
    /**
     * Performs the first matrix column operation : swapping two 
     * columns.
     * @param col1 a column to swap
     * @param col2 a column to swap
     */
    public void c1(int col1, int col2) {
        ArrayUtils.swap(matrix, col1, col2);
    }
    
    /**
     * Performs the second matrix column operation : multiplying a 
     * column by a (nonzero) constant.
     * @param col1 the column to multiply
     * @param k a constant to multiply by
     */
    public void c2(int col1, double k) {
        for (int c = 0 ; c < order.col ; c++) {
            matrix[col1][c] *= k;
        }
    }
    
    /**
     * Performs the third column operation : adding a (nonzero) 
     * multiple of a column to another.
     * @param col1 the column to add to
     * @param col2 the column whose multiple is to be added
     * @param k a constant to multiply the addend by
     */
    public void c3(int col1, int col2, double k) {
        for (int c = 0; c < order.col; c++) {
            matrix[col1][c] += k * matrix[col2][c];
        }
    }   
    
    /**
     * Transposes this matrix.
     */
    public void transpose() {
        setMatrix(this.transposition());
    }
    
    /**
     * Returns the transposition of this matrix.
     *
     * @return the transposition of this matrix
     */
    public Matrix transposition() {
        double[][] arr = new double[rows()][cols()];
        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < cols(); c++) {
                arr[c][r] = this.matrix[r][c];
            }
        }
        return new Matrix(arr);
    }
     
    public Matrix ref() {
        return null;
    }
    
    /**
     * Returns the determinant of this matrix.
     *
     * @return the determinant of this matrix
     */
    public double determinant() {
        return 0;
    }
    
    /**
     * Returns the minor submatrix of the specified entry, obtained 
     * when removing that entry's row and column from the matrix.
     *
     * @param row the row of the minor's entry
     * @param col the column of the minor's entry
     * @return the minor submatrix of an entry in this matrix
     */
    public Matrix minorMatrix(int row, int col) {
        row--;
        col--;

        double[][] minor = new double[rows() - 1][cols() - 1];

        for (int r = 0; r < row; r++) {
            System.arraycopy(matrix[r], 0, minor[r], 0, col);
            System.arraycopy(matrix[r], col + 1, minor[r], col, cols() - col - 1);
        }

        for (int r = row + 1; r < rows(); r++) {
            System.arraycopy(matrix[r], 0, minor[r - 1], 0, col);
            System.arraycopy(matrix[r], col + 1, minor[r - 1], col, cols() - col - 1);
        }

        return new Matrix(minor);
    }

    /**
     * Returns the minor determinant of a specified entry.
     *
     * @param order the order of the entry
     * @return the minor determinant of an entry in this matrix
     */
    public double minor(Order order) {
        return minor(order.row, order.col);
    }

    /**
     * Returns the minor determinant of a specified entry.
     *
     * @param row the row of the minor's entry
     * @param col the column of the minor's entry
     * @return the minor determinant of an entry in this matrix
     */
    public double minor(int row, int col) {
        return minorMatrix(row, col).determinant();
    }
    
    /**
     * Returns the cofactor determinant of a specified entry.
     * 
     * @param order the order of an entry
     * @return the cofactor determinant of an entry in this matrix 
     */
    public double cofactor(Order order) {
        return minor(order.row, order.col);
    }
    
    /**
     * Returns the cofactor determinant of a specified entry.
     * @param row the row of the cofactor's entry
     * @param col the column of the cofactor's entry
     * @return the cofactor determinant of an entry in this matrix 
     */
    public double cofactor(int row, int col) {
        return DeterminantUtils.negOnePow(row + col) * minor(row, col);
    }
    
    /**
     * Returns the cofactor matrix of this matrix.
     * 
     * <p><i>Note that this has an entirely separate meaning from 
     * {@link minorMatrix(int row, int col)}!</i> This will return a matrix
     * containing the calculated cofactors of <i>each</i> entry, while the 
     * latter will simply return a submatrix of the original matrix.
     * @return the cofactor matrix of this matrix
     */   
    public Matrix cofactorMatrix() {
        Matrix cofactorM = new Matrix(rows(), cols());
        for (int r = 0 ; r < rows() ; r++) {
            for (int c = 0 ; c < cols() ; c++) {
                cofactorM.matrix[r][c] = this.cofactor(r, c);
            }
        }
        return cofactorM;
    }
    
    /**
     * Returns the adjugate of this matrix.
     * 
     * <p>The adjugate matrix [A] has such a property that when multiplied by
     * the determinant |M| of the original square invertible matrix, it will 
     * equal M^-1, the inverse of M.
     * @return 
     */
    public Matrix adjugate() {
        return cofactorMatrix().transposition();
    }
    
    /**
     * Checks if the order - that is, the size - of this matrix is equal 
     * to another's.
     *
     * @param row a matrix to compare this to
     * @return {@code true} if and only if the order of the matrix is equal the
     * argument's and {@code false} otherwise
     */
    public boolean orderEquals(Matrix M) {
        return this.order.equals(M.order);
    }

    /**
     * Checks if two matrices are equal.
     *
     * <p>
     * Two matrices are defined to be equal if and only if their corresponding 
     * elements are equivalent, and no other elements exist.
     *
     * @param o an object to equate this to
     * @return {@code true} if and only if this is equal to the argument and
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }
        if (!(this.hashCode() == o.hashCode())) {
            return false;
        }
        return Arrays.deepEquals(this.matrix, ((Matrix) o).matrix);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.matrix);
        hash = 23 * hash + Objects.hashCode(this.order);
        return hash;
    }

    /**
     * Returns a string representation of this matrix.
     *
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (double[] row : this.matrix) {
            str += row[0];
            for (int r = 1; r < row.length; r++) {
                str += " " + row[r];
            }
            str += "\n";
        }
        return str;
    }
    
    public String toConsoleString() {
        String str = "";
        for (double[] row : this.matrix) {
            str += row[0];
            for (int r = 1; r < row.length; r++) {
                str += " " + row[r];
            }
            str += "\n";
        }
        return str;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;
import java.util.Objects;
import static console.print.Printer.*;
import java.util.ArrayList;

/**
 * A rectangular array of scalars.
 *
 * <p>
 * Note that index counting in matrices is traditionally one-based. This
 * convention is respected by all methods in this class.
 *
 * <p>
 * Methods in this class do not permit {@code null} values.
 * @author Thurman
 */
public class Matrix {

    static final double DEFAULT_VALUE = 0.0;

    /**
     * The internal 2D array storing the elements of the matrix.
     */
    double[][] matrix;

    /**
     * The getOrder of the matrix (which contains its number of getRows and
 columns).
     */
    Order order;
    
    void init() {
        
    }
    
    /**
     * Checks the initial restrictions of the construction of a matrix are
     * satisfied. It is designed to be overridden so as to allow subclasses to
     * delineate restrictions of that class's specific implementation of 
     * {@code Matrix}.
     * @param arr 
     * @throws java.lang.NullPointerException - if any row of the matrix is 
     * {@code null}
     */
    protected void init(double[][] arr) {
        requireNonNullParams((Object[]) arr);
    }
    
    protected void init(Order order) {
        requireNonNullParams(order);
    }
    protected void init(double[][] arr, Order order) {
        requireNonNullParams(arr, order);
    }
    
    protected void init(Matrix M) {
        requireNonNullParams(M);
    }
    
    /**
     * Constructs a matrix based on the specified 2D array.
     *
     * @param arr a double 2D array forming this matrix
     */
    public Matrix(double[][] arr) {
        init(arr);
        this.order = new Order(arr.length, ArrayUtils.maxRowLen(arr));
        this.matrix = ArrayUtils.resize(arr, order.rows, order.cols);
    }

    /**
     * Constructs a zero matrix based on the specified getRows and columns.
     *
     * @param rows the getRows of the matrix
     * @param cols the columns of the matrix
     */
    public Matrix(int rows, int cols) {
        this(new Order(rows, cols));
    }

    /**
     * Constructs a zero matrix based on the specified getOrder.
     *
     * @param order the getOrder of the matrix
     */
    public Matrix(Order order) {
        init(order);
        this.matrix = new double[order.rows][order.cols];
        this.order = new Order(order.rows, order.cols);
    }

    /**
     * Constructs a matrix filled with the specified entry and of the specified
     * order.
     *
     * @param entry the getEntry to fill this matrix with
     * @param rows the number of rows of this matrix
     * @param cols the number of columns of this matrix
     */
    public Matrix(double entry, int rows, int cols) {
        this(entry, new Order(rows, cols));
    }
    
    public Matrix(double entry, Order order) {
        this.matrix = ArrayUtils.fill(order.rows, order.cols, entry);
        this.order = new Order(order);
    }
    
    /**
     * Constructs a matrix based on the specified array and getOrder.
     *
     * <p>
     * If the getOrder is not equivalent to the array's getOrder, truncation
     * will begin from the end getRow and column of the array.
     *
     * @param arr
     * @param rows
     * @param cols
     */
    public Matrix(double[][] arr, int rows, int cols) {
        requireNonNullParams((Object) arr);
        this.order = new Order(rows, cols);
        this.matrix = ArrayUtils.resize(arr, order.rows, order.cols);

    }

    /**
     * Constructs a matrix based on the specified array and getOrder.
     *
     * <p>
     * If the getOrder is not equivalent to the array's actual getOrder,
     * truncation will begin from the end getRow and column of the array.
     *
     * @param arr
     * @param order
     */
    public Matrix(double[][] arr, Order order) {
        init(arr, order);
        this.matrix = ArrayUtils.resize(arr, order.rows, order.cols);
        this.order = order;
    }

    /**
     * Constructs a matrix based on another matrix.
     *
     * @param M the matrix to copy
     */
    public Matrix(Matrix M) {
        init(M);
        this.matrix = M.matrix.clone();
        this.order = new Order(M.order);
    }

    /**
     * Changes the matrix and getOrder without performing the rigorous tests of
     * the constructors.
     *
     * <p>
     * Note that this method and any overloaded variants are for internal use
     * only.
     *
     * @param M the matrix for this to change to
     */
    private void internalUncheckedSet(double[][] arr) {
        internalUncheckedSet(arr, new Order(arr.length, arr[0].length));
    }

    /**
     * Changes the matrix and getOrder without performing the rigorous tests of
     * the constructors.
     *
     * <p>
     * Note that this method and any overloaded variants are for internal use
     * only.
     *
     * @param M the matrix for this to change to
     */
    private void internalUncheckedSet(Matrix M) {
        internalUncheckedSet(M.matrix, M.order);
    }

    /**
     * Changes the matrix and getOrder without performing the rigorous tests of
     * the constructors.
     *
     * <p>
     * Note that this method and any overloaded variants are for internal use
     * only.
     *
     * @param M the matrix for this to change to
     */
    private void internalUncheckedSet(double[][] arr, Order order) {
        Objects.requireNonNull(arr);
        Objects.requireNonNull(order);
        this.matrix = arr;
        this.order = order;
    }

    /**
     * Returns the number of rows of this matrix.
     *
     * @return the number of columns of this matrix.
     */
    public int getRows() {
        return order.rows;
    }

    /**
     * Returns the number of columns of this matrix.
     *
     * @return the number of columns of this matrix.
     */
    public int getCols() {
        return order.cols;
    }

    /**
     * Returns the getOrder representing the size of this matrix.
     *
     * <p>
     * Note that although the fields of the getOrder are publicly accessible,
     * they cannot be changed.
     *
     * @return the getOrder representing the size of this matrix
     */
    public Order getOrder() {
        return this.order;
    }
    
    /**
     * Returns the entry at the specified getOrder.
     *
     * <p>
     * Note that index counting in matrices is traditionally one-based. This
     * convention is respected in all methods in this class.
     *
     * @param row the getRow of the getEntry
     * @param col the column of the getEntry
     * @return the targeted getEntry in the matrix
     */
    public double getEntry(final int row, final int col) {
        return this.matrix[row - 1][col - 1];
    }
    
    /**
     * Returns the getEntry at the specified getOrder.
     *
     * @param order the location of the getEntry
     * @return the targeted getEntry in the matrix
     */
    public double getEntry(Order order) {
        return getEntry(order.rows, order.cols);
    }

    /**
     * Returns a copy of the specified getRows vector.
     *
     * @param row the getRow getOrder of the vector
     * @return a getRow vector of this matrix
     */
    public Vector getRow(int row) {
        return new Vector(matrix[row - 1]);
    }

    /**
     * Returns a copy of the specified column vector.
     *
     * @param col the column getOrder of the vector
     * @return a column vector of this matrix
     */
    public Vector getCol(int col) {
        double[] column = new double[matrix[col - 1].length];
        for (int r = 0; r < matrix.length; r++) {
            column[r] = matrix[r][col - 1];
        }
        return new Vector(column);
    }

    /**
     * Sets the getEntry at the specified getOrder to a specified value.
     *
     * @param order the desired location for the getEntry
     * @param a the value to set the getEntry to
     */
    public void setEntry(Order order, double a) {
        setEntry(order.rows, order.cols, a);
    }

    /**
     * Sets the getEntry at the specified getOrder toa specified value.
     *
     * @param row the desired getRow for the getEntry
     * @param col the desired column for the getEntry
     * @param a the value to set the getEntry to
     */
    public void setEntry(int row, int col, double a) {
        matrix[row - 1][col - 1] = a;
    }
    
    public void setRow(int row, double[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length != this.getOrder().rows) 
            throw new IllegalArgumentException("Cannot set row to different dimension");
        for (int c = 0 ; c < arr.length ; c++) {
            setEntry(new Order(row, c), arr[c]);
        }
    }
    
    public void setCol(int col, double[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length != this.getOrder().rows) 
            throw new IllegalArgumentException("Cannot set column to different dimension");
        for (int r = 0; r < 10; r++) {
            setEntry(new Order(r, col), arr[r]);
        }
    }
    
    /**
     * Sets this matrix to the sum of itself and a specified matrix.
     *
     * @param M a matrix of the same getOrder
     */
    public void add(Matrix M) {
        orderCheck(M);
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                this.matrix[r][c] += M.matrix[r][c];
            }
        }
    }

    /**
     * Returns the sum of this and another matrix.
     *
     * @param M a matrix of the same getOrder
     * @return the sum of this and the argument matrix
     */
    public Matrix plus(Matrix M) {
        orderCheck(M);
        Matrix sum = new Matrix(getRows(), getCols());
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                sum.matrix[r][c] = this.matrix[r][c] + M.matrix[r][c];
            }
        }
        return sum;
    }

    /**
     * Sets this matrix to the difference of itself and a specified matrix.
     *
     * @param M a matrix of the same getOrder
     */
    public void subtract(Matrix M) {
        orderCheck(M);
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                this.matrix[r][c] -= M.matrix[r][c];
            }
        }
    }

    /**
     * Returns the difference of this and another matrix.
     *
     * @param M a matrix of the same getOrder
     * @return the difference of this and the argument matrix
     */
    public Matrix minus(Matrix M) {
        orderCheck(M);
        Matrix diff = new Matrix(getRows(), getCols());
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                diff.matrix[r][c] = this.matrix[r][c] - M.matrix[r][c];
            }
        }
        return diff;
    }

    /**
     * Sets this matrix to the product of itself and a specified matrix.
     *
     * @param M a matrix whose number of getRows equal this matrix's number of
 columns
     */
    public void multiply(Matrix M) {
        internalUncheckedSet(this.times(M));
    }

    /**
     * Returns the product of this and another matrix.
     *
     * @param M a matrix with the same getRow count as this matrix's columns
     * @return the product of this and another matrix
     */
    public Matrix times(Matrix M) {
        if (this.getCols() != M.getRows()) {
            throw new IllegalArgumentException(
                    "Row count of argument not equal to the column count of the calling matrix");
        }
        Matrix product = new Matrix(this.getRows(), M.getCols());
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < M.getCols(); c++) {
                for (int i = 0; i < M.getCols(); i++) {
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
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                this.matrix[r][c] *= k;
            }
        }
    }

    /**
     * Sets this matrix to the product of itself and a specified scalar.
     *
     * @param k a scalar
     * @return the product of this and the scalar
     */
    public Matrix times(double k) {
        Matrix product = new Matrix(this.getRows(), this.getCols());
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                product.matrix[r][c] = k * this.matrix[r][c];
            }
        }
        return product;
    }

    /**
     * Transposes this matrix.
     */
    public void transpose() {
        internalUncheckedSet(this.transposition());
    }

    /**
     * Returns the transposition of this matrix.
     *
     * @return the transposition of this matrix
     */
    public Matrix transposition() {
        double[][] arr = new double[getRows()][getCols()];
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
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
     * Returns the minor submatrix of the specified getEntry, obtained when
     * removing that getEntry's getRow and column from the matrix.
     *
     * @param row the getRow of the minor's getEntry
     * @param col the column of the minor's getEntry
     * @return the minor submatrix of an getEntry in this matrix
     */
    public Matrix minorMatrix(int row, int col) {
        row--;
        col--;

        double[][] minor = new double[getRows() - 1][getCols() - 1];

        for (int r = 0; r < row; r++) {
            System.arraycopy(matrix[r], 0, minor[r], 0, col);
            System.arraycopy(matrix[r], col + 1, minor[r], col, getCols() - col - 1);
        }

        for (int r = row + 1; r < getRows(); r++) {
            System.arraycopy(matrix[r], 0, minor[r - 1], 0, col);
            System.arraycopy(matrix[r], col + 1, minor[r - 1], col, getCols() - col - 1);
        }

        return new Matrix(minor);
    }

    /**
     * Returns the minor determinant of a specified getEntry.
     *
     * @param order the getOrder of the getEntry
     * @return the minor determinant of an getEntry in this matrix
     */
    public double minor(Order order) {
        return minor(order.rows, order.cols);
    }

    /**
     * Returns the minor determinant of a specified getEntry.
     *
     * @param row the getRow of the minor's getEntry
     * @param col the column of the minor's getEntry
     * @return the minor determinant of an getEntry in this matrix
     */
    public double minor(int row, int col) {
        return minorMatrix(row, col).determinant();
    }

    /**
     * Returns the cofactor determinant of a specified getEntry.
     *
     * @param order the getOrder of an getEntry
     * @return the cofactor determinant of an getEntry in this matrix
     */
    public double cofactor(Order order) {
        return minor(order.rows, order.cols);
    }

    /**
     * Returns the cofactor determinant of a specified getEntry.
     *
     * @param row the getRow of the cofactor's getEntry
     * @param col the column of the cofactor's getEntry
     * @return the cofactor determinant of an getEntry in this matrix
     */
    public double cofactor(int row, int col) {
        return DeterminantUtils.negOnePow(row + col) * minor(row, col);
    }

    /**
     * Returns the cofactor matrix of this matrix.
     *
     * <p>
     * <i>Note that this has an entirely separate meaning from
     * {@link minorMatrix(int row, int col)}!</i> This will return a matrix
     * containing the calculated cofactors of <i>each</i> getEntry, while the
     * latter will simply return a submatrix of the original matrix.
     *
     * @return the cofactor matrix of this matrix
     */
    public Matrix cofactorMatrix() {
        Matrix cofactorM = new Matrix(getRows(), getCols());
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                cofactorM.matrix[r][c] = this.cofactor(r, c);
            }
        }
        return cofactorM;
    }

    
    public final boolean isVector() {
        return this.getRows() == 1;
    }

    /**
     * Checks if this matrix is square (its row order is equal to its column
     * order.)
     * @return {@code true} if and only if the orders are equal 
     */
    public final boolean isSquare() {
        return this.getRows() == this.getCols();
    }
    
    public final SquareMatrix toSquare() {
        return new SquareMatrix(this.matrix, this.order);
    }
    
    /**
     * Checks if the order of this matrix is equal to another's.
     *
     * @param M a matrix to compare with
     * @return {@code true} if and only if the order of this matrix is equal
     * the argument's and {@code false} otherwise
     */
    public final boolean isOrderOf(Matrix M) {
        return this.getOrder().equals(M.getOrder());
    }
    
    /**
     * Checks if the order is equal to another's, and throws a {@code 
     * java.lang.IllegalArgumentException} if so, using the internal method
     * {@code unequalOrderMsg(Matrix M)} with the argument matrix as a 
     * parameter.
     * @param M a matrix to compare with
     * @throws java.lang.IllegalArgumentException if {@code isOrderOf(Matrix M}
     * is {@code false}
     */
    private void orderCheck(Matrix M) {
        if (!this.isOrderOf(M)) {
            throw new IllegalArgumentException(unequalOrderMsg(M));
        }
    }
    
    static final String unequalOrderMsg() {
        return "Argument matrix order unequal to this matrix's order";
    }

    final String unequalOrderMsg(Matrix M) {
        return unequalOrderMsg() + String.format(", expected: %s, was: %s", this.getOrder(), M.getOrder());
    }
    
    static final String nullMatrixCreationMsg() {
        return "Attempted creation of null (or containing-nulls) matrix";
    }
    
    @SuppressWarnings("varargs")
    protected static void requireNonNullParams(Object... params) {
        for (Object param : params) {
            Objects.requireNonNull(param, nullMatrixCreationMsg());
        }
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

    @Override
    public Matrix clone() {
        return new Matrix(this);
    }
}

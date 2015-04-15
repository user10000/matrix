/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author Guest
 */
public class SquareMatrix extends Matrix {
    
    @Override
    protected void init(double[][] arr) {
        
    }
    
    @Override
    protected void init(Order order) {
        if (order.rows != order.cols) throw new IllegalArgumentException();
        super.init(order);
    }
    
    public SquareMatrix(int size) {
        super(size, size);
    }

    public SquareMatrix(double[][] arr) {
        super(arr);
    }

    public SquareMatrix(int row, int col) {
        super(row, col);
    }

    public SquareMatrix(Order order) {
        super(order);
    }

    public SquareMatrix(double entry, int rows, int cols) {
        super(entry, rows, cols);
    }

    public SquareMatrix(double[][] arr, int rows, int cols) {
        super(arr, rows, cols);
    }

    public SquareMatrix(double[][] arr, Order order) {
        super(arr, order);
    }

    public SquareMatrix(Matrix M) {
        super(M);
    }
    
    
    /**
     * Returns the adjugate of this matrix.
     *
     * <p>
     * The adjugate matrix [A] has such a property that when multiplied by the
     * determinant |M| of the original square invertible matrix, it will equal
     * M^-1, the inverse of M.
     *
     * @return
     */
    public Matrix adjugate() {
        return cofactorMatrix().transposition();
    }

}

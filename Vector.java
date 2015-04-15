/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 * A list of a scalars which represent a path in a space (known
 * as a <i>vector space</i>).
 * 
 * 
 * @author Guest
 */
public class Vector extends Matrix {
    
    double[] vec;
    
    /**
     * Constructs a vector based on the specified array.
     * @param arr an array of doubles representing a vector
     */
    public Vector(double[] arr) {
        this(arr, arr.length);
    }
    
    /**
     * Constructs a zero vector based on the specified dimensions.
     * @param dimension the number of scalars representing this vector
     */
    public Vector(int dimension) {
        this(new double[dimension], dimension);
    }    
   
    /**
     * Constructs a vector filled with the specified element and of the
     * specified dimensions.
     * @param a the element to fill this vector
     * @param dimension the number of scalars representing this vector
     */
    public Vector(double a, int dimension) {
        this(ArrayUtils.fill(dimension, a), dimension);
    }
    
    /**
     * Constructs a vector based on the specified array and dimensions.
     * 
     * @param arr an array of doubles representing a vector
     * @param dimension the number of scalars representing this vector
     */
    public Vector(double[] arr, int dimension) {
        super(ArrayUtils.extend(arr), new Order(1, dimension));
        this.vec = matrix[0];
    }    
    
    public Vector(Vector v) {
        this(v.vec, v.length());
    }
    
    /**
     * Returns the dimensions of this vector.
     * @return the number of scalars representing this vector
     */
    public int length() {
        return order.rows;
    }
    
    /**
     * Changes this vector and and its dimensions.
     * 
     * @param v the vector for this to change to
     */
    private void setVector(Vector v) {
        this.vec = v.vec;
    }
    
    /**
     * Returns a vector representing this in the specified dimensions.
     * 
     * @param dimension the number of scalars to represent this vector
     * @return a vector representing this in another dimension
     */
    public Vector in(int dimension) {
        return new Vector(this.vec, dimension);
    }
    
    /**
     * Returns the magnitude in a specified dimension.
     * @param col a dimension this vector is in
     * @return the magnitude in a certain dimension
     */
    public double entry(int col) {
        return super.getEntry(1, col);
    }
    
    /**
     * Sets the magnitude in a specified dimension which this vector 
     * exists in.
     * @param col a dimension this vector is in 
     * @param a the magnitude of the vector in the dimension
     */
    public void setEntry(int col, double a) {
        super.setEntry(1, col, a);
    }
    
    @Override
    public void add(Matrix M) {
        setVector(this.plus(M));
    }
    
    /**
     * Returns the sum of this and another vector.
     * 
     * @param v a vector of the same getOrder
     * @return the sum of this and the argument vector
     */
    @Override
    public Vector plus(Matrix M) {
        return super.plus(M).getRow(1);
    }
    
    /**
     * Sets this vector to the difference of itself and a specified 
     * vector.
     *
     */
    @Override
    public void subtract(Matrix M) {
        setVector(this.minus(M));
    }
    
    /**
     * Returns the difference of this and another vector.
     * 
     * @return the difference of this and the argument vector 
     */
    @Override
    public Vector minus(Matrix M) {
        return super.minus(M).getRow(1);
    }
    
    /**
     * Returns the dot product of this and another vector.
     * 
     * <p>
     * This is equivalent to the product of two one-dimensional matrices
     * ({@link Matrix#multiply(Matrix M)}) expressed as a scalar.
     * @param v
     * @return 
     */
    public double dot(Vector v) {
        return MatrixMath.dot(this.vec, v.vec);
    }
    
    public Vector times(double k) {
        Vector vec = new Vector(this);
        for (int i = 0 ; i < this.length() ; i++) {
            this.vec[i] *= k;
        }
        return vec;
    }
    
    /**
     * Checks if the dimension of this vector is equal to another's.
     * @param v
     * @return 
     */
    public boolean dimensionEquals(Vector v) {
        return this.order.equals(v.order);
    }
    
    /**
     * Checks if the specified matrix is a vector.
     * @param M a matrix
     * @return {@code true} if a specified matrix is a vector
     */
    public boolean isVector(Matrix M) {
        return M.getRows() == 1;
    }
}

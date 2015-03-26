/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;

/**
 * A list of a scalars which represent a path in a space (known
 * as a <i>vector space</i>).
 * 
 * 
 * @author Guest
 */
public class Vector extends Matrix {
    
    /**
     * Constructs a vector based on the specified array.
     * @param arr an array of doubles representing a vector
     */
    public Vector(double[] arr) {
        this(arr, arr.length);
    }
    
    /**
     * Constructs a zero vector based on the specified dimension.
     * @param dimension the number of scalars representing this vector
     */
    public Vector(int dimension) {
        this(new double[dimension], dimension);
    }    
   
    /**
     * Constructs a vector filled with the specified element and of the
     * specified dimension
     * @param a the element to fill this vector
     * @param dimension the number of scalars representing this vector
     */
    public Vector(double a, int dimension) {
        this(ArrayUtils.fill(dimension, a), dimension);
    }
    
    /**
     * Constructs a vector based on the specified array and dimension.
     * 
     * @param arr an array of doubles representing a vector
     * @param dimension the number of scalars representing this vector
     */
    public Vector(double[] arr, int dimension) {
        super(ArrayUtils.extend(arr), new Order(1, dimension));
    }    
    
    /**
     * Returns the dimension of this vector.
     * @return the number of scalars representing this vector
     */
    public int dimension() {
        return order.row;
    }
    
    /**
     * Changes this vector and dimension.
     * 
     * @param v the vector for this to change to
     */
    public void setVector(Vector v) {
        matrix[0] = v.vec;
    }
    
    /**
     * Returns a vector representing this in the specified dimension.
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
        return super.entry(1, col);
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
     * @param v a vector of the same order
     * @return sum the sum of this and the argument vector
     */
    @Override
    public Vector plus(Matrix M) {
        return super.plus(M).row(1);
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
     * Returns the difference of this and another vector
     * 
     * @return the difference of this and the argument vector 
     */
    @Override
    public Vector minus(Matrix M) {
        return super.minus(M).row(1);
    }
    
    public void dot(Vector v) {
        
    }
    
    /**
     * Checks if the dimension of this vector is equal to another's.
     * @param v
     * @return 
     */
    public boolean dimensionEquals(Vector v) {
        return this.order.equals(v.order);
    }
}

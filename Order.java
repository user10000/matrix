/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Objects;

/**
 * Represents the dimensions (rows and column size) of a matrix or 
 entry.
 */
public class Order implements Cloneable {

    /**
     * The rows dimension.
     */
    final int rows;

    /**
     * The column dimension.
     */
    final int cols;

    /**
     * Constructs an order with the specified rows and columns.
     *
     * @param row the rows of the matrix
     * @param col the columns of the matrix
     */
    public Order(int row, int col) {
        this.rows = row;
        this.cols = col;
    }
    
    /**
     * Constructs an order based on the given 2D array.
     * @param arr a 2D array
     */
    public Order(double[][] arr) {       
        Objects.requireNonNull(arr);
        this.rows = arr.length;
        this.cols = ArrayUtils.maxRow(arr);
    }
    
    /**
     * Constructs an order equivalent to the specified order.
     * @param order the order to copy
     */
    public Order(Order order) {
        
        this.rows = order.rows;
        this.cols = order.cols;
    }

    /**
     * Checks if this is equal to another order.
     * 
     * <p>
     * Two orders are equal if and only if their rows and columns are 
     * respectively equal.
     * @param o the order to compare this to
     * @return {@code true} if this and another order are equal and {@code
     * false} otherwise
     */
    public boolean equals(Order o) {
        return this.rows == o.rows && this.cols == o.cols;
    }
    
    /**
     * Checks if this is equal to another object.
     * 
     * <p>
     * In order for the two objects to be equal, the argument must also be 
     * an order, in which case this statement will follow the value of {@link 
     * equals(Order o)}.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        return this.equals((Order) o);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.rows;
        hash = 17 * hash + this.cols;
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s x %s", rows, cols);
    }
    
    /**
     * Returns the order whose rows are equal to this order's columns,
     * and vice versa.
     * @return 
     */
    public Order inverse() {
        return new Order(this.cols, this.rows);
    }
    
    @Override
    public Order clone() throws CloneNotSupportedException {
        return new Order(this);
    }
}

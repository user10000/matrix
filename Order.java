/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 * Represents the dimensions (row and column size) of a matrix or 
 * entry.
 */
public class Order {

    /**
     * The number of rows of the matrix represented by this order.
     */
    final int row;

    /**
     * The number of columns of the matrix represented by this order.
     */
    final int col;

    /**
     * Constructs an order with the specified row and column.
     *
     * @param row the rows of the matrix
     * @param col the columns of the matrix
     */
    public Order(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Constructs an order based on the given 2D array.
     * @param arr a 2D array
     */
    public Order(double[][] arr) {
         this(arr.length, arr[ArrayUtils.maxRow(arr)].length);
    }
    
    /**
     * Constructs an order equivalent to the specified order.
     * @param order the order to copy
     */
    public Order(Order order) {
        this.row = order.row;
        this.col = order.col;
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
        return this.row == o.row && this.col == o.col;
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
        hash = 17 * hash + this.row;
        hash = 17 * hash + this.col;
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s x %s", row, col);
    }
    
    /**
     * Returns the order whose rows are equal to this order's columns,
     * and vice versa.
     * @return 
     */
    public Order inverse() {
        return new Order(this.col, this.row);
    }
}

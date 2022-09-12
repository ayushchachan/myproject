/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.LinkedList;

/**
 *
 * @author Ayush Chachan
 */
public class MyObject implements Comparable<MyObject>{
    /*fiels of  MyObject instance*/
    private int size;
    private int id;
    
    private MyBin bin;          //refernce to bin, in which this object is stored
    
    /**Constructs a new empty object*/
    public MyObject(int id, int size) {
       this.id = id;
       this.size = size;
    }
    /**returns the size of the object*/
    public int size() { return this.size;}
    
    /**returns the id of instance*/
    public int getObjectId() {return this.id;}
    
    /**updates the reference to bin */
    public void setBin(MyBin b) {
        this.bin = b;
    }
    
    /**return the reference to the bin object in which this object is stored*/
    public MyBin getBin() {return this.bin;}
    
    /**returns true if this object equals to other*/
    public boolean equals(Object other) {
        return ((MyObject)other).getObjectId() == this.getObjectId();
    }
    
    /**compare this with other MyObject instance*/
    public int compareTo(MyObject other) {
        if (this.getObjectId() == other.getObjectId()) return 0;
        else if (this.getObjectId() > other.getObjectId()) return 1;
        else return -1;
    }
    
    /**return a string representation of object*/
    public String toString() {
        return "(" + this.getObjectId() + ", " + this.size + ")";
    }
    
    @Override
    public int hashCode() {
        return this.getObjectId();
    }
}

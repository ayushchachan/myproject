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
public class MyBin implements Comparable<MyBin>{
    /*fiels of  MyObject instance*/
    private int capacity;
    private int id;
    private LinkedList<MyObject> objectList;
    
    
    /**Constructs a new empty bin*/
    public MyBin(int id, int initialCap) {
       this.id = id;
       this.capacity = initialCap;
       objectList = new LinkedList<>();
    }
    
    /**returns the capacity of the object*/
    public int getCapacity() { return this.capacity;}
    
    /**returns the id of instance*/
    public int getBinId() {return this.id;}
    
    /**adds an MyObject to the bin*/
    public void addObject(MyObject o) {
        
        if (this.capacity >= o.size()) {
            objectList.add(o);
            this.capacity = this.capacity - o.size();
        } else {
            System.out.println("Error: object " + o + " size is more than bin cpacity");
            throw new IllegalArgumentException("Error: object size is more than bin cpacity");
        }
    }
    
    /**removes object o from bin
     * throws an error if object o is not present in bin*/
    public void deleteObject(MyObject o) {
        for (MyObject obj : objectList) {
            if (obj.equals(o))  {
                objectList.remove(obj);
                return;
            }
            this.capacity = this.capacity + o.size();
        }
        throw new IllegalArgumentException("object o is not present in bin");
    }
    
    /**returns the List of MyObjects*/
    public LinkedList<MyObject> getObjectList() {
        return this.objectList;
    }
    /**returns true if this object equals to other*/
    public boolean equals(MyBin other) {
        return other.getBinId() == this.getBinId();
    }
    
    /**compare this with other MyObject instance*/
    public int compareTo(MyBin other) {
        if (this.getBinId() == other.getBinId()) return 0;
        else if (this.getBinId() > other.getBinId()) return 1;
        else return -1;
    }
    
    public String toString() {
        return "(" + this.getBinId() + ", " + this.getCapacity() + ")";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 *
 * @author Ayush Chachan
 */
public class Node {
    
    
    private int value;
    private Node next;
    
    
    //constructor1
    public Node(int i){
        value = i;
    }
    
    //condtructor2
    public Node( int v, Node n){
        value = v ;
        next = n;
        
    }
    
    //return the value stored in node
    public int getValue() {
        return value;
    }
    
    //returns the next node
    public Node getNextNode(){
        return next;
        
    }
    
    //changes the value of node
    public void setValue(int i){
        value = i;
    }
     //set next node
    public void setNextNode(Node n){
        next = n;
    }
        
}

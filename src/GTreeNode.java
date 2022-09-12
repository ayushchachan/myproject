/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import acm.program.*;
import acm.graphics.*;

/**
 *
 * @author Ayush Chachan
 */
public class GTreeNode extends GCompound{
    GRect parentBox, leftBox, rightBox;
    
    String key;
    GTreeNode parentNode, leftChildNode, rightChildNode;
    GLine parentR, leftChildR, rightChildR;             //refernces to parent, left child and right child
    GDimension frameSize;
    GLabel keyLabel;
    
    /**Constructs a new Graphical TreeNode object of default size, with label as txt*/
    public GTreeNode(String txt, GTreeNode p, GTreeNode l, GTreeNode r) {        
    }
    

    //overloaded constructor
    /**constructs a new node with parent, left and right all set to null*/
    public GTreeNode(String data) {
        this(data, null, null, null);
    }

    /**No argument constructor
     * return a node with all fields set to null */
    public GTreeNode() {
        this(null, null, null, null);
    }
    
    
    /**returns true if node has left child*/
    public boolean hasLeftChild() {
        return this.leftChildNode != null;
    }
    /**returns true if node n has right sibling*/
    public boolean hasRightChild() {
        return this.rightChildNode != null;
    }

    //query methods
    public GTreeNode getParent() {return this.parentNode;}

    public GTreeNode getLeftChild() {return this.leftChildNode;}

    public GTreeNode getRightChild() {return this.rightChildNode;}

    public String getKey() {return this.key;}       

    //updateGNode methods
    public void setParent(GTreeNode parent) {
        this.parentNode = parent;
    }

    public void setLeftChild(GTreeNode left) {
        this.leftChildNode = left;
        
    }

    public void setRightChild(GTreeNode right) {
        this.rightChildNode = right;
        
    }

    public void setKey(String k) {
        this.key = k;
    }
    
   
    
    /**return center point of GRect r*/
    private GPoint getCenter(GRect r) {
        double centerX = r.getX() + r.getWidth()/2;
        double centerY = r.getY() + r.getHeight()/2;
        
        return new GPoint(centerX, centerY);
    }
    
    /**return the string representation of node*/
    public String toString() {
        return ("<GTreeNode> : " + this.key.toString());
    }
    
    /*constants*/
    private static double DEFAULT_WIDTH = 40;
    private static double DEFAULT_HEIGHT = 40;
}

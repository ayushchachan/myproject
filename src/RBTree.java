package myproject;

import java.util.Comparator;

public class RBTree<T> extends BinarySearchTree<T> {
    
    //------nested RBNode class--------
    private static class RBNode<T> extends Node<T>{
	
	boolean color;					//false stands for black and true stands for red node
        
        
	public RBNode(T elem, RBNode<T> p, RBNode<T> l, RBNode<T> r, boolean color) {
            super(elem, p, l, r);
            this.color = color;
	}
        
        
	public RBNode(T elem) {
            this(elem, null, null, null, false);
	}
        
        public RBNode(T elem, boolean color) {
            this(elem, null, null, null, color);
	}

	public String toString() {
            if (this.color == false) return this.element + "-B";
            else
                return this.element + "-R";
	}
    }
	
    /**Constructs an empty Binary Search Tree with a given comparator*/
    public RBTree(Comparator<T> c) {
        this.comp = c;
        size = 0;
        root = null;
    }


    /**Constructs an empty Binary Search Tree with a default comparator*/
    public RBTree() {this(new DefaultComparator());}

    public Node<T> RBtreeInsert(T z) {
        RBNode<T> x = new RBNode(z, true); // x = new Node with color = Red
        super.treeInsert(x);
        
//        System.out.println("node = " + x + " has just been inserted. Now balancing is being done.");
        while (x != root && ((RBNode<T>)x.parent).color == true) {
            RBNode<T> y = null;
            
            if (x.parent == x.parent.parent.left) {             // 3case
                y = (RBNode<T>)x.parent.parent.right;
                
                if (y != null && y.color == true) {  //i.e. color[y] = Red
                    y.color = false;
                    ((RBNode<T>)x.parent).color = false;
                    ((RBNode<T>)x.parent.parent).color = true;
                    x = (RBNode<T>)x.parent.parent;
                
                } else {                //i.e. color[y] = Black
                    if (x == x.parent.right) {
                        x = (RBNode<T>)x.parent;
                        leftRotate(x);
                    }
                    ((RBNode<T>)x.parent).color = false;
                    ((RBNode<T>)x.parent.parent).color = true;
                    rightRotate((RBNode<T>)x.parent.parent);
                }
                
            } else {                                            // 3cases
                
                y = (RBNode<T>)x.parent.parent.left;
                
                if (y != null && y.color == true) {                  //i.e. color[y] = Red
                    ((RBNode<T>)x.parent).color = false;
                    ((RBNode<T>)x.parent.parent).color = true;
                    y.color = false;
                    x = (RBNode<T>)x.parent.parent;
                } else {                                //i.e. color[y] = Black
                    if (x == x.parent.left) {
                        x = (RBNode<T>)x.parent;
                        rightRotate(x);
                    }
                    ((RBNode<T>)x.parent).color = false;
                    ((RBNode<T>)x.parent.parent).color = true;
                    leftRotate((RBNode<T>)x.parent.parent);
                }
            }
        }
        ((RBNode<T>)this.root).color = false;
        return x;
    }
    
    public void leftRotate(RBNode<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        
        if (y.left != null) y.left.parent = x;
        
        if (x.parent == null) this.root = (RBNode<T>)y;
        else {
            Node<T> z = x.parent;
            if (x == z.left) z.left = y;
            else z.right = y;
        }    
        y.parent = x.parent;
        x.parent = y;
        y.left = x;   
    }
    
    public void rightRotate(RBNode<T> y) {
        Node<T> x = y.left;
        y.left = x.right;
        
        if (x.right != null) x.right.parent = y;
        
        if (y.parent == null) this.root = (RBNode<T>)x;
        else {
            Node<T> z = y.parent;
            if (y == z.left) z.left = x;
            else z.right = x;
        }
        x.parent = y.parent;
        x.right = y;
        y.parent = x;
    }

    public void RBtreeDelete(T z) {

    }


}


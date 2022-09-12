/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Comparator;
import java.util.HashSet;

/**
 *


 * @author Ayush Chachan
 */
public class BestFit {
    private static AVLTree<MyObject> objectTree;
    private static AVLTree<MyBin> binTreeId;
    private static AVLTree<MyBin> binTreeCapacity;
    private static BufferedReader br;
    
    private static HashSet<MyObject> errorObjectSet;
    
    public static void main(String[] args) throws IOException{
        objectTree = new AVLTree<>();
        binTreeId = new AVLTree<>();
        binTreeCapacity = new AVLTree<>(new BinCapacityComparator());
        errorObjectSet = new HashSet<>();
        
        br = new BufferedReader(new FileReader("test_case4.txt"));
        String line;
        int i = 1;
        while (!(line = br.readLine()).equals("stop")) {
            String[] command = line.split(" ");

            int query = new Integer(command[0]);
            int query1 = new Integer(command[1]);
            int query2;
            if (command.length == 2) 
                query2 = 0;
            else
                query2 = new Integer(command[2]);

            if (query == 1) {
                //System.out.println("add_bin(" + query1 + ", " + query2 + ")");
                add_bin(query1, query2);
            } else if (query == 2) {
                //System.out.println("add_object(" + query1 + ", " + query2 + ")");
                System.out.println(add_object(query1, query2));
                
            } else if (query == 3) {
                //System.out.println("delete_object(" + query1 + ")");
//                for (MyObject o : objectTree) {
//                    System.out.print(o + ", ");
//                }
                System.out.println(delete_object(query1));
                
            } else if (query == 4) {
                System.out.println("contents(" + query1 + ")");
                System.out.println(contents(query1));
            }
            
        }
    }
    

    
    /**In this method, we want to add a new bin to the system, whose id is given as a parameter, and c is it's capacity.*/
    public static void add_bin(int bin_id, int capacity){
        MyBin newBin = new MyBin(bin_id, capacity);
        binTreeId.treeInsert(newBin);
        binTreeCapacity.treeInsert(newBin);
    }
    
    /**Here, we want to add a new object to the system, whose id and size are parameters. 
     * You should use the best fit algorithm to assign the object to a bin. If the object can not fit into any bin, 
     * the program should print the object is too big, otherwise, it should print the id of the bin into which the object has been assigned.
    
    * return the bin id to which this object is added*/
    public static int add_object(int obj_id, int size){
        MyObject newObject = new MyObject(obj_id, size);
        
        
        AVLTree.AVLNode<MyBin> largestBinNode = (AVLTree.AVLNode<MyBin>)binTreeCapacity.treeMaximum(binTreeCapacity.root());
        MyBin largestBin = largestBinNode.getKey();
//        System.out.println("----------tree before removing largest bin-----------");
//        for (MyBin b : binTreeCapacity) {
//            System.out.print(b + ", ");
//        }
        binTreeCapacity.treeDelete(largestBinNode);
//        System.out.println("largestBin  = " + largestBin);
        try {
            largestBin.addObject(newObject);
            newObject.setBin(largestBin);
            binTreeCapacity.treeInsert(largestBin);
            objectTree.treeInsert(newObject);

        } catch (IllegalArgumentException ex) {
            errorObjectSet.add(newObject);
        }
        //System.out.println("largestBin = " + largestBin);
//        System.out.println("----------tree after reinserting largest bin-----------");
//        for (MyBin b : binTreeCapacity) {
//            System.out.print(b + ", ");
//        }

        //objectTree.printTree();
        //new GBinaryTree(objectTree).start();
        return largestBin.getBinId();
    }

    /** Remove the object with id "id" from the bin it is placed in.
        Return the bin_id from which this object was removed*/
    public static int delete_object(int obj_id){
        //System.out.println("object tree : root = " + objectTree.root);
        //System.out.println(" left[root] = " + objectTree.root.getLeftChild() + ", right[root] = " + objectTree.root.getRightChild());
//        for (MyObject o : objectTree) {
//            System.out.print(o + ", ");
//        }
//        System.out.println();
//        //objectTree.printTree();
 
        MyObject obj = new MyObject(obj_id, 0);                                         //an object with id = obj_id, used to search original object
        AVLTree.AVLNode<MyObject> objectTreeNode = (AVLTree.AVLNode<MyObject>)objectTree.treeSearch(obj);
        
        if (objectTreeNode == null) {
            if (errorObjectSet.contains(obj)) {
                errorObjectSet.remove(obj);
                return -1;
            } else {
                System.out.println("errorObjectSet = " + errorObjectSet);
                throw new IllegalArgumentException("there is no object of id " + obj_id);
            }
        }
        
        obj = objectTreeNode.getKey();                       //original MyObject with id = obj_id
        MyBin bin = obj.getBin();               //bin which stores object obj
        
        objectTree.treeDelete(objectTreeNode);               //deleting object from object tree
        bin.deleteObject(obj);                               //deleting object from bin
        
        binTreeCapacity.treeDelete(bin);
        binTreeCapacity.treeInsert(bin);
        //objectTree.printTree();
        return bin.getBinId();
    }
    
    /**PrintBin(id) : print the id(s) and sizes of objects in this bin.*/
    public static void printBin(int bin_id) {
        List<MyObject> objectList = contents(bin_id);
        System.out.println(objectList);
    }
    
    /** Return the list of current objects in the bin with id "bin_id" : list of pairs  for each object in the bin.*/
    public static List<MyObject> contents(int bin_id){ 
        MyBin bin = new MyBin(bin_id, 0);                                //a bin with id = bin_id, used to search original bin
        BinaryTree.Node<MyBin> binTreeIdNode = binTreeId.treeSearch(bin);
        
        bin = binTreeIdNode.getKey();            //original bin with id = bin_id
        return bin.getObjectList();
    }
    
    
    private static class BinCapacityComparator implements Comparator<MyBin> {
        public int compare(MyBin a, MyBin b) {
            //System.out.println("a = " + a + ", b = " + b);
            if (a.getCapacity() > b.getCapacity()) {
                return 1;
            } else if (a.getCapacity() == b.getCapacity()) return 0;
            else return -1;
        }
    }
}

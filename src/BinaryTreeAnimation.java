/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import acm.program.*;
import acm.graphics.*;
import static acm.program.Program.SOUTH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.*;
/**
 *
 * @author Ayush Chachan
 */
public class BinaryTreeAnimation extends GraphicsProgram{
    
    HashMap<BinaryTree.Node<Integer>, GPoint> nodeLocationSet;
    AVLTree<Integer> myTree;
    
    double HORIZONTAL_GAP = 20;
    double VERTICAL_GAP = 50;
    double START_X = 20;
    double TOP_MARGIN = 50;
    
    
    
    
    
    public void init() {
        this.setSize(1300, 500);
        add(new JButton("Pause"), SOUTH);
        nodeLocationSet = new HashMap<>();
        initializeTree();
        update();
    }
    
    /**updates the display*/
    private void update() {
        removeAll();
        //drawGrid();
        drawTree();
    }
    
    /**draws a grid of vertical and horizontal lines*/
    private void drawGrid() {
        GLine line;                         //line to add to canvas
        double lineStartX, lineStartY, lineEndX, lineEndY;
        /*drawing vertical lines*/

        lineStartY = TOP_MARGIN - VERTICAL_GAP;
        lineEndY = myTree.height()*VERTICAL_GAP + TOP_MARGIN;
        for (int i = 0; i < myTree.size(); i++) {
            lineStartX = START_X + i*HORIZONTAL_GAP;
            lineEndX = lineStartX;
            line = new GLine(lineStartX, lineStartY, lineEndX, lineEndY);
            add(line);
        }
        
        /*drawing horizontal lines*/
        lineStartX = 0;
        lineEndX = START_X + myTree.size()*HORIZONTAL_GAP;
        for (int i = 0; i < myTree.height() + 1; i++) {
            lineStartY = TOP_MARGIN + i*VERTICAL_GAP;
            lineEndY = lineStartY;
            line = new GLine(lineStartX, lineStartY, lineEndX, lineEndY);
            add(line);
        }
        
    }
    
    /**draws the tree on canvas*/
    public void drawTree() {

        int i = 0;
        LinkedList<BinaryTree.Node<Integer>> internalNodeList = new LinkedList();
        
        for (BinaryTree.Node<Integer> treeNode : myTree.nodeSet()) {
            
            double nodeX = START_X + i*HORIZONTAL_GAP;
            double nodeY = TOP_MARGIN + myTree.depth(treeNode)*VERTICAL_GAP;
            drawNode(treeNode, nodeX, nodeY);
            i++;
            this.nodeLocationSet.put(treeNode, new GPoint(nodeX, nodeY));
            if (!treeNode.isExternal()) {
                //System.out.println(treeNode);
                
                internalNodeList.addLast(treeNode);
            }
        }
        BinaryTree.Node treeNode;
        /**adding branches*/
        while (!internalNodeList.isEmpty()) {
            treeNode = internalNodeList.removeFirst();
            GPoint parentLoc = this.nodeLocationSet.get(treeNode);
            
            if (treeNode.hasLeftChild()) {
                GPoint leftChildLoc = this.nodeLocationSet.get(treeNode.getLeftChild());
                if (leftChildLoc == null) throw new IllegalArgumentException();
                GLine leftBranch = new GLine(parentLoc.getX(), parentLoc.getY(), leftChildLoc.getX(), leftChildLoc.getY());
                add(leftBranch);
            }
            
            if (treeNode.hasRightChild()) {
                GPoint rightChildLoc = this.nodeLocationSet.get(treeNode.getRightChild());
                GLine rightBranch = new GLine(parentLoc.getX(), parentLoc.getY(), rightChildLoc.getX(), rightChildLoc.getY());
                add(rightBranch);
            }
        }  
    }
    
    /**draw a node n to x and y*/
    private void drawNode(BinaryTree.Node n, double x, double y) {
        GLabel nodeLabel = new GLabel(n.getKey().toString());
        nodeLabel.setFont("*-bold-*");
        addLabel(nodeLabel, x, y);
        GOval nodeBoundary = new GOval(30, 30);
        addGOval(nodeBoundary, x, y);
        
    }
    
    /**adds a label with its center at x and y*/
    private void addLabel(GLabel label, double x, double y) {
        double labelX = x - (label.getWidth()/2);
        double labelY = y + (label.getHeight()*0.4);
        add(label, labelX, labelY);
    }
    
    private void addGOval(GOval circle, double x, double y) {
        double ovalX = x - circle.getWidth()/2;
        double ovalY = y - circle.getHeight()/2;
        add(circle, ovalX, ovalY);
    }
    
    
    private void initializeTree() {
       BufferedReader br = null;
        myTree = new AVLTree<>();
        
        try {
            br = new BufferedReader(new FileReader("random.txt"));
            
            String line;
            int n, j = 1;
            while ((line = br.readLine()) != null) {
                System.out.print("j = " + j++ + ", ");
                System.out.println(line);
                
                String[] input = line.split(" ");
                if (input[0].equals("tree_insert")) {
                    myTree.treeInsert(Integer.parseInt(input[1]));
                } else if (input[0].equals("tree_delete")) {
                    myTree.treeDelete(Integer.parseInt(input[1]));
                   
                }
                pause(100);
                update();
                
            }
            myTree.printTree();
//            BinaryTree.AVLNode<Integer> node_135 = (BinaryTree.AVLNode<Integer>)myTree.treeSearch(135);
//            System.out.println("node_135.getHeight() = " + node_135.getHeight());
//            BinaryTree.AVLNode<Integer> node_127 = (BinaryTree.AVLNode<Integer>)myTree.treeSearch(127);
//            System.out.println("node_127.getHeight() = " + node_127.getHeight());
//            System.out.println("myTree.getChildWithMaxHeight(node_127) = " + myTree.getChildWithMaxHeight(node_127));
//            System.out.println("node_127 = " + node_127);
//            System.out.println("node_127.getLeftChild() = " + node_127.getLeftChild());
//            System.out.println("node_127.getRightChild() = " + node_127.getRightChild());
//            myTree.treeDelete(node_127);
//            System.out.println("myTree.treeDelete(node_127) = "); 
//            
//            
//            System.out.println("node_135.getHeight() = " + node_135.getHeight());
//            System.out.println("myTree.getChildWithMaxHeight(node_135) = " + myTree.getChildWithMaxHeight(node_135));
//            System.out.println("node_135 = " + node_135);
//            System.out.println("node_135.getLeftChild() = " + node_135.getLeftChild());
//            System.out.println("node_135.getRightChild() = " + node_135.getRightChild());
            
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                try {
                    if (br != null) br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
        }
    }
    
    public static void main(String[] args) {
        
        new BinaryTreeAnimation().start(args);
        
    }
}

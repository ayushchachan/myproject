/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import acm.program.*;




/**
 *
 * @author Ayush Chachan
 */
public class GraphicalTowerOfHanoi extends GraphicsProgram {
    public void run() {
        
        
        waitForClick();
        putDisksFrom(n, stack1, stack2, stack3);
    }
    
    /*Constructor*/
    public GraphicalTowerOfHanoi(int num_disks) {
        n = num_disks;      //storing num_disks in an object
        
        stack1 = new GStack(num_disks);
        stack2 = new GStack(num_disks);
        stack3 = new GStack(num_disks);

        int i = num_disks;
        while (i > 0) {
            double width = 10*(i + 1);      //width of GElement
            double height = 10;             //height of GElement
           
            GElement e = new GElement("" + i, width, height);
            stack1.push(e);
            i--;
        }
        
        add(stack1, 100, 400);
        add(stack2, 350, 400);
        add(stack3, 600, 400);
    }
    
    /**This method will move n disks from stack a to stack b using another stack c*/
    public void putDisksFrom(int n, GStack a, GStack b, GStack c) {
        if (a.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        


            
            
//-----recursive definition------- 
        if (n == 1) {
            b.push(a.pop());
            
            
        } else {
            putDisksFrom(n - 1, a, c, b);
            b.push(a.pop());
            
            putDisksFrom(n - 1, c, b, a);
        }
//-----------------end of recursive definition---------*/

    }
    
    /*we will always assume that disks are initially on stack1 and need to be moved to
    stack3 and we have another empty stack2 which we can use for the process*/
    
    //private instance variables
    private GStack stack1;    //stack of disks on stack1 
    private GStack stack2;    //stack of disks on stack2 
    private GStack stack3;    //stack of disks on stack3
    
    private int n;      //number of disks(stored for class)
    
    
        
    //main method 
    public static void main(String[] args) {
        new GraphicalTowerOfHanoi(6).start(args);
    }

}

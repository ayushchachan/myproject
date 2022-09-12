/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Ayush Chachan
 */
public class Threefr {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        
        int numOfTests = Integer.valueOf(inputs.nextInt());      // numbers to test cases i.e. T
        
        //System.out.println("numOfTests = " + numOfTests);
        
        for (int j = 0; j < numOfTests; j++) {
            
            int x = inputs.nextInt();
            int y = inputs.nextInt();
            int z = inputs.nextInt();
            
            boolean answer = isValid(x, y, z);
            if (answer == false) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }
    }
    
    private static boolean isValid(int a, int b, int c) {
        //System.out.println("a = " + a + ", b = " + b + ", c = " + c);
        if (a > b) {
            if (a > c) {
                return (b + c - a) == 0;    //maximum is a
            } else {
                return (a + b - c) == 0;    //maximum is c
            }
        } else {
            if (b > c) {
                
                return (a + c - b) == 0;    //maximum is b
            } else {
                
                return (a + b - c) == 0;    //maximum is c
            }
        }
        
    }
}

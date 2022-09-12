/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;


import acm.program.*;
import inverted_index_part_1.SearchEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Ayush Chachan
 */
public class MyProject extends GraphicsProgram {
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;

    //main method for graphics
//    public static void main(String[] args) {
//        new MyProject().start(args);
//       
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        SearchEngine r = new SearchEngine();
        AVLTree<Integer> myTree = new AVLTree<>();
        try {
            String actionString;
            br = new BufferedReader(new FileReader("actions.txt"));

            while ((actionString = br.readLine()) != null) {
                System.out.print(actionString + ": ");
                r.performAction(actionString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
//----------file writer---------------        
//        Random rgen = new Random();
//        
//        try {
//            bw = new BufferedWriter(new FileWriter("random.txt"));
//            
//            for (int i = 0; i < 50; i++) {
//                bw.write("tree_insert " + rgen.nextInt(199));
//                bw.newLine();
//            }
//            
//            for (int i = 0; i < 30; i++) {
//                if (rgen.nextBoolean()) {
//                    bw.write("tree_insert " + rgen.nextInt(199));
//                } else {
//                    bw.write("tree_delete " + rgen.nextInt(199));
//                }
//                bw.newLine();
//            }
//        } catch (IOException e) {
//                e.printStackTrace();
//        } finally {
//                try {
//                    if (bw != null) bw.close();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//        }
//--------------file writer -----------------------
    }

}

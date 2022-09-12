/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Ayush Chachan
 */
public class WordCount {
    private static Scanner doc;
    private static HashMap<String, Integer> wordFreq0;
    private static ChainHashMap<String, Integer> wordFreq1;
    private static ProbeHashMap<String, Integer> wordFreq2;
    private static BufferedWriter bw;

    /**
     * main method
     */
    public static void main(String[] args) {
        wordFreq0 = new HashMap<>();
        wordFreq1 = new ChainHashMap<>();          //a hash table which maps word to their frequency
        wordFreq2 = new ProbeHashMap<>();

        try {
            //a scanner object
            doc = new Scanner(new FileReader("wordCount.txt")).useDelimiter("[^a-zA-Z]+");

            while (doc.hasNext()) {
                String w = doc.next().toLowerCase();
                Integer count = wordFreq1.get(w);         //frequency of word w in the document

                if (count == null) {
                    count = 0;
                }
                wordFreq0.put(w, count + 1);
                wordFreq1.put(w, count + 1);
                wordFreq2.put(w, count + 1);
            }

            System.out.println("wordFreq0 = " + wordFreq0);
            System.out.println("wordFreq1 = " + wordFreq1);
            System.out.println("wordFreq2 = " + wordFreq2);

            for (String s : wordFreq0.keySet()) {
                if (wordFreq0.get(s).equals(wordFreq2.get(s))) {
                    System.out.println(wordFreq0.get(s).equals(wordFreq2.get(s)));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }
}

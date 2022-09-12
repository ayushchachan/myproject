/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import inverted_index_part_1.WordEntry;

/**
 * This hash table is used by Inverted Index to map word to their word entries
 */
public class MyHashTable {

    private ChainHashMap<String, WordEntry> myTable;          //hash table which maps string to their word entries

    /**
     * Constructor
     */
    public MyHashTable() {
        myTable = new ChainHashMap<>();
    }

    /**
     * Computes hash code for a string using Horner's rule
     */
    private long getHashIndex(String str) {
        int constant = 35;
        int n = str.length();

        int hashCode = 0;
        for (int j = 0; j < n - 1; j++) {
            hashCode = (hashCode + str.charAt(j)) * constant;
        }
        return hashCode + str.charAt(n - 1);
    }

    /**
     * return word entry corresponding to word w
     * else returns null if no word entry for word w is present
     */
    public WordEntry getWordEntryForWord(String w) {
        return myTable.get(w);
    }

    /**
     * It adds an entry to the hash table
     * if no word entry exist then it creates a new word entry
     * if there is existing word entry then it merge w with it.
     */
    public void addPositionsForWord(WordEntry w) {
        String word = w.getWord();
        WordEntry oldEntry = myTable.get(word);     //existing entry associated with word 

        if (oldEntry == null) {
            myTable.put(word, w);
        } else {
            oldEntry.addPositions(w.getAllPositions());
        }
    }


}
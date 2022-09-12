/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myproject;

import java.util.Comparator;

/**
 * @author Ayush Chachan
 */
public class DefaultComparator<E> implements Comparator<E> {

    public int compare(E a, E b) {
        return ((Comparable<E>) a).compareTo(b);
    }
}

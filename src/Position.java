/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 *
 * @author Ayush
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position.
     */
    E getElement() throws IllegalStateException;
}

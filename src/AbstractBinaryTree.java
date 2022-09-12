/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ayush
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);

        if (parent == null) return null;
        if (p == left(parent)) return right(parent);
        else
            return left(parent);
    }

    public int numChildren(Position<E> p) {
        int n = 0;
        if (left(p) != null)
            n++;
        if (right(p) != null)
            n++;
        return n;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> children = new LinkedList<>();
        if (left(p) != null)
            children.add(left(p));
        if (right(p) != null)
            children.add(right(p));
        return children;
    }
}

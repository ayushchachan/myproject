/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Ayush
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    // fields of LinkedBinaryTree class
    protected Node<E> root;
    //-----------end of nested Node class----------------
    private int size;
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * validate the position and returns it as a node.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");

        Node<E> n = (Node<E>) p;    // safe cast
        if (parent(p) == p)
            throw new IllegalArgumentException("position p is not in tree");

        return n;

    }

    @Override
    public Position<E> root() {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getParent();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();

    }

    @Override
    public Iterator<Position<E>> positions() {
        return new PositionIterator();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getRight();
    }

    public String toString() {
        if (isEmpty()) return "<Empty Tree>";

        return "";
    }

    //------------nested Node class----------------
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            this.element = e;
            this.parent = p;
            this.left = l;
            this.right = r;
        }

        //accessor methods
        public E getElement() {
            return this.element;
        }

        public void setElement(E newE) {
            this.element = newE;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public void setParent(Node<E> p) {
            this.parent = p;
        }

        //update methods i.e. setter

        public Node<E> getLeft() {
            return this.left;
        }

        public void setLeft(Node<E> l) {
            this.left = l;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public void setright(Node<E> r) {
            this.right = r;
        }
    }

    //Support for iterator
    public class PositionIterator implements Iterator<Position<E>> {
        LinkedList<Node<E>> nodes;


        public PositionIterator() {
            nodes = new LinkedList<>();
        }

        public boolean hasNext() {
            return (!nodes.isEmpty());
        }

        public Position<E> next() {
            if (nodes.isEmpty()) throw new IllegalStateException("No more items!");

            Node<E> current = nodes.removeFirst();

            if (current.getLeft() != null) nodes.add(current.getLeft());

            if (current.getRight() != null) nodes.add(current.getRight());

            return current;
        }

    }

    public class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIter;


        public ElementIterator() {
            posIter = new PositionIterator();
        }

        public boolean hasNext() {
            return posIter.hasNext();
        }

        public E next() {
            return posIter.next().getElement();
        }

    }

}

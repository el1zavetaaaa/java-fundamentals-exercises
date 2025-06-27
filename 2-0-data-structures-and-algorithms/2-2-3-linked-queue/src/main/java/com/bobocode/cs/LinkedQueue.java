package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {

    private static class Node<T>{
        T element;
        Node<T> nextValue;

        public Node() {
        }

        public Node(T element, Node<T> nextValue) {
            this.element = element;
            this.nextValue = nextValue;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        if(isEmpty()){
            this.head = new Node<>(element, this.tail);
            this.head = new Node<>(element,null);
        }
        else if(size == 1){
             this.tail = new Node<>(element, null);
             this.head.nextValue = this.tail;
        }
        else {
            Node<T> newElement = new Node<>(element, null);
            this.tail.nextValue = newElement;
            this.tail = newElement;

        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {

        if(isEmpty()){
            return null;
        }
        else if(size == 1){
            size --;
            T elementToReturn = this.head.element;
            this.head = null;
            this.tail = null;
            return elementToReturn;
        }
        else if(size == 2){
            size --;
            T elementToReturn = this.head.element;
            this.head = this.tail;
            this.tail = null;
            return elementToReturn;
        }
        else {
            size --;
            T elementToReturn = this.head.element;
            this.head = head.nextValue;
            return elementToReturn;
        }
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }
}

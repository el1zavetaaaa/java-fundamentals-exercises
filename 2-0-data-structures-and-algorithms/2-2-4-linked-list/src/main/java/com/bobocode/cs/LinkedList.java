package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    @AllArgsConstructor
    static class Node<T>{
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T el: elements) {
            linkedList.add(el);
        }

        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> currentNode  = new Node<>(element);
        if(head == null){
            tail = head = currentNode;
        } else {
            tail.next = currentNode;
            tail = currentNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Node<T>[] arr = new Node[size];
        Node<T> current = head;

        int i = 0;
        while (current!= null && i < size){
            arr[i] = current;
            i++;
            current = current.next;
        }

        if(index == 0){
            Node<T> newEl = new Node<>(element);
            newEl.next = head;
            head = newEl;
        } else if(index == size){
            Node<T> newEl = new Node<>(element);
            arr[index - 1].next = newEl;
            tail = newEl;
        }
        else {
            Node<T> newEl = new Node<>(element);
            newEl.next = arr[index];
            arr[index] = newEl;
            arr[index -1].next = newEl;
        }

        size++;

    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if(index >= size || size ==0){
            throw  new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        int i =0;
         while (i<=index){
             if(i== index){
                 current.value = element;
             } else {
                 current = current.next;
             }
             i++;
         }

    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if(index >= size || size ==0){
            throw  new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        int i =0;
        while (i <= index){
            if( i == index){
                break;
            } else current = current.next;

            i++;
        }
        return current.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if(size == 0) throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if(index >= size || size ==0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T>[] arr = new Node[size];
        Node<T> current = head;
        T deletedEl = get(index);

        int i = 0;
        while (current!= null && i < size){
            arr[i] = current;
            i++;
            current = current.next;
        }

        int j = 0;
        current = head;

        while (j <= index){
           if(j == index) {
               if(j == 0 && size == 1){
                   head = null;
               }
               else if(j == 0){
                   Node<T> next = arr[j+1];
                   head.next = next;
                   head = next;
               } else if( j == size -1){
                   Node<T> previous = arr[j-1];
                   tail = previous;
                   previous.next = tail;
               } else {
                   Node<T> previous = arr[j - 1];
                   Node<T> next = arr[j + 1];
                   previous.next = next;
                   current = next;
               }
           }
           current = current.next;
           j++;
        }

        size--;
        return deletedEl;

    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = head;
         int i = 0;
         while (i < size || current != null){
             if(current.value == element){
                 return true;
             }
             current = current.next;
             i++;
         }
         return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        int count = size -1;
        while (count !=-1){
            remove(count);
            count --;
        }
    }
}

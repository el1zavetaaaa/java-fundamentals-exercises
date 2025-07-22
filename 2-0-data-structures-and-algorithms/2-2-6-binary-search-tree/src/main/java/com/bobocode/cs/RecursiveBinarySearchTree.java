package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T>{
        T element;
        Node<T> left;
        Node<T> right;

        public Node() {
        }

        public Node(T element) {
            this.element = element;
        }
    }

    private int size = 0;

    private Node<T> root;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
       RecursiveBinarySearchTree<T> newTree = new RecursiveBinarySearchTree<>();
       Arrays.stream(elements).forEach(newTree::insert);
       return newTree;
    }

    @Override
    public  boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else return insertElement(element, root);
    }

    public boolean insertElement(T element, Node<T> root) {
        T current = root.element;

        if (element.compareTo(current) > 0) {
            if (root.right == null) {
                root.right = new Node<>(element);
                size++;
                return true;
            } else return insertElement(element, root.right);
        } else if (element.compareTo(current) < 0) {
            if (root.left == null) {
                root.left = new Node<>(element);
                size++;
                return true;
            } else return insertElement(element, root.left);
        } else
            return false;
    }

    @Override
    public boolean contains(T element) {
        if(element == null){
            throw new NullPointerException();
        }
        if(root == null){
            return false;
        } else return contains(root, element);
    }

    public boolean contains(Node<T> node, T element){
        T current = node.element;

        if(element.compareTo(current) > 0){
            if(node.right == null){
                return node.element == element;
            } else return contains(node.right, element);
        } else if(element.compareTo(current) < 0){
            if(node.left == null){
                return node.element == element;
            } else return contains(node.left, element);
        } else return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if(root == null){
            return 0;
        }
        return depth(root);
    }

    public int depth(Node<T> node){
        if (node == null)
            return -1;

        int depthLeft = depth(node.left);
        int depthRight = depth(node.right);

        return Math.max(depthLeft, depthRight) + 1;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
       inOrderTraversalUsingStack(root, consumer);
    }

    private void inOrderTraversalUsingStack(Node<T> node, Consumer<T> consumer){
        var stack = new LinkedStack<Node<T>>();

        Node<T> current = node;

        while(!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            consumer.accept(current.element);
            current = current.right;
        }
    }
    private void inOrderTraversal(Node<T> node, Consumer<T> consumer){
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, consumer);
        consumer.accept(node.element);
        inOrderTraversal(node.right, consumer);

    }
}

package First;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key) {
        this.key = key;
    }

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public BinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public BinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null) result.addAll(left.preOrder());
        if (right != null) result.addAll(right.preOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) result.addAll(left.inOrder());
        result.add(this);
        if (right != null) result.addAll(right.inOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) result.addAll(left.postOrder());
        if (right != null) result.addAll(right.postOrder());
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (left != null) left.forEachInOrder(consumer);
        consumer.accept(key);
        if (right != null) right.forEachInOrder(consumer);
    }
    @Override
    public void depthFirstSearch() {
        Stack<AbstractBinaryTree<E>> stack = new Stack<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            AbstractBinaryTree<E> currentNode = stack.pop();
            System.out.print(currentNode.getKey() + " ");

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    @Override
    public void breadthFirstSearch() {
        Queue<AbstractBinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            AbstractBinaryTree<E> currentNode = queue.poll();
            System.out.print(currentNode.getKey() + " ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, "");
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder sb, String indent) {
        if (right != null) {
            right.toString(sb, indent + "   ");
        }

        sb.append(indent).append(key).append("\n");

        if (left != null) {
            left.toString(sb, indent + "   ");
        }
    }
}
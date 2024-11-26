package Tree.First;

import java.util.*;
import java.util.function.Consumer;

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
        depthFirstSearchRecursive(this);
    }

    private void depthFirstSearchRecursive(AbstractBinaryTree<E> node) {
        if (node == null) {
            return;
        }

        // Рекурсивно обрабатываем левое поддерево
        if (node.getLeft() != null) {
            depthFirstSearchRecursive(node.getLeft());
        }

        // Рекурсивно обрабатываем правое поддерево
        if (node.getRight() != null) {
            depthFirstSearchRecursive(node.getRight());
        }

        // Обрабатываем текущий узел (после обработки потомков)
        System.out.print(node.getKey() + " ");
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
        StringBuilder result = new StringBuilder();
        int height = getHeight(this);
        int width = getWidth(height);
        String[][] matrix = new String[height][width];

        for (String[] row : matrix) {
            Arrays.fill(row, "   ");
        }

        fillMatrix(matrix, this, 0, 0, width);

        for (String[] row : matrix) {
            for (String cell : row) {
                result.append(cell);
            }
            result.append("\n");
        }

        return result.toString();
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

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();
        int height = getHeight(this);
        String[][] matrix = new String[height][getWidth(height)];
        fillMatrix(matrix, this, 0, 0, matrix[0].length);
        printMatrix(matrix, result);
        return result.toString();
    }

    private int getHeight(AbstractBinaryTree<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(
                getHeight(node.getLeft()),
                getHeight(node.getRight())
        );
    }


    private int getWidth(int height) {
        return (int) Math.pow(2, height) - 1;
    }

    private void fillMatrix(String[][] matrix, AbstractBinaryTree<E> node, int row, int start, int end) {
        if (node == null) return;

        int mid = (start + end) / 2;
        matrix[row][mid] = String.format("%-3s", node.getKey());

        fillMatrix(matrix, node.getLeft(), row + 1, start, mid);
        fillMatrix(matrix, node.getRight(), row + 1, mid + 1, end);
    }

    private void printMatrix(String[][] matrix, StringBuilder result) {
        for (String[] row : matrix) {
            for (String cell : row) {
                result.append(cell == null ? "   " : String.format("%-3s", cell));
            }
            result.append("\n");
        }
    }
}
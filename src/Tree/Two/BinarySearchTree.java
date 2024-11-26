package Tree.Two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree<E extends Number & Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E element) {
        if (root == null) {
            return new Node<>(element);
        }

        if (element.compareTo(root.value) < 0) {
            root.leftChild = insertRec(root.leftChild, element);
        } else if (element.compareTo(root.value) > 0) {
            root.rightChild = insertRec(root.rightChild, element);
        }

        return root;
    }

    @Override
    public boolean contains(E element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<E> root, E element) {
        if (root == null) {
            return false;
        }

        if (element.compareTo(root.value) < 0) {
            return containsRec(root.leftChild, element);
        } else if (element.compareTo(root.value) > 0) {
            return containsRec(root.rightChild, element);
        }

        return true;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> result = searchRec(root, element);
        BinarySearchTree<E> tree = new BinarySearchTree<>();
        if (result != null) {
            tree.root = result;
        }
        return tree;
    }

    private Node<E> searchRec(Node<E> root, E element) {
        if (root == null || root.value.equals(element)) {
            return root;
        }

        if (element.compareTo(root.value) < 0) {
            return searchRec(root.leftChild, element);
        }

        return searchRec(root.rightChild, element);
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeftNode() {
        return (root != null) ? root.leftChild : null;
    }

    @Override
    public Node<E> getRightNode() {
        return (root != null) ? root.rightChild : null;
    }

    @Override
    public E getValue() {
        return (root != null) ? root.value : null;
    }

    @Override
    public double calculateAverage() {
        double[] result = new double[2];
        calculateSumAndCount(root, result);
        return result[1] == 0 ? 0 : result[0] / result[1];
    }

    private void calculateSumAndCount(Node<E> node, double[] result) {
        if (node == null) {
            return;
        }

        result[0] += node.value.doubleValue();
        result[1]++;

        calculateSumAndCount(node.leftChild, result);
        calculateSumAndCount(node.rightChild, result);
    }

    @Override
    public String calculateStatistics() {
        double[] result = new double[2];
        calculateSumAndCount(root, result);

        double sum = result[0];
        double count = result[1];
        double average = (count == 0) ? 0 : sum / count;

        return String.format("Сумма всех узлов: %.2f\nКоличество узлов: %.0f\nСреднее арифметическое: %.2f", sum, count, average);
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        if (root == null) {
            return "Дерево пусто.";
        }

        StringBuilder sb = new StringBuilder();
        int treeHeight = getHeight(root);
        int maxWidth = (int) Math.pow(2, treeHeight) - 1;
        List<List<String>> levels = new ArrayList<>();

        for (int i = 0; i < treeHeight; i++) {
            List<String> level = new ArrayList<>(Collections.nCopies(maxWidth, "   "));
            levels.add(level);
        }

        fillLevels(root, levels, 0, 0, maxWidth - 1);

        for (List<String> level : levels) {
            for (String node : level) {
                sb.append(node);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void fillLevels(Node<E> node, List<List<String>> levels, int level, int left, int right) {
        if (node == null || level >= levels.size()) {
            return;
        }

        int mid = (left + right) / 2;
        levels.get(level).set(mid, String.format("%3s", node.value));

        fillLevels(node.leftChild, levels, level + 1, left, mid - 1);
        fillLevels(node.rightChild, levels, level + 1, mid + 1, right);
    }

    private int getHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
    }
}

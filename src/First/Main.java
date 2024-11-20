package First;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(
                1,
                new BinaryTree<>(2, new BinaryTree<>(4, null, null), new BinaryTree<>(5, null, null)),
                new BinaryTree<>(3, new BinaryTree<>(6, null, null), new BinaryTree<>(7, null, null)));

        System.out.println("Tree structure:");
        System.out.println(tree);

        System.out.println("\nPre-order:");
        List<AbstractBinaryTree<Integer>> preOrder = tree.preOrder();
        preOrder.forEach(node -> System.out.print(node.getKey() + " "));

        System.out.println("\n\nIn-order:");
        List<AbstractBinaryTree<Integer>> inOrder = tree.inOrder();
        inOrder.forEach(node -> System.out.print(node.getKey() + " "));

        System.out.println("\n\nPost-order:");
        List<AbstractBinaryTree<Integer>> postOrder = tree.postOrder();
        postOrder.forEach(node -> System.out.print(node.getKey() + " "));

        System.out.println("\n\nIn-order with Consumer:");
        tree.forEachInOrder(key -> System.out.print(key + " "));

        System.out.println("\n\nDepth-First Search:");
        tree.depthFirstSearch();

        System.out.println("\n\nBreadth-First Search:");
        tree.breadthFirstSearch();
    }
}

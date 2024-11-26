package Tree.Two;

public class MainSearch {
    public static void main(String[] args) {
        BinarySearchTree<Double> tree = new BinarySearchTree<>();
        tree.insert(5.5);
        tree.insert(3.3);
        tree.insert(7.1);
        tree.insert(2.4);
        tree.insert(4.3);
        tree.insert(6.0);
        tree.insert(8.8);
        tree.insert(6.0);

        System.out.println("Дерево:");
        System.out.println(tree.asIndentedPreOrder(0));

        System.out.println("Проверка на существование вершины: ");
        System.out.println("Существует вершина 9.0: " + tree.contains(9.0));
        System.out.println("Существует вершина 5.5: " + tree.contains(5.5));
        System.out.println("Существует вершина 3.3: " + tree.contains(3.3));
        System.out.println("Существует вершина 8.5: " + tree.contains(8.5));
        System.out.println("Существует вершина 7.1: " + tree.contains(7.1));
        System.out.println("Существует вершина 2.4: " + tree.contains(2.4));
        System.out.println("Существует вершина 4.3: " + tree.contains(4.3));
        System.out.println("Существует вершина 6.0: " + tree.contains(6.0));
        System.out.println("Существует вершина 8.8: " + tree.contains(8.8));


        System.out.println(" ");
        System.out.println("Подсчет среднего арифметического: ");
        System.out.println(tree.calculateStatistics());
    }
}

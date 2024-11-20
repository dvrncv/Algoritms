package Two;


public interface AbstractBinarySearchTree<E extends Comparable<E>>  {
    void insert(E element);

    boolean contains(E element);

    AbstractBinarySearchTree<E> search(E element);

    Node<E> getRoot();

    Node<E> getLeftNode();

    Node<E> getRightNode();

    E getValue();

    double calculateAverage();

    String calculateStatistics();
}

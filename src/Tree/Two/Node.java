package Tree.Two;

public class Node<E> {

    public E value;
    public Node<E> leftChild;
    public Node<E> rightChild;

    public Node(E value) {
        this.value = value;
    }

    public Node(E value, Node<E> leftChild, Node<E> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}


package Tree.First;

import java.util.List;
import java.util.function.Consumer;

public interface AbstractBinaryTree<E> {
    E getKey();

    AbstractBinaryTree<E> getLeft();

    AbstractBinaryTree<E> getRight();

    void setKey(E key);

    List<AbstractBinaryTree<E>> preOrder();

    List<AbstractBinaryTree<E>> inOrder();

    List<AbstractBinaryTree<E>> postOrder();

    void forEachInOrder(Consumer<E> consumer);

    void depthFirstSearch();

    void breadthFirstSearch();

    void toString(StringBuilder sb, String indent);

    String asIndentedPreOrder(int indent);

}


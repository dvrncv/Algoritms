package Heap;

import java.util.ArrayList;
import java.util.List;

public class AbstractQueueImpl<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> queue;

    public AbstractQueueImpl(){
        queue = new ArrayList<>();
    }

    @Override
    public int size(){
        return queue.size();
    }

    @Override
    public void add(E element){
        queue.add(element);
        heapifyUp(queue.size()-1);
    }

    @Override
    public E peek(){
        if(queue.isEmpty()){
            return null;
        }
        return queue.get(0);
    }
    @Override
    public E poll(){
        if (queue.isEmpty()){
            return null;
        }
        E max = queue.get(0);
        E lastElement = queue.remove(queue.size() - 1);
        if (!queue.isEmpty()) {
            queue.set(0, lastElement);
            heapifyDown(0);
        }
        return max;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (queue.get(index).compareTo(queue.get(parentIndex)) <= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largest = index;

        if (leftChildIndex < queue.size() && queue.get(leftChildIndex).compareTo(queue.get(largest)) > 0) {
            largest = leftChildIndex;
        }
        if (rightChildIndex < queue.size() && queue.get(rightChildIndex).compareTo(queue.get(largest)) > 0) {
            largest = rightChildIndex;
        }
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int i, int j) {
        E temp = queue.get(i);
        queue.set(i, queue.get(j));
        queue.set(j, temp);
    }
    public void changePriority(E oldElement, E newElement) {
        int index = queue.indexOf(oldElement);
        if (index == -1) {
            throw new IllegalArgumentException("Элемент не найден в очереди");
        }

        queue.set(index, newElement);

        if (newElement.compareTo(oldElement) > 0) {
            heapifyUp(index);
        }
        else if (newElement.compareTo(oldElement) < 0) {
            heapifyDown(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int height = getHeight(0);
        int width = (int) Math.pow(2, height) - 1;
        String[][] matrix = new String[height][width];

        for (String[] row : matrix) {
            for (int i = 0; i < width; i++) {
                row[i] = "   ";
            }
        }

        fillMatrix(matrix, 0, 0, width, 0);

        for (String[] row : matrix) {
            for (String cell : row) {
                result.append(cell);
            }
            result.append("\n");
        }

        return result.toString();
    }

    private int getHeight(int index) {
        int height = 0;
        while (index < queue.size()) {
            index = 2 * index + 1;
            height++;
        }
        return height;
    }

    private void fillMatrix(String[][] matrix, int row, int start, int end, int index) {
        if (index >= queue.size()) {
            return;
        }
        int mid = (start + end) / 2;
        matrix[row][mid] = String.format("%-3s", queue.get(index));

        fillMatrix(matrix, row + 1, start, mid, 2 * index + 1);
        fillMatrix(matrix, row + 1, mid + 1, end, 2 * index + 2);
    }
}

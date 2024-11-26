package Heap;

import java.util.ArrayList;
import java.util.List;


public class HeapImpl<E extends Comparable<E>> implements Heap<E> {

    private List<E> heap;

    public HeapImpl() {
        heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void add(E element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    @Override
    public E peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public E poll() {
        if (heap.isEmpty()) {
            return null;
        }
        E max = heap.get(0);
        E lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return max;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) <= 0) {
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

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(largest)) > 0) {
            largest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(largest)) > 0) {
            largest = rightChildIndex;
        }
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
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
        while (index < heap.size()) {
            index = 2 * index + 1;
            height++;
        }
        return height;
    }

    private void fillMatrix(String[][] matrix, int row, int start, int end, int index) {
        if (index >= heap.size()) {
            return;
        }
        int mid = (start + end) / 2;
        matrix[row][mid] = String.format("%-3s", heap.get(index));

        fillMatrix(matrix, row + 1, start, mid, 2 * index + 1);
        fillMatrix(matrix, row + 1, mid + 1, end, 2 * index + 2);
    }
}
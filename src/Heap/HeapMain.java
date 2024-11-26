package Heap;

public class HeapMain {
    public static void main(String[] args) {
        HeapImpl<Integer> heap = new HeapImpl<Integer>();

        heap.add(30);
        heap.add(20);
        heap.add(15);
        heap.add(10);
        heap.add(5);
        heap.add(25);
        heap.add(17);

        System.out.println("Куча:");
        System.out.println(heap.toString());

        System.out.println("Размер кучи: " + heap.size());
        System.out.println("Максимальный элемент кучи: " + heap.peek());
        System.out.println("Удаляем максимальный элемент: " + heap.poll());
        System.out.println("Куча после удаления максимального элемента:");
        System.out.println(heap.toString());
        System.out.println("Максимальный элемент после удаления: " + heap.peek());
        System.out.println("Размер кучи после удаления: " + heap.size());
        System.out.println("Удаляем максимальный элемент: " + heap.poll());
        System.out.println("Куча после удаления максимального элемента:");
        System.out.println(heap.toString());
        System.out.println("Максимальный элемент после удаления: " + heap.peek());
        System.out.println("Размер кучи после удаления: " + heap.size());

    }
}

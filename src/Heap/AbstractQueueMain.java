package Heap;

public class AbstractQueueMain {
    public static void main(String[] args) {
        AbstractQueueImpl<Integer> queue = new AbstractQueueImpl<Integer>();

        queue.add(30);
        queue.add(20);
        queue.add(15);
        queue.add(10);
        queue.add(5);
        queue.add(25);
        queue.add(17);

        System.out.println("Очередь с приоритетом: ");
        System.out.println(queue.toString());
        System.out.println("Размер очереди: " + queue.size());
        System.out.println("Максимальный элемент в очереди: " + queue.peek());
        System.out.println("Удаляем максимальный элемент: " + queue.poll());
        System.out.println("Очередь после удаления:");
        System.out.println(queue.toString());

        System.out.println("Максимальный элемент в очереди после удаления: " + queue.peek());
        System.out.println("Размер очереди после удаления: " + queue.size());

        queue.changePriority(25, 11);
        System.out.println("Изменение приоритета произвольного элемента:");
        System.out.println(queue.toString());
    }
}

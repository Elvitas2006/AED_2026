package Ejercicio4;

public class TestHybrid {
    public static void main(String[] args) {
        PriorityQueueHybrid<String, Integer> pq = new PriorityQueueHybrid<>(3);

        pq.enqueue("A", 2, 5);
        pq.enqueue("B", 2, 1);
        pq.enqueue("C", 1, 3);
        pq.enqueue("D", 2, 3);

        System.out.println(pq);

        while (!pq.isEmpty()) {
            System.out.println("Dequeue: " + pq.dequeue());
        }
    }
}


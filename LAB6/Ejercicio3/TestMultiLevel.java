package Ejercicio3;

public class TestMultiLevel {
    public static void main(String[] args) {
        MultiLevelPriorityQueue<String> pq = new MultiLevelPriorityQueue<>(3);

        pq.enqueue("A", 0);
        pq.enqueue("B", 2);
        pq.enqueue("C", 1);
        pq.enqueue("D", 2);

        System.out.println(pq);

        while (!pq.isEmpty()) {
            System.out.println("Dequeue: " + pq.dequeue());
        }
    }
}
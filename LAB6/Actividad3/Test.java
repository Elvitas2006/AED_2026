package Actividad3;

public class Test {
    public static void main(String[] args) {
        try {
            PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

            pq.enqueue("A", 2);
            pq.enqueue("B", 5);
            pq.enqueue("C", 1);
            pq.enqueue("D", 4);

            System.out.println(pq);
            System.out.println(pq.front());
            System.out.println(pq.back());
            System.out.println(pq.dequeue());
            System.out.println(pq);

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
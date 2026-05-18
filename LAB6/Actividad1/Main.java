public class Main {
    public static void main(String[] args) {
        try {
            QueueArray<Integer> cola = new QueueArray<>(5);

            cola.enqueue(10);
            cola.enqueue(20);
            cola.enqueue(30);

            System.out.println("Cola: " + cola);
            System.out.println("Dequeue: " + cola.dequeue());
            System.out.println("Front: " + cola.front());

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}

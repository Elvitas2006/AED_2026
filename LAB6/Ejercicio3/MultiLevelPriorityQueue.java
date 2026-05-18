package Ejercicio3;

public class MultiLevelPriorityQueue<E> {
    private SimpleQueue<E>[] queues;
    private int levels;
    @SuppressWarnings("unchecked")
    public MultiLevelPriorityQueue(int levels) {
        this.levels = levels;
        queues = new SimpleQueue[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new SimpleQueue<>();
        }
    }
    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad inválida");
            return;
        }
        queues[priority].enqueue(x);
    }
    public E dequeue() {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        System.out.println("Cola vacía");
        return null;
    }
    public boolean isEmpty() {
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i).append(": ").append(queues[i]).append("\n");
        }
        return sb.toString();
    }
}
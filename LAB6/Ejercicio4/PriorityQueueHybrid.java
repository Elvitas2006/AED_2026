package Ejercicio4;

public class PriorityQueueHybrid<E, N extends Comparable<N>> {
    private SortedQueue<E, N>[] queues;
    private int levels;

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        queues = new SortedQueue[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new SortedQueue<>();
        }
    }

    public void enqueue(E x, int priority, N secondary) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad inválida");
            return;
        }
        queues[priority].enqueue(x, secondary);
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
package Ejercicio4;

public class SortedQueue<E, N extends Comparable<N>> {
    private Node<Entry<E, N>> first;
    private Node<Entry<E, N>> last;

    public SortedQueue() {
        first = null;
        last = null;
    }

    public void enqueue(E data, N secondary) {
        Entry<E, N> newEntry = new Entry<>(data, secondary);
        Node<Entry<E, N>> newNode = new Node<>(newEntry);

        if (first == null) {
            first = last = newNode;
            return;
        }

        if (newEntry.secondary.compareTo(first.getData().secondary) < 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }

        Node<Entry<E, N>> current = first;
        while (current.getNext() != null &&
               current.getNext().getData().secondary.compareTo(newEntry.secondary) <= 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) last = newNode;
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E data = first.getData().data;
        first = first.getNext();
        if (first == null) last = null;
        return data;
    }

    public boolean isEmpty() { return first == null; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Entry<E, N>> current = first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(" -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
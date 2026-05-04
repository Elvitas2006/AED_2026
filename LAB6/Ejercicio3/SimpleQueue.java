package Ejercicio3;

public class SimpleQueue<E> {
    private Node<E> first;
    private Node<E> last;

    public SimpleQueue() {
        first = null;
        last = null;
    }

    public void enqueue(E x) {
        Node<E> newNode = new Node<>(x);
        if (last == null) {
            first = last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E data = first.getData();
        first = first.getNext();
        if (first == null) last = null;
        return data;
    }

    public boolean isEmpty() { return first == null; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(" -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
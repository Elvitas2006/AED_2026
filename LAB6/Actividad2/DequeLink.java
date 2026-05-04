package Actividad2;

class DequeLink<E> implements Deque<E> {

    private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        first = null;
        last = null;
    }

    public void addFirst(E x) {
        Node<E> n = new Node<>(x);
        if (isEmpty()) {
            first = last = n;
        } else {
            n.next = first;
            first.prev = n;
            first = n;
        }
    }

    public void addLast(E x) {
        Node<E> n = new Node<>(x);
        if (isEmpty()) {
            first = last = n;
        } else {
            last.next = n;
            n.prev = last;
            last = n;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
        E val = first.data;
        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return val;
    }

    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
        E val = last.data;
        if (first == last) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return val;
    }

    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
        return first.data;
    }

    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
        return last.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> temp = first;
        while (temp != null) {
            sb.append(temp.data).append(" ");
            temp = temp.next;
        }
        return sb.toString();
    }
}
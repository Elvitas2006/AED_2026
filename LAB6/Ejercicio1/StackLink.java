package Ejercicio1;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Stack is empty");
        E data = top.getData();
        top = top.getNext();
        return data;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Stack is empty");
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TOP -> ");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(" -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
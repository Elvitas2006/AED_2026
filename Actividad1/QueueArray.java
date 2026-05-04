class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(E x) {
        if (isFull()) return;
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        E temp = array[front];
        front = (front + 1) % array.length;
        size--;
        return temp;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[(front + i) % array.length]).append(" ");
        }
        return sb.toString();
    }
}
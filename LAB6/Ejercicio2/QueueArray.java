package Ejercicio2;
public class QueueArray {
    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        rear = (rear + 1) % capacity;
        array[rear] = x;
        size++;
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        int data = array[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }
    public int front() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        return array[front];
    }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
}
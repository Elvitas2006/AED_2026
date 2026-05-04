package Ejercicio2;

public class TestColaArreglo {
    public static void main(String[] args) {
        QueueArray cola = new QueueArray(5);

        cola.enqueue(101);
        cola.enqueue(102);
        cola.enqueue(103);
        cola.enqueue(104);
        cola.enqueue(105);
        cola.enqueue(106);

        System.out.println("Atendiendo cliente: " + cola.dequeue());
        System.out.println("Atendiendo cliente: " + cola.dequeue());
        System.out.println("Cliente en frente: " + cola.front());

        cola.enqueue(106);
        cola.enqueue(107);

        while (!cola.isEmpty()) {
            System.out.println("Atendiendo cliente: " + cola.dequeue());
        }
        cola.dequeue();
    }
}
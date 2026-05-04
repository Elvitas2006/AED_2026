package Actividad2;

public class Main {
    public static void main(String[] args) {
        try {
            DequeLink<Integer> d = new DequeLink<>();

            d.addFirst(10);
            d.addLast(20);
            d.addFirst(5);

            System.out.println(d);
            System.out.println(d.removeFirst());
            System.out.println(d.getLast());

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
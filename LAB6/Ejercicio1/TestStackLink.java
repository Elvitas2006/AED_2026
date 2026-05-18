package Ejercicio1;

public class TestStackLink {
    public static void main(String[] args) throws ExceptionIsEmpty {
        StackLink<Integer> stack = new StackLink<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack);
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println(stack);
    }
}
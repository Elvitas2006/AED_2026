package Ejercicio6;

public class Main {
    public static void main(String[] args) {
        AVLTree arbol1 = new AVLTree();
        int[] secuencia1 = {10, 20, 30, 40, 50, 25};
        for (int val : secuencia1) {
            arbol1.insertar(val);
        }
        System.out.print("Recorrido Preorden Árbol 1: ");
        arbol1.preOrder();

        AVLTree arbol2 = new AVLTree();
        int[] secuencia2 = {50, 25, 75, 15, 35, 65, 85};
        for (int val : secuencia2) {
            arbol2.insertar(val);
        }
        System.out.print("Recorrido Preorden Árbol 2: ");
        arbol2.preOrder();

        AVLTree arbol3 = new AVLTree();
        int[] secuencia3 = {5, 10, 15, 20, 25};
        for (int val : secuencia3) {
            arbol3.insertar(val);
        }
        System.out.print("Recorrido Preorden Árbol 3: ");
        arbol3.preOrder();
    }
}
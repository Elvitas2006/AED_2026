package Ejercicio5;

public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();

        int[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int d : datos) {
            arbol.insertar(d);
        }

        System.out.print("Recorrido por Amplitud (BFS Recursivo): ");
        arbol.recorrerAmplitud();
    }
}
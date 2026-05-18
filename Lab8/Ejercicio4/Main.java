package Ejercicio4;

public class Main {
    public static void main(String[] args) {
        AVLTree arbol1 = new AVLTree();
        int[] datos1 = {40, 20, 10, 30, 60, 50, 70};
        for (int d : datos1) {
            arbol1.insertar(d);
        }

        System.out.println("--- ÁRBOL AVL 1 ---");
        System.out.print("Amplitud (Por Niveles): ");
        arbol1.recorrerAmplitud();
        System.out.print("Preorden:               ");
        arbol1.recorrerPreorden();
        System.out.print("Inorden:                ");
        arbol1.recorrerInorden();
        System.out.println();

        AVLTree arbol2 = new AVLTree();
        int[] datos2 = {15, 60, 45, 10, 5, 80, 90, 75};
        for (int d : datos2) {
            arbol2.insertar(d);
        }

        System.out.println("--- ÁRBOL AVL 2 ---");
        System.out.print("Amplitud (Por Niveles): ");
        arbol2.recorrerAmplitud();
        System.out.print("Preorden:               ");
        arbol2.recorrerPreorden();
        System.out.print("Inorden:                ");
        arbol2.recorrerInorden();
    }
}
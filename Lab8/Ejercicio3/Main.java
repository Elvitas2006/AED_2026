package Ejercicio3;

public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();

        int[] valores = {50, 25, 75, 15, 35, 65, 85, 30, 40};
        for (int v : valores) {
            arbol.insertar(v);
        }
        System.out.println("Arreglo Inicial:");
        arbol.mostrarArreglos();

        System.out.println("Eliminando 15 (Caso Nodo Hoja):");
        arbol.eliminar(15);
        arbol.mostrarArreglos();

        System.out.println("Eliminando 85 (Provoca desbalance y Rotación Doble):");
        arbol.eliminar(85);
        arbol.mostrarArreglos();

        System.out.println("Eliminando 50 (Caso Dos Hijos - Reemplazo con Sucesor Inorden 65):");
        arbol.eliminar(50);
        arbol.mostrarArreglos();
    }
}
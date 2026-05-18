package Ejercicio7;

public class Main {
    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();

        System.out.println("INSERCIÓN CON CASOS DE DESBALANCE");
        
        System.out.println("Insertando 10, 20, 30");
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(30);
        arbol.mostrarArreglos();
        System.out.println();

        System.out.println("Insertando 40, 50");
        arbol.insertar(40);
        arbol.insertar(50);
        arbol.mostrarArreglos();
        System.out.println();

        System.out.println("Insertando 25 ");
        arbol.insertar(25);
        arbol.mostrarArreglos();
        System.out.println();

        System.out.println("ELIMINACIÓN Y BALANCEO");
        
        arbol.eliminar(50);
        arbol.mostrarArreglos();
        System.out.println();

        arbol.eliminar(40);
        arbol.mostrarArreglos();
        System.out.println();
    }
}
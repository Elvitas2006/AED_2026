package Ejercicio2;

public class ComparadorArboles {
    public static void main(String[] args) {
        
        System.out.println("CASO DE PRUEBA 1: Secuencia Ordenada (Peor Caso BST)");
        int[] secuenciaOrdenada = {10, 20, 30, 40, 50, 60, 70};
        ejecutarComparacion(secuenciaOrdenada);

        System.out.println("CASO DE PRUEBA 2: Secuencia Desordenada / Aleatoria");
        int[] secuenciaAleatoria = {40, 20, 10, 30, 60, 50, 70};
        ejecutarComparacion(secuenciaAleatoria);
    }

    private static void ejecutarComparacion(int[] datos) {
        ArbolBST bst = new ArbolBST();
        ArbolAVL avl = new ArbolAVL();

        System.out.print("Datos a insertar: ");
        for (int d : datos) System.out.print(d + " ");
        System.out.println("\n");

        for (int d : datos) {
            bst.insertar(d);
            avl.insertar(d);
        }

        System.out.print("Recorrido Inorden BST: ");
        bst.mostrarInorden();
        System.out.print("Recorrido Inorden AVL: ");
        avl.mostrarInorden();
        System.out.println();

        System.out.println("Altura final del BST: " + bst.obtenerAltura());
        System.out.println("Altura final del AVL: " + avl.obtenerAlturaRaiz());
        System.out.println();

        int idBuscarExito = datos[datos.length / 2];
        int idBuscarFallo = 999;

        System.out.println("Búsqueda del elemento " + idBuscarExito + " (Existe):");
        System.out.println("  -> Encontrado en BST: " + bst.buscar(idBuscarExito));
        System.out.println("  -> Encontrado en AVL: " + avl.buscar(idBuscarExito));

        System.out.println("Búsqueda del elemento " + idBuscarFallo + " (No existe):");
        System.out.println("  -> Encontrado en BST: " + bst.buscar(idBuscarFallo));
        System.out.println("  -> Encontrado en AVL: " + avl.buscar(idBuscarFallo));
    }
}
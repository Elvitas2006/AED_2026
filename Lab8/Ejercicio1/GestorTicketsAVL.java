package Ejercicio1;

public class GestorTicketsAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        int[] ticketsInsertar = {30, 10, 20, 40, 50, 25};
        for (int ticket : ticketsInsertar) {
            arbol.insertar(ticket);
            arbol.mostrarArreglos();
        }

        arbol.mostrarInorden();
        System.out.println("---------------------------------------");

        arbol.buscar(20);
        arbol.buscar(60);
        System.out.println("---------------------------------------");

        int[] ticketsEliminar = {10, 40, 30};
        for (int ticket : ticketsEliminar) {
            arbol.eliminar(ticket);
            arbol.mostrarArreglos();
        }

        arbol.mostrarInorden();
    }
}
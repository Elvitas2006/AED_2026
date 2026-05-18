package Ejemplo2;

public class Main {
    public static void main(String[] args) {
        AVLTree turnos = new AVLTree();

        System.out.println("ASIGNANDO TURNOS EN LA CLÍNICA");
        turnos.insertar(10);
        turnos.insertar(20);
        turnos.insertar(30);

        System.out.print("Turnos activos en espera (ordenados): ");
        turnos.recorrerInorden();

        System.out.println("ATENDIENDO PACIENTES (ELIMINACIÓN)");
        System.out.println("Atendiendo y eliminando turno 10...");
        turnos.eliminar(10);

        System.out.print("Turnos restantes: ");
        turnos.recorrerInorden();
    }
}
package Ejemplo1;

public class Main {
    public static void main(String[] args) {
        AVLTree inventario = new AVLTree();

        System.out.println("REGISTRANDO CÓDIGOS DE PRODUCTOS");
        inventario.insertar(105);
        inventario.insertar(110);
        inventario.insertar(115);
        inventario.insertar(102);
        inventario.insertar(101);

        System.out.print("Inventario disponible (Ordenado): ");
        inventario.recorrerInorden();
        System.out.println();

        System.out.println("BUSCANDO PRODUCTOS");
        System.out.println("¿Existe código 110?: " + (inventario.buscar(110) ? "Sí" : "No"));
        System.out.println("¿Existe código 999?: " + (inventario.buscar(999) ? "Sí" : "No"));
        System.out.println();

        System.out.println("ELIMINANDO PRODUCTOS DISCONTINUADOS");
        System.out.println("Eliminando producto 110...");
        inventario.eliminar(110);

        System.out.print("Inventario actualizado: ");
        inventario.recorrerInorden();
    }
}
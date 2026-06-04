package btree;

public class Main {
    public static void main(String[] args) {
        Biblioteca miBiblioteca = new Biblioteca();
        
        System.out.println("--- Cargando biblioteca desde archivo ---");
        miBiblioteca.cargarDesdeArchivo("biblioteca.txt");
        
        System.out.println("\n--- Libros en la biblioteca (Ordenados por ISBN) ---");
        miBiblioteca.mostrarLibros();
        
        System.out.println("\n--- Busqueda de libro (Camino recorrido) ---");
        miBiblioteca.buscarLibro("9780134494166");
        
        System.out.println("\n--- Estadisticas ---");
        miBiblioteca.mostrarEstadisticas();
    }
}
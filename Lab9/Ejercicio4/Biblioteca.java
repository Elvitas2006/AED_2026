package btree;
import java.io.*;

public class Biblioteca {
    private BTree<Libro> btree;

    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String primeraLinea = br.readLine();
            
            if (primeraLinea == null) {
                System.out.println("El archivo está vacío.");
                return;
            }
            
            int orden = Integer.parseInt(primeraLinea.trim());
            this.btree = new BTree<>(orden);
            
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] d = linea.split(",");
                if (d.length == 4) {
                    btree.insert(new Libro(d[0].trim(), d[1].trim(), d[2].trim(), Integer.parseInt(d[3].trim())));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void buscarLibro(String isbn) {
        btree.searchWithPath(new Libro(isbn, "", "", 0));
        System.out.println();
    }

    public void mostrarLibros() { btree.printInOrder(btree.getRoot()); }

    public void mostrarEstadisticas() {
        System.out.println("Altura: " + btree.getHeight());
        System.out.println("Total libros: " + btree.getTotalCount());
    }
}
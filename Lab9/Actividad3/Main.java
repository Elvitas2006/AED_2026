package btree;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(4);
        
        int[] claves = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        
        System.out.println("Insertando claves en el BTree: ");
        for (int clave : claves) {
            System.out.println("Insertando: " + clave);
            bTree.insert(clave);
        }
        
        System.out.println("Estructura final del árbol");
        System.out.println(bTree.toString());
        System.out.println("Pruebas de búsqueda");
        
        System.out.println("Raíz:");
        bTree.search(50); 
        
        System.out.println("Extremo inicial:");
        bTree.search(5);
        
        System.out.println("Extremo final:");
        bTree.search(85);
        
        System.out.println("No encontrado:");
        boolean res = bTree.search(999);
        System.out.println("No encontrado: " + res);

        System.out.println("Rango [20, 40]:");
        bTree.searchRange(20, 40);

        System.out.println("Rango inexistente [100, 200]:");
        bTree.searchRange(100, 200);

        System.out.println("Rango inválido [50, 20]:");
        bTree.searchRange(50, 20);
        
        System.out.println("Prueba de Eliminacion");
        System.out.println("Clave 26:");
        bTree.remove(26);
        System.out.println(bTree.toString());
    }
}
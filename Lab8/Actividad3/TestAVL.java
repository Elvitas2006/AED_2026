package Actividad3;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        try {
            System.out.println("--- PRUEBAS DE INSERCIÓN ---");
            
            tree.insert(50);
            tree.insert(40);
            tree.insert(30); 
            System.out.print("RSR (Caso Izquierda-Izquierda): ");
            tree.inOrder();

            tree.insert(60);
            tree.insert(70); 
            System.out.print("RSL (Caso Derecha-Derecha): ");
            tree.inOrder();

            tree.insert(35);
            tree.insert(38); 
            System.out.print("RDR (Caso Izquierda-Derecha): ");
            tree.inOrder();

            tree.insert(65);
            tree.insert(62); 
            System.out.print("RDL (Caso Derecha-Izquierda): ");
            tree.inOrder();

            tree.insert(25);
            tree.insert(20); 
            System.out.print("RSR 2: ");
            tree.inOrder();

            tree.insert(75);
            tree.insert(80); 
            System.out.print("RSL 2: ");
            tree.inOrder();

            tree.insert(15);
            tree.insert(18); 
            System.out.print("RDR 2: ");
            tree.inOrder();

            tree.insert(85);
            tree.insert(82); 
            System.out.print("Arbol Completo Antes de Eliminar: ");
            tree.inOrder();

            System.out.println("\n--- PRUEBAS DE ELIMINACIÓN ---");

            tree.remove(15);
            System.out.print("Eliminar Nodo Hoja (15): ");
            tree.inOrder();

            tree.remove(25);
            System.out.print("Eliminar Nodo con Un Hijo (25): ");
            tree.inOrder();

            tree.remove(40);
            System.out.print("Eliminar Nodo con Dos Hijos (40): ");
            tree.inOrder();

            tree.remove(20);
            System.out.print("Eliminar con Rotacion Simple: ");
            tree.inOrder();

            tree.remove(30);
            System.out.print("Eliminar con Multiples Rotaciones: ");
            tree.inOrder();

        } catch (ItemDuplicate | ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
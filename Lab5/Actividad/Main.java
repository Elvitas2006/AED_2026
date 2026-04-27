package Actividad;

public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        gestor.agregarTarea(new Tarea("Diseñar BD", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API", 3, "completada"));
        gestor.agregarTarea(new Tarea("Code review", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42", 1, "completada"));

        System.out.println("--- Tareas actuales ---");
        gestor.imprimirTareas();
        
        System.out.println("\nMas prioritaria: " + gestor.obtenerTareaMasPrioritaria());
        
        System.out.println("\nEliminando 'Code review'...");
        gestor.eliminarTarea(new Tarea("Code review", 2, "pendiente"));

        System.out.println("\n--- Lista después de invertir ---");
        gestor.invertirTareas();
        gestor.imprimirTareas();

        System.out.println("\n--- Transferencia de completadas ---");
        ListaEnlazada<Tarea> completadas = new ListaEnlazada<>();
        Nodo<Tarea> aux = gestor.getListaInterna().getPrimero();
        
        while (aux != null) {
            if (aux.valor.getEstado().equals("completada")) {
                completadas.insertarAlFinal(aux.valor);
            }
            aux = aux.siguiente;
        }
        
        System.out.println("Nueva lista de completadas:");
        completadas.imprimir();
    }
}
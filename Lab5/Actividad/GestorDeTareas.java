package Actividad;

public class GestorDeTareas<T> {
    private ListaEnlazada<T> lista;

    public GestorDeTareas() {
        this.lista = new ListaEnlazada<>();
    }

    public void agregarTarea(T tarea) { lista.insertarAlFinal(tarea); }

    public boolean eliminarTarea(T tarea) { return lista.eliminarNodo(tarea); }

    public boolean contieneTarea(T tarea) { return lista.buscar(tarea); }

    public void imprimirTareas() { lista.imprimir(); }

    public int contarTareas() { return lista.longitud(); }

    public void invertirTareas() { lista.invertir(); }

    @SuppressWarnings("unchecked")
    public T obtenerTareaMasPrioritaria() {
        if (lista.estaVacia()) return null;

        Nodo<T> actual = lista.getPrimero();
        T masPrioritaria = actual.valor;

        while (actual != null) {
            if (actual.valor instanceof Comparable) {
                if (((Comparable<T>) actual.valor).compareTo(masPrioritaria) < 0) {
                    masPrioritaria = actual.valor;
                }
            }
            actual = actual.siguiente;
        }
        return masPrioritaria;
    }

    public ListaEnlazada<T> getListaInterna() { return lista; }
}
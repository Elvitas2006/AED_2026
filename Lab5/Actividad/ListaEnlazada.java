package Actividad;

public class ListaEnlazada<T> {
    private Nodo<T> primero;
    private int tamano;

    public ListaEnlazada() {
        this.primero = null;
        this.tamano = 0;
    }

    public void insertarAlInicio(T x) {
        Nodo<T> nuevoNodo = new Nodo<>(x);
        nuevoNodo.siguiente = primero;
        primero = nuevoNodo;
        tamano++;
    }

    public void insertarAlFinal(T x) {
        Nodo<T> nuevoNodo = new Nodo<>(x);
        if (estaVacia()) {
            primero = nuevoNodo;
        } else {
            Nodo<T> actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    public boolean eliminarNodo(T x) {
        if (estaVacia()) return false;

        if (primero.valor.equals(x)) {
            primero = primero.siguiente;
            tamano--;
            return true;
        }

        Nodo<T> actual = primero;
        while (actual.siguiente != null && !actual.siguiente.valor.equals(x)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamano--;
            return true;
        }
        return false;
    }

    public boolean buscar(T x) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.valor.equals(x)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public int longitud() { return tamano; }

    public boolean estaVacia() { return primero == null; }

    public void imprimir() {
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.println(actual.valor);
            actual = actual.siguiente;
        }
    }

    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = primero;
        Nodo<T> siguiente = null;
        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        primero = anterior;
    }

    public Nodo<T> getPrimero() { return primero; }
}
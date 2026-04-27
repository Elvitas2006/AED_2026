package Ejercicio8;

public class NodoDoble<T> {
    T valor;
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;

    public NodoDoble(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
}
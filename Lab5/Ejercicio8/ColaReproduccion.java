package Ejercicio8;

import java.util.Random;

public class ColaReproduccion<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private NodoDoble<T> actual;
    private int tamano;

    public void agregarCancion(T cancion) {
        NodoDoble<T> nuevo = new NodoDoble<>(cancion);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            actual = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamano++;
    }

    public T reproducirSiguiente() {
        if (actual != null && actual.siguiente != null) {
            actual = actual.siguiente;
        }
        return (actual != null) ? actual.valor : null;
    }

    public T reproducirAnterior() {
        if (actual != null && actual.anterior != null) {
            actual = actual.anterior;
        }
        return (actual != null) ? actual.valor : null;
    }

    public void mezclar() {
        if (tamano < 2) return;
        Object[] elementos = new Object[tamano];
        NodoDoble<T> temp = cabeza;
        int i = 0;
        while (temp != null) {
            elementos[i++] = temp.valor;
            temp = temp.siguiente;
        }
        Random rand = new Random();
        for (int j = tamano - 1; j > 0; j--) {
            int idx = rand.nextInt(j + 1);
            Object tmp = elementos[idx];
            elementos[idx] = elementos[j];
            elementos[j] = tmp;
        }
        temp = cabeza;
        i = 0;
        while (temp != null) {
            temp.valor = (T) elementos[i++];
            temp = temp.siguiente;
        }
        actual = cabeza;
    }

    public void mostrarCola() {
        NodoDoble<T> temp = cabeza;
        while (temp != null) {
            System.out.println((temp == actual ? ">> " : "   ") + temp.valor);
            temp = temp.siguiente;
        }
    }

    public int duracionTotal() {
        int total = 0;
        NodoDoble<T> temp = cabeza;
        while (temp != null) {
            if (temp.valor instanceof Cancion) {
                total += ((Cancion) temp.valor).getDuracionSeg();
            }
            temp = temp.siguiente;
        }
        return total;
    }
}
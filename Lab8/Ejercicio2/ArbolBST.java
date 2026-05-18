package Ejercicio2;

class ArbolBST {
    private Nodo raiz;

    public void insertar(int id) {
        raiz = insertarRec(raiz, id);
    }

    private Nodo insertarRec(Nodo nodo, int id) {
        if (nodo == null) {
            return new Nodo(id);
        }
        if (id < nodo.id) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, id);
        } else if (id > nodo.id) {
            nodo.derecho = insertarRec(nodo.derecho, id);
        }
        return nodo;
    }

    public boolean buscar(int id) {
        return buscarRec(raiz, id);
    }

    private boolean buscarRec(Nodo nodo, int id) {
        if (nodo == null) {
            return false;
        }
        if (nodo.id == id) {
            return true;
        }
        return id < nodo.id ? buscarRec(nodo.izquierdo, id) : buscarRec(nodo.derecho, id);
    }

    public int obtenerAltura() {
        return obtenerAlturaRec(raiz);
    }

    private int obtenerAlturaRec(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(obtenerAlturaRec(nodo.izquierdo), obtenerAlturaRec(nodo.derecho));
    }

    public void mostrarInorden() {
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.izquierdo);
            System.out.print(nodo.id + " ");
            inordenRec(nodo.derecho);
        }
    }
}
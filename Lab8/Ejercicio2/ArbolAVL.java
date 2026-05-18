package Ejercicio2;

class ArbolAVL {
    private Nodo raiz;

    private int obtenerAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    public int obtenerAlturaRaiz() {
        return obtenerAltura(raiz);
    }

    private int obtenerFactorEquilibrio(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho)) + 1;
        x.altura = Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho)) + 1;
        return x;
    }

    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho)) + 1;
        y.altura = Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho)) + 1;
        return y;
    }

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
        } else {
            return nodo;
        }

        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        int fe = obtenerFactorEquilibrio(nodo);

        if (fe > 1 && id < nodo.izquierdo.id) {
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && id > nodo.derecho.id) {
            return rotacionIzquierda(nodo);
        }
        if (fe > 1 && id > nodo.izquierdo.id) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && id < nodo.derecho.id) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
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
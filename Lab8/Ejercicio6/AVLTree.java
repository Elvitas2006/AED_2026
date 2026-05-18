package Ejercicio6;

import java.util.Arrays;

class AVLTree {
    private int[] ids;
    private int[] alturas;
    private boolean[] ocupado;
    private int capacidad;

    public AVLTree() {
        this.capacidad = 31;
        this.ids = new int[capacidad];
        this.alturas = new int[capacidad];
        this.ocupado = new boolean[capacidad];
    }

    private void asegurarCapacidad(int indiceMinimo) {
        if (indiceMinimo >= capacidad) {
            int nuevaCapacidad = Math.max(capacidad * 2, indiceMinimo + 1);
            ids = Arrays.copyOf(ids, nuevaCapacidad);
            alturas = Arrays.copyOf(alturas, nuevaCapacidad);
            ocupado = Arrays.copyOf(ocupado, nuevaCapacidad);
            capacidad = nuevaCapacidad;
        }
    }

    private int obtenerAltura(int i) {
        if (i >= capacidad || !ocupado[i]) {
            return 0;
        }
        return alturas[i];
    }

    private int obtenerFactorEquilibrio(int i) {
        if (i >= capacidad || !ocupado[i]) {
            return 0;
        }
        return obtenerAltura(2 * i + 1) - obtenerAltura(2 * i + 2);
    }

    private void actualizarAltura(int i) {
        if (i < capacidad && ocupado[i]) {
            alturas[i] = 1 + Math.max(obtenerAltura(2 * i + 1), obtenerAltura(2 * i + 2));
        }
    }

    private void copiarSubarbol(int origen, int destino, int[] tempIds, int[] tempAlturas, boolean[] tempOcupado) {
        if (origen >= tempIds.length || !tempOcupado[origen]) {
            return;
        }
        asegurarCapacidad(destino);
        ids[destino] = tempIds[origen];
        alturas[destino] = tempAlturas[origen];
        ocupado[destino] = true;

        copiarSubarbol(2 * origen + 1, 2 * destino + 1, tempIds, tempAlturas, tempOcupado);
        copiarSubarbol(2 * origen + 2, 2 * destino + 2, tempIds, tempAlturas, tempOcupado);
    }

    private void limpiarSubarbol(int i) {
        if (i >= capacidad || !ocupado[i]) {
            return;
        }
        ocupado[i] = false;
        ids[i] = 0;
        alturas[i] = 0;
        limpiarSubarbol(2 * i + 1);
        limpiarSubarbol(2 * i + 2);
    }

    private void rotacionDerecha(int y) {
        int x = 2 * y + 1;
        int t2 = 2 * x + 2;
        int idY = ids[y];
        int idX = ids[x];
        int[] tempIds = Arrays.copyOf(ids, capacidad);
        int[] tempAlturas = Arrays.copyOf(alturas, capacidad);
        boolean[] tempOcupado = Arrays.copyOf(ocupado, capacidad);

        limpiarSubarbol(y);
        asegurarCapacidad(y);
        ids[y] = idX;
        ocupado[y] = true;
        asegurarCapacidad(2 * y + 2);
        ids[2 * y + 2] = idY;
        ocupado[2 * y + 2] = true;

        copiarSubarbol(2 * x + 1, 2 * y + 1, tempIds, tempAlturas, tempOcupado);
        copiarSubarbol(t2, 2 * (2 * y + 2) + 1, tempIds, tempAlturas, tempOcupado);
        copiarSubarbol(2 * y + 2, 2 * (2 * y + 2) + 2, tempIds, tempAlturas, tempOcupado);

        recalcularAlturasDesdeAbajo(y);
    }

    private void rotacionIzquierda(int x) {
        int y = 2 * x + 2;
        int t2 = 2 * y + 1;
        int idX = ids[x];
        int idY = ids[y];
        int[] tempIds = Arrays.copyOf(ids, capacidad);
        int[] tempAlturas = Arrays.copyOf(alturas, capacidad);
        boolean[] tempOcupado = Arrays.copyOf(ocupado, capacidad);

        limpiarSubarbol(x);
        asegurarCapacidad(x);
        ids[x] = idY;
        ocupado[x] = true;
        asegurarCapacidad(2 * x + 1);
        ids[2 * x + 1] = idX;
        ocupado[2 * x + 1] = true;

        copiarSubarbol(2 * y + 2, 2 * x + 2, tempIds, tempAlturas, tempOcupado);
        copiarSubarbol(t2, 2 * (2 * x + 1) + 2, tempIds, tempAlturas, tempOcupado);
        copiarSubarbol(2 * x + 1, 2 * (2 * x + 1) + 1, tempIds, tempAlturas, tempOcupado);

        recalcularAlturasDesdeAbajo(x);
    }

    private void recalcularAlturasDesdeAbajo(int i) {
        if (i >= capacidad || !ocupado[i]) {
            return;
        }
        recalcularAlturasDesdeAbajo(2 * i + 1);
        recalcularAlturasDesdeAbajo(2 * i + 2);
        actualizarAltura(i);
    }

    public void insertar(int id) {
        insertarRec(0, id);
    }

    private void insertarRec(int i, int id) {
        asegurarCapacidad(i);
        if (!ocupado[i]) {
            ids[i] = id;
            alturas[i] = 1;
            ocupado[i] = true;
            return;
        }
        if (id < ids[i]) {
            insertarRec(2 * i + 1, id);
        } else if (id > ids[i]) {
            insertarRec(2 * i + 2, id);
        } else {
            return;
        }
        balancear(i);
    }

    private void balancear(int i) {
        actualizarAltura(i);
        int fe = obtenerFactorEquilibrio(i);
        if (fe > 1) {
            int hijoIzq = 2 * i + 1;
            if (obtenerFactorEquilibrio(hijoIzq) >= 0) {
                rotacionDerecha(i);
            } else {
                rotacionIzquierda(hijoIzq);
                rotacionDerecha(i);
            }
        } else if (fe < -1) {
            int hijoDer = 2 * i + 2;
            if (obtenerFactorEquilibrio(hijoDer) <= 0) {
                rotacionIzquierda(i);
            } else {
                rotacionDerecha(hijoDer);
                rotacionIzquierda(i);
            }
        }
    }

    public void preOrder() {
        preOrderRec(0);
        System.out.println();
    }

    private void preOrderRec(int i) {
        if (i < capacidad && ocupado[i]) {
            System.out.print(ids[i] + " ");
            preOrderRec(2 * i + 1);
            preOrderRec(2 * i + 2);
        }
    }
}
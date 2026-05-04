package Ejercicio4;

public class Entry<E, N extends Comparable<N>> {
    E data;
    N secondary;

    public Entry(E data, N secondary) {
        this.data = data;
        this.secondary = secondary;
    }

    public String toString() {
        return "(" + data + "," + secondary + ")";
    }
}
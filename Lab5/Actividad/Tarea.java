package Actividad;

public class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; // 1 = alta, 2 = media, 3 = baja
    private String estado; // "pendiente", "completada"

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getTitulo() { return titulo; }
    public int getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return String.format("[%s | Prioridad: %d | Estado: %s]", titulo, prioridad, estado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return titulo.equals(tarea.titulo);
    }
}
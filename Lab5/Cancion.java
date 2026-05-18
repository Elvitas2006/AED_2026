public class Cancion {
    private String titulo;
    private String artista;
    private int duracionSeg;
    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }
    //Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public void setDuracion(int duracionSeg) {
        this.duracionSeg = duracionSeg;
    }
    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracionSeg;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", duracionSeg=" + duracionSeg +
                '}';
    }

}

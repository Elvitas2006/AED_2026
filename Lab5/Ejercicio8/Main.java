package Ejercicio8;

public class Main {
    public static void main(String[] args) {
        ColaReproduccion<Cancion> miCola = new ColaReproduccion<>();

        miCola.agregarCancion(new Cancion("Starboy", "The Weeknd", 230));
        miCola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        miCola.agregarCancion(new Cancion("Midnight City", "M83", 243));
        miCola.agregarCancion(new Cancion("Circles", "Post Malone", 215));
        miCola.agregarCancion(new Cancion("Levitating", "Dua Lipa", 203));
        miCola.agregarCancion(new Cancion("Blinding Lights", "The Weeknd", 200));

        miCola.mostrarCola();
        
        System.out.println("\nSaltando canciones...");
        for (int i = 0; i < 3; i++) {
            System.out.println("Siguiente -> " + miCola.reproducirSiguiente());
        }

        System.out.println("Anterior <- " + miCola.reproducirAnterior());
        miCola.mostrarCola();

        System.out.println("\nActivando modo aleatorio (Shuffle)...");
        miCola.mezclar();
        miCola.mostrarCola();

        int totalSeg = miCola.duracionTotal();
        System.out.printf("\nDuración total de la cola: %02d:%02d\n", totalSeg / 60, totalSeg % 60);
    }
}
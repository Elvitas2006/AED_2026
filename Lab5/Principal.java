public class Principal {
    
    public static void main(String[] args) {
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();
        cola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        cola.agregarCancion(new Cancion("Imagine", "John Lennon", 183));
        cola.agregarCancion(new Cancion("Hotel California", "Eagles", 391));
        cola.agregarCancion(new Cancion("Stairway to Heaven", "Led Zeppelin", 482));
        cola.agregarCancion(new Cancion("Hey Jude", "The Beatles", 431));
        cola.agregarCancion(new Cancion("Smells Like Teen Spirit", "Nirvana", 301));

        System.out.println("Cola inicial:");
        cola.mostrarCola();

        System.out.println("\nAvanzando 3 canciones:");
        for (int i = 0; i < 3; i++) {
            Cancion actual = cola.reproducirSiguiente();
            if (actual != null) {
                System.out.println("Canción actual: " + actual);
            } else {
                System.out.println("No hay siguiente canción.");
            }
        }

        System.out.println("\nRetrocediendo 1 canción:");
        Cancion anterior = cola.reproducirAnterior();
        if (anterior != null) {
            System.out.println("Canción actual: " + anterior);
        } else {
            System.out.println("No hay canción anterior.");
        }

        System.out.println("\nMezclando la cola...");
        cola.mezclar();
        System.out.println("Cola reorganizada:");
        cola.mostrarCola();

        int duracionTotalSeg = cola.duracionTotal();
        int minutos = duracionTotalSeg / 60;
        int segundos = duracionTotalSeg % 60;
        System.out.printf("\nDuración total de la cola: %02d:%02d\n", minutos, segundos);

    }
    
}
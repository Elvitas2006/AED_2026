class ColaReproduccion<T> {
    NodeDoble<T> head;
    NodeDoble<T> current;
    public ColaReproduccion() {
        this.head = null;
        this.current = null;
    }
    public void agregarCancion(T cancion) {
        NodeDoble<T> newNode = new NodeDoble<>(cancion);
        if (head == null) {
            head = newNode;
            current = head;
        } else {
            NodeDoble<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
    }
    public T reproducirSiguiente() {
        if (current != null && current.next != null) {
            current = current.next;
            return current.value;
        }
        return null; // No hay siguiente canción
    }
    public T reproducirAnterior() {
        if (current != null && current.prev != null) {
            current = current.prev;
            return current.value;
        }
        return null; // No hay canción anterior
    }
    public void mezclar() {
        // Implementación del algoritmo de Fisher-Yates para mezclar la lista
        if (head == null) return;
        // Contar el número de nodos
        int count = 0;
        NodeDoble<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        // Crear un array para almacenar los nodos
        NodeDoble<T>[] nodes = new NodeDoble[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            nodes[i] = temp;
            temp = temp.next;
        }
        // Mezclar el array usando Fisher-Yates
        for (int i = count - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            // Intercambiar nodes[i] y nodes[j]
            NodeDoble<T> tempNode = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tempNode;
        }
        // Reconstruir la lista a partir del array mezclado
        head = nodes[0];
        head.prev = null;
        for (int i = 1; i < count; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }
        nodes[count - 1].next = null;
    }
    public void mostrarCola() {
        NodeDoble<T> temp = head;
        while (temp != null) {
            if (temp == current) {
                System.out.println(temp.value + " <-- Canción actual");
            } else {
                System.out.println(temp.value);
            }
            temp = temp.next;
        }
    }
    public int duracionTotal() {
        int totalDuracion = 0;
        NodeDoble<T> temp = head;
        while (temp != null) {
            // convetir totalDuracion en el siguiente formato: "mm:ss" y mostrarlo al usuario
            totalDuracion += ((Cancion) temp.value).getDuracion();
            temp = temp.next;
        }
        return totalDuracion;
    }
}
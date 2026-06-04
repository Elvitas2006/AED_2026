package btree;

public class BTree<E extends Comparable<E>> {
    private LibroNode<E> root;
    private int orden;
    private boolean up;
    private LibroNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public void insert(E cl) {
        up = false;
        E mediana = push(this.root, cl);
        if (up) {
            LibroNode<E> pnew = new LibroNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.setChild(0, this.root);
            pnew.setChild(1, nDes);
            this.root = pnew;
        }
    }

    private E push(LibroNode<E> current, E cl) {
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        }
        int res = current.searchNode(cl);
        if (res >= 0) { up = false; return null; }
        int pos = -res - 1;
        E mediana = push(current.childs.get(pos), cl);
        if (up) {
            if (current.nodeFull()) mediana = dividedNode(current, mediana, pos);
            else { putNode(current, mediana, nDes, pos); up = false; }
        }
        return mediana;
    }

    private void putNode(LibroNode<E> current, E cl, LibroNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.setChild(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.setChild(k + 1, rd);
        current.count++;
    }

    private E dividedNode(LibroNode<E> current, E cl, int k) {
        LibroNode<E> rd = nDes;
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nDes = new LibroNode<>(this.orden);
        int j = 0;
        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(j, current.keys.get(i));
            nDes.setChild(j + 1, current.childs.get(i + 1));
            j++;
        }
        nDes.count = (orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= orden / 2) putNode(current, cl, rd, k);
        else putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.setChild(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    public E searchWithPath(E cl) {
        return searchRecursive(root, cl);
    }

    private E searchRecursive(LibroNode<E> current, E cl) {
        if (current == null) return null;
        System.out.print("[Nodo " + current.idNode + "] -> ");
        int res = current.searchNode(cl);
        if (res >= 0) return current.keys.get(res);
        return searchRecursive(current.childs.get(-res - 1), cl);
    }

    public void printInOrder(LibroNode<E> node) {
        if (node == null) return;
        for (int i = 0; i < node.count; i++) {
            printInOrder(node.childs.get(i));
            System.out.println(node.keys.get(i));
        }
        printInOrder(node.childs.get(node.count));
    }

    public int getHeight() {
        int h = 0;
        LibroNode<E> cur = root;
        while (cur != null) { h++; cur = cur.childs.get(0); }
        return h;
    }

    public int getTotalCount() { return countTotal(root); }

    private int countTotal(LibroNode<E> node) {
        if (node == null) return 0;
        int t = node.count;
        for (int i = 0; i <= node.count; i++) t += countTotal(node.childs.get(i));
        return t;
    }

    public LibroNode<E> getRoot() { return root; }
}
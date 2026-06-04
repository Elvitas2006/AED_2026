package btree;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public void insert(E cl) {
        up = false;
        E mediana = push(this.root, cl);
        if (up) {
            BNode<E> pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.setChild(0, this.root);
            pnew.setChild(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
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

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.setChild(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.setChild(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nDes = new BNode<>(this.orden);
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

    public boolean search(E cl) {
        return searchRecursive(this.root, cl);
    }

    private boolean searchRecursive(BNode<E> current, E cl) {
        if (current == null) return false;
        int res = current.searchNode(cl);
        if (res >= 0) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + res);
            return true;
        }
        int childIndex = -res - 1;
        return searchRecursive(current.childs.get(childIndex), cl);
    }

    public void searchRange(E min, E max) {
        if (root == null) return;
        searchRangeRecursive(root, min, max);
        System.out.println();
    }

    private void searchRangeRecursive(BNode<E> current, E min, E max) {
        int i = 0;
        while (i < current.count && min.compareTo(current.keys.get(i)) > 0) i++;
        for (int j = i; j < current.count && max.compareTo(current.keys.get(j)) >= 0; j++) {
            if (current.childs.get(j) != null) searchRangeRecursive(current.childs.get(j), min, max);
            System.out.print(current.keys.get(j) + " ");
            i = j + 1;
        }
        if (i <= current.count && current.childs.get(i) != null) {
            if (max.compareTo(current.keys.get(i == current.count ? i - 1 : i)) >= 0) searchRangeRecursive(current.childs.get(i), min, max);
        }
    }

    public void remove(E cl) {
        if (root == null) return;
        removeRecursive(root, cl);
        if (root.count == 0) root = (root.childs.get(0) != null) ? root.childs.get(0) : null;
    }

    private void removeRecursive(BNode<E> current, E cl) {
        int res = current.searchNode(cl);
        if (res >= 0) {
            if (current.childs.get(0) == null) removeFromLeaf(current, res);
            else removeFromInternal(current, res);
        } else {
            int pos = -res - 1;
            if (current.childs.get(pos) != null && current.childs.get(pos).count < (orden / 2)) fill(current, pos);
            removeRecursive(current.childs.get(pos), cl);
        }
    }

    private void removeFromLeaf(BNode<E> current, int idx) {
        for (int i = idx; i < current.count - 1; i++) current.keys.set(i, current.keys.get(i + 1));
        current.count--;
    }

    private void removeFromInternal(BNode<E> current, int idx) {
        BNode<E> pred = current.childs.get(idx);
        BNode<E> succ = current.childs.get(idx + 1);
        if (pred.count >= (orden / 2)) {
            E replacement = getPredecessor(pred);
            current.keys.set(idx, replacement);
            removeRecursive(pred, replacement);
        } else if (succ.count >= (orden / 2)) {
            E replacement = getSuccessor(succ);
            current.keys.set(idx, replacement);
            removeRecursive(succ, replacement);
        } else {
            merge(current, idx);
            removeRecursive(pred, current.keys.get(idx));
        }
    }

    private E getPredecessor(BNode<E> node) {
        while (node.childs.get(node.count) != null) node = node.childs.get(node.count);
        return node.keys.get(node.count - 1);
    }

    private E getSuccessor(BNode<E> node) {
        while (node.childs.get(0) != null) node = node.childs.get(0);
        return node.keys.get(0);
    }

    private void fill(BNode<E> current, int idx) {
        if (idx != 0 && current.childs.get(idx - 1).count >= (orden / 2)) borrowFromPrev(current, idx);
        else if (idx != current.count && current.childs.get(idx + 1).count >= (orden / 2)) borrowFromNext(current, idx);
        else {
            if (idx != current.count) merge(current, idx);
            else merge(current, idx - 1);
        }
    }

    private void borrowFromPrev(BNode<E> current, int idx) {
        BNode<E> child = current.childs.get(idx);
        BNode<E> sibling = current.childs.get(idx - 1);
        for (int i = child.count - 1; i >= 0; i--) child.keys.set(i + 1, child.keys.get(i));
        if (child.childs.get(0) != null) {
            for (int i = child.count; i >= 0; i--) child.setChild(i + 1, child.childs.get(i));
        }
        child.keys.set(0, current.keys.get(idx - 1));
        if (child.childs.get(0) != null) child.setChild(0, sibling.childs.get(sibling.count));
        current.keys.set(idx - 1, sibling.keys.get(sibling.count - 1));
        child.count++;
        sibling.count--;
    }

    private void borrowFromNext(BNode<E> current, int idx) {
        BNode<E> child = current.childs.get(idx);
        BNode<E> sibling = current.childs.get(idx + 1);
        child.keys.set(child.count, current.keys.get(idx));
        if (child.childs.get(0) != null) child.setChild(child.count + 1, sibling.childs.get(0));
        current.keys.set(idx, sibling.keys.get(0));
        for (int i = 1; i < sibling.count; i++) sibling.keys.set(i - 1, sibling.keys.get(i));
        if (sibling.childs.get(0) != null) {
            for (int i = 1; i <= sibling.count; i++) sibling.setChild(i - 1, sibling.childs.get(i));
        }
        child.count++;
        sibling.count--;
    }

    private void merge(BNode<E> current, int idx) {
        BNode<E> left = current.childs.get(idx);
        BNode<E> right = current.childs.get(idx + 1);
        left.keys.set(left.count, current.keys.get(idx));
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + 1 + i, right.keys.get(i));
            left.setChild(left.count + 1 + i, right.childs.get(i));
        }
        left.setChild(left.count + 1 + right.count, right.childs.get(right.count));
        left.count += right.count + 1;
        for (int i = idx; i < current.count - 1; i++) {
            current.keys.set(i, current.keys.get(i + 1));
            current.setChild(i + 1, current.childs.get(i + 2));
        }
        current.count--;
    }

    public String toString() {
        if (root == null) return "BTree is empty...";
        return "Id.Nodo\tClaves Nodo\tId.Padre\tId.Hijos\n" + writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
        StringBuilder sb = new StringBuilder();
        sb.append(current.idNode).append("\t(");
        for(int i=0; i<current.count; i++) sb.append(current.keys.get(i)).append(i<current.count-1 ? ", " : "");
        sb.append(")\t\t[").append(current.getParentId()).append("]\t\t");
        if (current.childs.get(0) != null) {
            sb.append("[");
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) sb.append(current.childs.get(i).idNode).append(i < current.count ? ", " : "");
            }
            sb.append("]");
        } else sb.append("--");
        sb.append("\n");
        if (current.childs.get(0) != null) {
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) sb.append(writeTree(current.childs.get(i)));
            }
        }
        return sb.toString();
    }
}
package btree;

import java.util.ArrayList;

public class BNode<T extends Comparable<T>> {
    private static int idCounter = 0;
    
    protected int idNode;
    protected BNode<T> parent;
    protected ArrayList<T> keys;
    protected ArrayList<BNode<T>> childs;
    protected int count;
    protected int maxKeys;

    public BNode(int n) {
        this.idNode = idCounter++;
        this.parent = null;
        this.maxKeys = n - 1;
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n);
        this.count = 0;
        
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public void setChild(int index, BNode<T> child) {
        this.childs.set(index, child);
        if (child != null) {
            child.parent = this;
        }
    }

    public String getParentId() {
        return (parent != null) ? String.valueOf(parent.idNode) : "--";
    }

    public boolean nodeFull() {
        return this.count == maxKeys;
    }

    public int searchNode(T key) {
        int i = 0;
        while (i < count && key.compareTo(keys.get(i)) > 0) i++;
        if (i < count && key.compareTo(keys.get(i)) == 0) return i;
        return -i - 1;
    }
}
package Actividad3;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    class NodeAVL extends Node<E> {
        protected int bf;

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data.toString() + " (" + bf + ")";
        }
    }

    private boolean height;

    public void insert(E x) throws ItemDuplicate {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Node<E> insert(E x, NodeAVL node) throws ItemDuplicate {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.data.compareTo(x);
            if (resC == 0) {
                throw new ItemDuplicate(x + " ya se encuentra en el arbol...");
            }

            if (resC < 0) {
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height) {
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.height = true;
                            break;
                        case 1:
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            } else {
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1:
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }

    public void remove(E x) throws ItemNotFound {
        this.height = false;
        this.root = remove(x, (NodeAVL) this.root);
    }

    protected Node<E> remove(E x, NodeAVL node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("El elemento no se encuentra en el arbol...");
        }

        NodeAVL fat = node;
        int resC = node.data.compareTo(x);

        if (resC < 0) {
            fat.right = remove(x, (NodeAVL) node.right);
            if (this.height) {
                fat = balanceRemoveLeft(fat);
            }
        } else if (resC > 0) {
            fat.left = remove(x, (NodeAVL) node.left);
            if (this.height) {
                fat = balanceRemoveRight(fat);
            }
        } else {
            if (node.left == null || node.right == null) {
                NodeAVL temp = (node.left != null) ? (NodeAVL) node.left : (NodeAVL) node.right;
                if (temp == null) {
                    fat = null;
                } else {
                    fat = temp;
                }
                this.height = true;
            } else {
                NodeAVL successor = minAsign(node.right);
                fat.data = successor.data;
                fat.right = remove(successor.data, (NodeAVL) node.right);
                if (this.height) {
                    fat = balanceRemoveLeft(fat);
                }
            }
        }
        return fat;
    }

    private NodeAVL minAsign(Node<E> node) {
        Node<E> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return (NodeAVL) current;
    }

    private NodeAVL balanceRemoveLeft(NodeAVL node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                this.height = true;
                break;
            case 0:
                node.bf = -1;
                this.height = false;
                break;
            case -1:
                NodeAVL hijo = (NodeAVL) node.left;
                if (hijo.bf <= 0) {
                    node = balanceToRight(node);
                    if (hijo.bf == 0) {
                        ((NodeAVL) node.right).bf = -1;
                        node.bf = 1;
                        this.height = false;
                    } else {
                        this.height = true;
                    }
                } else {
                    node = balanceToRight(node);
                    this.height = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL balanceRemoveRight(NodeAVL node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                this.height = true;
                break;
            case 0:
                node.bf = 1;
                this.height = false;
                break;
            case 1:
                NodeAVL hijo = (NodeAVL) node.right;
                if (hijo.bf >= 0) {
                    node = balanceToLeft(node);
                    if (hijo.bf == 0) {
                        ((NodeAVL) node.left).bf = 1;
                        node.bf = -1;
                        this.height = false;
                    } else {
                        this.height = true;
                    }
                } else {
                    node = balanceToLeft(node);
                    this.height = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1:
            case 0:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        switch (hijo.bf) {
            case -1:
            case 0:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL nieto = (NodeAVL) hijo.right;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.left;
        node.left = p.right;
        p.right = node;
        node = p;
        return node;
    }

    public void inOrder() {
        inOrder((NodeAVL) this.root);
        System.out.println();
    }

    private void inOrder(NodeAVL node) {
        if (node != null) {
            inOrder((NodeAVL) node.left);
            System.out.print(node.data + "[" + node.bf + "] ");
            inOrder((NodeAVL) node.right);
        }
    }
}
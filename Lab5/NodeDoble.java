public class NodeDoble<T> {
    T value;
    NodeDoble<T> next;
    NodeDoble<T> prev;
    public NodeDoble(T value) {
        this.value=value;
        this.next=null;
        this.prev=null;
    }
}
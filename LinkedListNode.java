/**
 * Generic node class for linked lists.
 * 
 * @param <T> the type of data stored in the node
 */
class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> next;
    private LinkedListNode<T> prev;

    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }
}
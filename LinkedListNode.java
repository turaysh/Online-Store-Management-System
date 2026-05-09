/**
 * Generic node class for linked lists.
 * Stores data and references to the next and previous nodes.
 *
 * @param <T> the type of data stored in the node
 */
class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> next;
    private LinkedListNode<T> prev;

    /**
     * Creates a new linked list node that stores the given data.
     *
     * @param data the data stored in the node
     */
    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the node data
     */
    public T getData() {
        return data;
    }

    /**
     * Updates the data stored in this node.
     *
     * @param data the new node data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return the next node
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Updates the reference to the next node.
     *
     * @param next the next node
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    /**
     * Returns the previous node in the linked list.
     *
     * @return the previous node
     */
    public LinkedListNode<T> getPrev() {
        return prev;
    }

    /**
     * Updates the reference to the previous node.
     *
     * @param prev the previous node
     */
    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }
}

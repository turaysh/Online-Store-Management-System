

/**
 * Represents a shopping cart for a customer.
 * It stores selected items and supports add, remove, search, and total calculation.
 */

class ShoppingCart implements searchable  {
    private LinkedListNode<Item> itemsHead;
    private LinkedListNode<Item> itemsTail;
    private int nofItem;

    public ShoppingCart(){
        itemsHead = null;
        itemsTail = null;
        nofItem = 0;
    }

    /**
     * Adds an item to the cart.
     *
     * @param item the item to add
     * @return true if added successfully, otherwise false
     */
    public boolean addItem(Item item){
        if(item == null)
            return false;

        LinkedListNode<Item> newNode = new LinkedListNode<>(item);
        if (itemsHead == null) {
            itemsHead = newNode;
            itemsTail = newNode;
        } else {
            itemsTail.setNext(newNode);
            itemsTail = newNode;
        }
        nofItem++;
        return true;
    }

    /**
     * Removes an item from the cart using its id.
     *
     * @param itemId the item id
     * @return true if removed successfully, otherwise false
     */
    public boolean removeItem(int itemId) {
        LinkedListNode<Item> current = itemsHead;
        LinkedListNode<Item> prev = null;

        while (current != null) {
            if (current.getData() != null && current.getData().getId() == itemId) {
                if (prev == null) {
                    itemsHead = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                if (current == itemsTail) {
                    itemsTail = prev;
                }
                nofItem--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Searches for an item in the cart using its id.
     *
     * @param itemId the item id
     * @return the matching item if found, otherwise null
     */
    public Item searchItem(int itemId) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null && current.getData().getId() == itemId) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Recursively counts non-null items in the cart starting from a given node.
     *
     * @param node the current node
     * @return the number of items from that node onward
     */
    public int countItemRecursive(LinkedListNode<Item> node) {
        if (node == null) {
            return 0;
        }
        if (node.getData() == null) {
            return countItemRecursive(node.getNext());
        }
        return 1 + countItemRecursive(node.getNext());
    }

    /**
     * Counts items in the cart (overloaded for backward compatibility).
     *
     * @param index the starting index (ignored, kept for compatibility)
     * @return the number of items from that index onward
     */
    public int countItemRecursive(int index) {
        return countItemRecursive(itemsHead);
    }

    /**
     * Calculates the total price of items in the cart.
     *
     * @return the total price
     */
    public double calculateTotal(){
        double total = 0;
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                total += current.getData().getPrice();
            }
            current = current.getNext();
        }
        return total;
    }

    /**
     * Removes all items from the cart and resets the count.
     */
    public void clearCart(){
        itemsHead = null;
        itemsTail = null;
        nofItem = 0;
        System.out.println("The operation has been successfully done");
    }

    /**
     * Displays all items currently in the cart.
     */
    public void displayCartItems(){
        if (itemsHead == null) {
            System.out.println("Cart is empty.");
            return;
        }
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                System.out.println(current.getData().getName() + " - Price: " + current.getData().getPrice());
                System.out.println("Total: " + calculateTotal());
            }
            current = current.getNext();
        }
    }

    public String getCartText() {
        if (itemsHead == null) {
            return "Cart is empty.";
        }

        StringBuilder sb = new StringBuilder();
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                sb.append(current.getData().getName())
                  .append(" - Price: ")
                  .append(current.getData().getPrice())
                  .append("\n");
            }
            current = current.getNext();
        }
        sb.append("Total: ").append(calculateTotal());
        return sb.toString();
    }

    /**
     * Returns the head of the items linked list (for Store to iterate).
     *
     * @return the head node of the items list
     */
    public LinkedListNode<Item> getItemsHead() {
        return itemsHead;
    }

    /**
     * Searches for an item in the cart by name.
     *
     * @param name the name to search for
     * @return the matching item if found, otherwise null
     */
    public Item searchByName(String name) {
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null && current.getData().getName().equalsIgnoreCase(name)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart: ");
        LinkedListNode<Item> current = itemsHead;
        while (current != null) {
            if (current.getData() != null) {
                sb.append(current.getData().toString()).append("\n");
                System.out.println(calculateTotal());
            }
            current = current.getNext();
        }
        return sb.toString();
    }

    /**
     * Returns the number of items in the cart.
     *
     * @return item count
     */
    public int getNofItem() {
        return nofItem;
    }
}

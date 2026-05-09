/**
 * Thrown when an admin tries to add an item with an ID that already exists.
 * This user-defined unchecked exception helps prevent duplicate items in the store.
 */
public class DuplicateItemException extends RuntimeException {

    /**
     * Creates a new duplicate item exception with a message that includes the duplicate item ID.
     *
     * @param itemId the item ID that already exists in the store
     */
    public DuplicateItemException(int itemId) {
        super("Item ID " + itemId + " already exists.");
    }
}

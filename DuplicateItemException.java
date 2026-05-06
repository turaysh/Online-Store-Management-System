/**
 * Thrown when an admin tries to add an item with an id that already exists.
 * This is an unchecked exception, so Store can throw it and the menu can handle it.
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(int itemId) {
        super("Item ID " + itemId + " already exists.");
    }
}

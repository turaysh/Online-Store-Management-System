/**
 * Interface for classes that support searching by item name.
 */

public interface searchable {
    /**
     * Searches for an item using its name.
     *
     * param name the name to search for
     * return the matching item if found, otherwise null
     */

    public Item searchByName(String name);
    
}

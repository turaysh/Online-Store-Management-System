# Online Store Management System

This project is a Java application for a simple online store system. The original console program is still available, and Phase 2 adds linked list updates, file handling, exceptions, and a simple Swing GUI.

## Phase 2 Modifications

- Added a Java Swing GUI with two frames:
  - `InputFrame.java` for entering item information and pressing buttons.
  - `ResultFrame.java` for displaying items, search results, cart details, and checkout messages.
- Added `StoreGUI.java` as a separate GUI runner.
- Kept the console program in `test.java`.
- Replaced the `Order[] orders` array in `Customer.java` with a linked list using `LinkedListNode<Order>`.
- Added `DuplicateItemException.java` to stop admins from adding an item ID that already exists.
- Connected item file loading and saving more clearly.

## Linked List Usage

Linked lists are used in these places:

- `Store.java` stores items using `LinkedListNode<Item>`.
- `Store.java` stores users using `LinkedListNode<User>`.
- `ShoppingCart.java` stores cart items using `LinkedListNode<Item>`.
- `Order.java` stores ordered items using `LinkedListNode<Item>`.
- `Customer.java` now stores customer orders using `LinkedListNode<Order>`.

## File Handling

File handling is done in `FileManager.java`.

- `FileManager.loadItems(store)` loads items from `items.txt` when the program starts.
- `FileManager.saveItems(store)` saves items to `items.txt`.
- Items are saved after admin add, remove, and stock update actions.
- Items are also saved before exiting the console program and when closing the GUI.

The item file format is:

```text
TYPE|ID|NAME|PRICE|STOCK|EXTRA
```

Examples:

```text
ELECTRONIC|1|Laptop|999.99|1|2
GROCERY|2|Milk|2.99|50|2024-12-31
```

## Exceptions

- Checked exception handling:
  - `FileManager.java` handles `IOException` with `try/catch` when loading and saving files.
- User-defined unchecked exception:
  - `DuplicateItemException` is thrown in `Store.addItem` when the item ID already exists.
  - It is handled in the console admin menu and in the Swing GUI.

## GUI Frames

- `InputFrame.java`
  - Uses text fields for item ID, name, price, stock, and warranty/expiry date.
  - Uses buttons and `ActionListener`.
  - Uses the existing `Store`, `Admin`, `Customer`, `Item`, and `ShoppingCart` logic.
- `ResultFrame.java`
  - Displays output from the GUI actions.

## How to Run in VS Code

1. Open the project folder in VS Code.
2. Make sure the Java Extension Pack is installed.
3. Compile the project:

```bash
javac *.java
```

4. Run the console version:

```bash
java test
```

5. Run the GUI version:

```bash
java StoreGUI
```

The program should load items from `items.txt` at startup.

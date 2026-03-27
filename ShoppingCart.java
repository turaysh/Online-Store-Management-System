class ShoppingCart implements searchable  {
    private Item[] items;
    private int nofItem;
    private int count;
    

    public ShoppingCart(){
        items = new Item[100];
        count = 0;
        nofItem = 0;
    }
    public boolean addItem(Item item){
        if(nofItem >= items.length || item == null)
            return false;
        items[nofItem++] = item;
        return true;
    }
    public boolean removeItem(int itemId){
        for(int i = 0; i < nofItem; i++)
            if(items[i].getId() == itemId){
                items[i] = null;
                return true;
            }
        return false;
    }
    public Item searchItem(int itemId){
        for(int i = 0; i < nofItem; i++)
            if(items[i].getId() == itemId)
                return items[i];
        return null;
    }
    public double calculateTotal(){
        double total=0;
        for(int i = 0; i < nofItem; i++)
            total += items[i].getPrice();

        return total;
    }
    public int countItemRecursive(int index) {
        if (index >= count) {
            return 0; 
        }

        if (items[index] != null) {
            return 1 + countItemRecursive(index + 1);
        } else {
            return countItemRecursive(index + 1);
        }
    }
    public void clearCart(){
        for(int i =0; i < nofItem; i++){
            items[i] = null;
        }
        nofItem = 0;
        System.out.println("the operation has been succesfuly done");
    }
    public void displayCartItems(){
        for(int i = 0; i < nofItem; i++){
            if(items[i] != null){
                System.out.println(items[i].getName());
            }
        }
    }
    public Item[] getItems() { 
        return items;

    }
    public Item searchByName(String name) {
        for (int i = 0; i < nofItem; i++) {
            if (items[i] != null && items[i].getName().equalsIgnoreCase(name)) {
                return items[i];
            }
        }
        return null;
    }}
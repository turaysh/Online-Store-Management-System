class ShoppingCart implements searchable  {
    private Item[] items;
    private int nofItem;

    public ShoppingCart(){
        items = new Item[100];
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
    double total = 0;
    for(int i = 0; i < nofItem; i++) {
        if(items[i] != null)
            total += items[i].getPrice();
    }
    return total;
}
public int countItemRecursive(int index) {
    if (index >= nofItem) {
        return 0;
    }
    return 1 + countItemRecursive(index + 1);
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
}
public String getStringItems() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nofItem; i++) {
        if (items[i] != null) {
            sb.append(items[i].getName()).append(", ");
        }
    }
    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
}
public int getNofItem() {
    return nofItem;
}
}
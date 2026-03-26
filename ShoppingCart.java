class ShoppingCart{
    private Item[] items;
    private int nofItem;
    private int count;

    public ShoppingCart(){
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
            if(items[i].getId == itemId){
                items[i] == null;
                return true;
            }
        return false;
    }
    public Item searchItem(int itemId){
        for(int i = 0; i < nofItem; i++)
            if(items[i].getId == itemId)
                return items[i];
        return null;
    }
    public double calculateTotal(){
        double total;
        for(int i = 0; i < nofItem; i++)
            total += items[i].getPrice();

        return total;
    }
    public int countItemsRecursive(){} // ?
    public void clearCart(){
        for(int i =0; i < nofItem; i++){
            items[i] = null;
        }
        nofItem = 0;
        System.out.println("the operation has been succesfuly done");
    }

}
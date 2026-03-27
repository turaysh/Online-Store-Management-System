class Store implements searchable {
    private String name;
    private Item[] items;
    private Customer[] Customers;
    private int nofItem;
    private int nofC;

    public Store(String name){
        this.name = name;
        items = new Item[100];
        Customers = new Customer[100];
        nofC = 0;
        nofItem = 0;
    }
    public boolean addItem(Item item){
        if(nofItem >= items.length || item == null)
            return false;
        items[nofItem++] = item;
        return true;
    }
    public boolean removeItem(int itemId){
        for(int i = 0; i < nofItem; i++){
            if(items[i].getId() == itemId)
            {
                for(int j = i; j < nofItem - 1; j++)
                    items[j] = items[j + 1];
                items[--nofItem] = null;
                return true;
            }
        }
        return false;
    }

    public boolean updateStoke(int itemId, int newStock){
        for (int i = 0; i < nofItem; i++)
            if(items[i].getId() == itemId){
                items[i].setStock(newStock);
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
  
    public void displayAllItems(){
        for(int i = 0; i < nofItem; i++)
            System.out.println("Product : " + items[i].getName() + " price : " + items[i].getPrice());
    }
    public int countAvalibleItems(int index){ // ??
        int count = 0;
        for (int i = 0; i < nofItem; i++){
            if(items[i].getStock() > 0)
                count++;
        }
        return count;
    }
    public Item searchByName(String name) {
        for (int i = 0; i < nofItem; i++) {
            if (items[i] != null && items[i].getName().equalsIgnoreCase(name)) {
                return items[i];
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
}
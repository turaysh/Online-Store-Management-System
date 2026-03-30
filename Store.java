
class Store implements searchable {
    private String name;
    private Item[] items;
    private Customer[] Customers;
    private int nofItem;
    private int nofC;
    private int userCount;
    private User[] users;

    public Store(String name){
        this.name = name;
        items = new Item[100];
        Customers = new Customer[100];
        nofC = 0;
        nofItem = 0;
        userCount = 0;
        users = new User[200];
    }
    public boolean addUser(User user) {
    if (userCount < users.length) {
        users[userCount] = user;
        userCount++;
        return true;
    }
    return false;
}
public int getNextUserId() {
    return userCount + 1;
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
    public Item searchItem(String itemName){
        for(int i = 0; i < nofItem; i++)
            if(items[i].getName().equalsIgnoreCase(itemName))
                return items[i];

        return null;
    }
  
    public void displayAllItems(){
        for(int i = 0; i < nofItem; i++)
            System.out.println("Product : " + items[i].getName() + " price : " + items[i].getPrice());
    }
    public int countAvalibleItems(){ 
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
    public User login(String username, String password) {
    for (int i = 0; i < userCount; i++) {
        if (users[i] != null &&
            users[i].getUsername() != null &&
            users[i].getPassword() != null &&
            users[i].getUsername().equals(username) &&
            users[i].getPassword().equals(password)) {
            return users[i];
        }
    }
    return null;
}

}
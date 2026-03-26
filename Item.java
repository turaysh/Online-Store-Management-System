class Item{
    protected int itemId;
    protected String name;
    protected double price;
    protected int stock;

    public Item(int id, String name, double price, int stock){
        this.itemId = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public String getDitails(){ // ?

    }



    public int getItem(){
        return itemId;
    }
    public double getPrice(){
        return price;
    }
}
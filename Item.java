public class Item {
    private String name;
    private int price;

    public Item(String n, int p){
        name = n;
        price = p;
    }

    public String nm(){
        return name;
    }

    public int prc(){
        return price;
    }
}
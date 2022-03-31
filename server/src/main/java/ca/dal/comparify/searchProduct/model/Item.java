package ca.dal.comparify.searchProduct.model;

public class Item {

    String name;
    String id;
    public Item(){

    };
    public Item(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}

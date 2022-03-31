package ca.dal.comparify.searchProduct.model;

import org.bson.codecs.pojo.annotations.BsonId;

public class Item {

    String name;
    @BsonId String id;
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

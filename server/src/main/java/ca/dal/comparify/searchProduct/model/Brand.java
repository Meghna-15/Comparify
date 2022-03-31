package ca.dal.comparify.searchProduct.model;


import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.beans.factory.annotation.Value;


public class Brand {
   
    String brandId;

   
    String name;
    
    
    public Brand() {
    }
    public Brand(@BsonId String id, String name) {
        this.brandId = id;
        this.name = name;
    }
    public String getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return name;
    }
    public void setBrandId( String brandId) {
        this.brandId = brandId;
    }
    public void setName(String name) {
        this.name = name;
    }


}

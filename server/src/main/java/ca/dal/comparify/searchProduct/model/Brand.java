package ca.dal.comparify.searchProduct.model;

import javax.persistence.Entity;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Brand {
    String brandId; 
   
    String brandName;
    
    public Brand() {
    }
    public Brand(String brandId, @BsonProperty("name") String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }
    public String getBrandId() {
        return brandId;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


}

package ca.dal.comparify.searchProduct.model;



import javax.persistence.Entity;

import org.bson.codecs.pojo.annotations.BsonId;

@Entity
public class Store {
    @BsonId
    String storeId;
    String storeName;

    public Store() {
    }
    public Store(String storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }
    public String getStoreID() {
        return storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
 
}

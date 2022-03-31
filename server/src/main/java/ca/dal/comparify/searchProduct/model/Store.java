package ca.dal.comparify.searchProduct.model;



import javax.persistence.Entity;


public class Store {
    String storeID;
    String storeName;

    public Store() {
    }
    public Store(String storeID, String storeName) {
        this.storeID = storeID;
        this.storeName = storeName;
    }
    public String getStoreID() {
        return storeID;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
 
}

package ca.dal.comparify.searchProduct.model;

import javax.persistence.Entity;


public class ItemDetail {
    String brandId;
    String productId;
    String storeId;
    double unit;
    double price;
   

    public ItemDetail() {
    }
    public ItemDetail(String brandId, String productId, String storeID, double unit, double price) {
        this.brandId = brandId;
        this.productId = productId;
        this.storeId = storeID;
        this.unit = unit;
        this.price = price;
    }
    public String getBrandId() {
        return brandId;
    }
    public String getProductId() {
        return productId;
    }
    public String getStoreId() {
        return storeId;
    }
    public double getPrice() {
        return price;
    }
    public double getUnit() {
        return unit;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setStoreId(String storeID) {
        this.storeId = storeID;
    }
    public void setUnit(double unit) {
        this.unit = unit;
    }
}

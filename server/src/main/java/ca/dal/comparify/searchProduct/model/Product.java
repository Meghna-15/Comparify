package ca.dal.comparify.searchProduct.model;

import javax.persistence.Entity;



public class Product {
   
   
    private String Productname;
    private String BrandName;
    private String StoreName;
    private double unitOrVolume;
    private double Price;

    public Product(){

    }
    public Product(String productname, String brandName, String storeName, double unitOrVolume, double price) {
    
        Productname = productname;
        BrandName = brandName;
        StoreName = storeName;
        this.unitOrVolume = unitOrVolume;
        Price = price;
    }
  
    public String getBrandName() {
        return BrandName;
    }
    public String getProductname() {
        return Productname;
    }
    public String getStoreName() {
        return StoreName;
    }
    public double getUnitOrVolume() {
        return unitOrVolume;
    }
    public double getPrice() {
        return Price;
    }
   
    public void setBrandName(String brandName) {
        this.BrandName = brandName;
    }
    public void setProductname(String productname) {
        this.Productname = productname;
    }
    public void setStoreName(String storeName) {
        this.StoreName = storeName;
    }
    public void setUnitOrVolume(double unitOrVolume) {
        this.unitOrVolume = unitOrVolume;
    }
    public void setPrice(double price) {
        this.Price = price;
    }
}

package ca.dal.comparify.searchProduct.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Product {
   
   
    private String Productname;
    private String BrandName;
    private String StoreName;
    private double unitOrVolume;
    private double Price;
    private String image;
    private String description;
<<<<<<< HEAD
=======
    private String itemId;
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f

    @BsonId
    private String recordId;

    public Product(){

    }
<<<<<<< HEAD
    public Product(String productname, String brandName, String storeName, double unitOrVolume, double price,String image,String description,@BsonId String  recordId) {
=======


    public Product(String productname, String brandName, String storeName,
                   double unitOrVolume, double price, String image, String
                       description, String itemId, String recordId) {
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f
        Productname = productname;
        BrandName = brandName;
        StoreName = storeName;
        this.unitOrVolume = unitOrVolume;
        Price = price;
<<<<<<< HEAD
        this.image=image;
        this.description=description;
        this.recordId=recordId;
    }
  
=======
        this.image = image;
        this.description = description;
        this.itemId = itemId;
        this.recordId = recordId;
    }

>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
<<<<<<< HEAD
=======

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f
}

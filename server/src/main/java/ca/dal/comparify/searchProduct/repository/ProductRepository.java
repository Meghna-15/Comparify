package ca.dal.comparify.searchProduct.repository;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.store.model.StoreModel;


import java.util.ArrayList;
import java.util.List;
import static ca.dal.comparify.mongo.MongoUtils.eq;

import static ca.dal.comparify.mongo.MongoUtils.and;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRepository {

    @Autowired
    private MongoRepository mongoRepository;
    static final String STORECOLLECTION_NAME = "store";
    static final String ITEMDETAILCOLLECTION_NAME = "compareItems";
    static final String ITEMCOLLECTION_NAME = "item";
    static final String BRRANDCOLLECTION_NAME = "brand";

    private final String ITEM_ID = "productId";
    private final String STOREID = "_id";
    private final String BRANDID = "_id";
    private final String ITEMNAME = "name";

    Class<Product> productCLass = Product.class;
    Class<StoreModel> storeCLass = StoreModel.class;
    Class<ItemModel> itemCLass = ItemModel.class;
    Class<BrandModel> brandCLass = BrandModel.class;
    Class<CompareItemsModel> itemDetailCLass = CompareItemsModel.class;

    public List<Product> getAllProducts(String itemName) {
        List<Product> products = new ArrayList<>();
        try {
            ItemModel item = mongoRepository.findOne(ITEMCOLLECTION_NAME, eq(ITEMNAME, itemName), itemCLass);
            Bson filter =  and(eq(ITEM_ID, item.getId()), eq("status", "verified"));
            List<CompareItemsModel> itemsDetails = mongoRepository.find(ITEMDETAILCOLLECTION_NAME,
                   filter,itemDetailCLass);

            for (CompareItemsModel itemDetail : itemsDetails) {
                Bson matchStore = eq(STOREID,
                        new ObjectId(itemDetail.getStoreId()));
                StoreModel store = mongoRepository.findOne(STORECOLLECTION_NAME, matchStore,
                        storeCLass);
                String storeName = store.getStoreName();
                Bson matchBrand = eq(BRANDID, itemDetail.getBrandId());
                BrandModel brand = mongoRepository.findOne(BRRANDCOLLECTION_NAME, matchBrand,
                        brandCLass);
                String brandName = brand.getName();
                String productName = item.getName();
                double price = itemDetail.getPrice();
                double unit = itemDetail.getUnit();
                String image = item.getDefaultImage();
                String description = item.getDescription();

                String recordId = itemDetail.getId().toString();
                Product p = new Product(productName, brandName, storeName, price, unit, image, description,
                    item.getId(),  recordId);
                products.add(p);
            }

        } catch (Exception e) {  
            return products;
        }
      
       
        return products;
    }

}

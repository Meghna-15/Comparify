package ca.dal.comparify.searchProduct.repository;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;

import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.store.model.StoreModel;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Filters;

import static ca.dal.comparify.mongo.MongoUtils.eq;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
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
    Class<Store> storeCLass = Store.class;
    Class<Item> itemCLass = Item.class;
    Class<Brand> brandCLass = Brand.class;
    Class<ItemDetail> itemDetailCLass = ItemDetail.class;

    public List<Product> getAllProducts(String itemName) {
        List<Product> products = new ArrayList<>();
        Item item = mongoRepository.findOne(ITEMCOLLECTION_NAME, eq(ITEMNAME, itemName), itemCLass);

        List<ItemDetail> itemsDetails = mongoRepository.find(ITEMDETAILCOLLECTION_NAME, eq(ITEM_ID, item.getId()),
                itemDetailCLass);

        for (ItemDetail itemDetail : itemsDetails) {
            // String storeName = mongoRepository
            // .findOne(STORECOLLECTION_NAME, eq(STOREID, itemDetail.getStoreId()),
            // storeCLass).getStoreName();
            Store store = mongoRepository.findOne(STORECOLLECTION_NAME, eq(STOREID, itemDetail.getStoreId()),
                    storeCLass);
            String storeName = store.getStoreName();
            List<Brand> brands = mongoRepository.find(BRRANDCOLLECTION_NAME, Filters.empty(),brandCLass);
            Brand brand = mongoRepository.findOne(BRRANDCOLLECTION_NAME, eq(BRANDID, itemDetail.getBrandId()),
                    brandCLass);
            String brandName = brand.getBrandName();
            String productName = item.getName();
            double price = itemDetail.getPrice();
            double unit = itemDetail.getUnit();
            Product p = new Product(productName, brandName, storeName, price, unit);
            products.add(p);
        }

        return products;
    }

}

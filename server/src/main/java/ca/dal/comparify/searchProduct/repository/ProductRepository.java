package ca.dal.comparify.searchProduct.repository;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.searchProduct.model.Brand;
import ca.dal.comparify.searchProduct.model.Item;
import ca.dal.comparify.searchProduct.model.ItemDetail;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.model.Store;
import io.micrometer.core.ipc.http.HttpSender.Method;

import java.util.ArrayList;
import java.util.List;
import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.MongoUtils.set;
import static ca.dal.comparify.mongo.MongoUtils.and;
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
           
            Store store = mongoRepository.findOne(STORECOLLECTION_NAME, eq(STOREID, (String)itemDetail.getStoreId()),
                    storeCLass);
            String storeName = store.getStoreName();
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

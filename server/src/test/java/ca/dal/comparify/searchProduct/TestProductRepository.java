package ca.dal.comparify.searchProduct;

import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.repository.ProductRepository;
import ca.dal.comparify.store.model.StoreModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class TestProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private MongoRepository mongoRepository;

    static final String STORECOLLECTION_NAME = "store";
    static final String ITEMDETAILCOLLECTION_NAME = "compareItems";
    static final String ITEMCOLLECTION_NAME = "item";
    static final String BRRANDCOLLECTION_NAME = "brand";
    StoreModel store = new StoreModel();
    ItemModel item = new ItemModel();
    BrandModel brand = mock(BrandModel.class);
    CompareItemsModel itemDetail = new CompareItemsModel();
    List<Object> itemsDetails = new ArrayList<>();


    @Test
    public void getAllQuestionSuccessful() {
        
        when(mongoRepository.findOne(anyString(), any(), any())).thenReturn(item);
        when(mongoRepository.findOne(anyString(), any(Bson.class), any())).thenReturn(store);
        when(mongoRepository.findOne(anyString(), any(Bson.class), any())).thenReturn(brand);
        when(mongoRepository.find(anyString(), any(Bson.class), any())).thenReturn(itemsDetails);
        List<Product> products = new ArrayList<>();
        try {
            ItemModel item = mongoRepository.findOne(ITEMCOLLECTION_NAME, any(), any());

            List<CompareItemsModel> itemsDetails = mongoRepository.find(ITEMDETAILCOLLECTION_NAME,
                    any(),any());

            for (CompareItemsModel itemDetail : itemsDetails) {

                StoreModel store = mongoRepository.findOne(STORECOLLECTION_NAME, any(), any());
                String storeName = store.getStoreName();
                BrandModel brand = mongoRepository.findOne(BRRANDCOLLECTION_NAME, any(), any());
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
            e.printStackTrace();
        }
        assertEquals(productRepository.getAllProducts(any()), products);
    }

    @Test
    public void getAllProductsFail() {
       
        when(mongoRepository.find(anyString(), any(Bson.class), any())).thenThrow(new NullPointerException());
        assertEquals(productRepository.getAllProducts(any()),new ArrayList<>());
    }

}
package ca.dal.comparify.searchProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.repository.ProductRepository;


public class TestSearchProductController {
    MongoRepository mongoRepository = mock(MongoRepository.class);
    // // ProductRepository productRepository = new ProductRepository();  
    // @Test
    public void testSearchProduct() {
        // List<Product> 
        // assertEquals(,ProductRepository.getAllProducts("Milk"));    
    }

}

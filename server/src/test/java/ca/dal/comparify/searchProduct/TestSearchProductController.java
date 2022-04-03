package ca.dal.comparify.searchProduct;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ca.dal.comparify.searchProduct.model.Product;

import java.util.List;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestSearchProductController {
    
    @Autowired
    private MockMvc mockMvc;    

    @MockBean
    private SearchProductController searchProductController;

    @Test
    public void testSearchProduct() throws Exception {
        Product product = new Product();
        product.setProductname("Milk");
        List<Product> allProducts = singletonList(product);
        given(searchProductController.getProducts(product.getProductname())).willReturn(allProducts);

        mockMvc.perform(get("/product/search?name=Milk")

                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productname", is(product.getProductname())));
    }

}

package ca.dal.comparify.Appreciation;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.model.AppreciationModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class AppreciationRepositoryTest {

    @Autowired
    private AppreciationRepository appreciateRepository;

    @MockBean
    private MongoRepository mongoRepository;

    @Test
    void saveUserAppreciationTest()
    {
        AppreciationModel model = new AppreciationModel();
        model.setPoints(10.0);
        model.setType("silver");
        model.setUsername("john");

        when(mongoRepository.updateOne((String) any(), (Bson) any(), (Bson) any())).thenReturn(true);

        assertEquals(appreciateRepository.saveUserAppreciation(model),true);
    }

    @Test
    void billItemsOfSameProductsTest()
    {
        Date date = new Date();

        CompareItemsModel model = new CompareItemsModel();
        model.setStatus("verified");
        model.setBrandId("6247c13f28da7f42a60913e2");
        model.setDateOfPurchase(date);
        model.setImageText("<base64>");
        model.setPrice(20.0);
        model.setPriceAfterDiscount(2.0);
        model.setProductId("6247c13f28da7f42a60913e2");
        model.setStoreId("6247c13f28da7f42a60913e2");
        model.setUnit(2.0);
        model.setId(new ObjectId(("6247c13f28da7f42a60913e3")));

        List models = new ArrayList<>();
        models.add(model);

        when(mongoRepository.find(any(),any(),any())).thenReturn(models);

        assertEquals(appreciateRepository.billItemsOfSameProducts(model).size(),models.size());
    }
}

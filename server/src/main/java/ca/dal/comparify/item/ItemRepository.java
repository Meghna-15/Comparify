package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.mongo.MongoRepository;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chanpreet Singh
 */
@Service
public class ItemRepository {

    public static final String ITEM_COLLECTION = "item";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param model
     * @return
     *
     * @author Chanpreet Singh
     */
    public int save(ItemModel model){
        return mongoRepository.insertOne(ITEM_COLLECTION, model, ItemModel.class);
    }

    /**
     * @author Chanpreet Singh
     */
    public List<ItemModel> getAll(){
        List<ItemModel> result = mongoRepository.find(ITEM_COLLECTION, Filters.empty(), ItemModel.class);
        return result;
    }

    /**
     * @author Chanpreet Singh
     */
    public ItemModel searchItemName(String ItemName){
        ItemModel result = mongoRepository.findOne(ITEM_COLLECTION, Filters.regex("name", "/^" + ItemName + "$/i"), ItemModel.class);
        return result;
    }
}

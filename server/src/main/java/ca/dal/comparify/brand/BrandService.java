package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.brand.model.BrandRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */

    public int create(BrandRequestModel model, String createdBy){
        return brandRepository.save(BrandModel.transform(model, createdBy));
    }

    /**
     * @return
     *
     * @author Chanpreet Singh
     */
    public ArrayList<Map> getAllBrands(){
        List<BrandModel> mongoResult = brandRepository.getAll();
        ArrayList<Map> result = new ArrayList<>();
        for(BrandModel each:mongoResult) {
            Map<String, String> eachObj = new HashMap<String, String>(){{
                put("id", each.getId());
                put("name", each.getName());
                put("description", each.getDescription());}};
            result.add(eachObj);
        }
        return result;
    }
}

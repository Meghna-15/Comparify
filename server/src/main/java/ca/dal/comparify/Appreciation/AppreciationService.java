package ca.dal.comparify.Appreciation;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amansinghbhandari
 */
@Service
public class AppreciationService {


    @Autowired
    private AppreciationRepository appreciationRepository;


    /**
     * @param addAppreciation
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean addAppreciation(CompareItemsModel compareItemsModel)
    {

        List<CompareItemsModel> listOfEntries= appreciationRepository.billItemsOfSameProducts(compareItemsModel);
        System.out.println(listOfEntries.size());

        return true;
    }


}

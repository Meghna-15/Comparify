package ca.dal.comparify.Appreciation;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.model.AppreciationModel;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.repository.iam.UserDetailsRepository;
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

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    private final double ADDITIONAL_POINTS = 20.0;

    public final double GOLD_MEMBER_MIN = 50.0;

    private final String GOLD_MEMBERSHIP = "gold";

    private final String SILVER_MEMBERSHIP = "silver";

    /**
     * @param compareItemsModel
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean addAppreciation(CompareItemsModel compareItemsModel)
    {

        List<CompareItemsModel> listOfEntries= appreciationRepository.billItemsOfSameProducts(compareItemsModel);

        List<String> users = fetchUserList(listOfEntries);

        //---- appreciate users ---
        for(String userId : users)
        {
            UserDetailsModel userModels = userDetailsRepository.fetchUserDetails(userId);
            Double totalPoints = userModels.getPoints() + ADDITIONAL_POINTS;
            AppreciationModel appreciationModel = new AppreciationModel(userId,totalPoints,evaluateMembership(totalPoints));

            Boolean result = appreciationRepository.saveUserAppreciation(appreciationModel);

            if(result == false)
            {
                return false;
            }
        }
        //-----

        return true;
    }

    public List<String> fetchUserList(List<CompareItemsModel> compareItemsModels)
    {
        List<String> users = new ArrayList<>();
        for(CompareItemsModel compareItemsModel : compareItemsModels)
        {
            users.add(compareItemsModel.getUserId());
        }

        return users;
    }

    private String evaluateMembership(double point)
    {
        return point >= GOLD_MEMBER_MIN ? GOLD_MEMBERSHIP : SILVER_MEMBERSHIP;
    }


}

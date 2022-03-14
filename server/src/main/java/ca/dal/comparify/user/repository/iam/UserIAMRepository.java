package ca.dal.comparify.user.repository.iam;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import com.mongodb.MongoException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author Harsh Shah
 */
@Service
public class UserIAMRepository {

    public static final String USER_IAM_COLLECTION = "user_identity_management";

    public static final String USER_AUTHORIZATION_KEY = "authorization";

    public static final String USER_AUTHENTICATION_KEY = "authentication";

    @Autowired
    private MongoRepository mongoRepository;


    /**
     * @param userIdentifier
     * @return
     */
    public UserIAMModel fetchUserAuthenticationInfo(String userIdentifier) {

        return mongoRepository.findOne(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier),
                new Document(USER_AUTHENTICATION_KEY, 1)
                        .append(UserIAMModel.USER_IDENTIFIER, 1),
                UserIAMModel.class);
    }

    /**
     * @param userIdentifier
     * @return
     */
    public boolean isUserExists(String userIdentifier) {
        return mongoRepository.count(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier)) > 0;
    }

    /**
     * @param userIAMModel
     * @return
     */
    public boolean save(UserIAMModel userIAMModel) {
        try {
            mongoRepository.insert(USER_IAM_COLLECTION, userIAMModel, UserIAMModel.class);
            return true;
        } catch (MongoException e) {
            return false;
        }
    }
}

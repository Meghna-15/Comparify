package ca.dal.comparify.user.service;

import ca.dal.comparify.user.model.SignupRequest;
import ca.dal.comparify.user.repository.UserRepository;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationResponseModel;
import ca.dal.comparify.user.model.authentication.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param authenticationRequestModel
     * @return
     *
     * @author Harsh Shah
     */
    public UserIAMResponseModel authenticate(UserIAMRequestModel authenticationRequestModel) {
        return userIAMService.authenticate(authenticationRequestModel);
    }

    /**
     * @param userIdentifier
     * @return
     *
     * @author Harsh Shah
     */
    public UserPrincipal fetchUser(String userIdentifier) {
        return userIAMService.fetchUser(userIdentifier);
    }


    /**
     * @param userIdentifier
     * @param secret
     * @return
     *
     * @author Harsh Shah
     */
    public int createUserIAMInfo(String userIdentifier, String secret){
        return userIAMService.createUserIAMInfo(userIdentifier, secret);
    }

    public boolean register(SignupRequest signupRequest) {
        return userRegistrationService.register(signupRequest);

    }
}

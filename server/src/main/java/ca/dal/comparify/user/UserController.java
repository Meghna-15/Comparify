package ca.dal.comparify.user;

import ca.dal.comparify.framework.exception.InvalidFormatException;
import ca.dal.comparify.framework.exception.MissingRequiredFieldException;
import ca.dal.comparify.user.model.SignupRequest;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationResponseModel;
import ca.dal.comparify.user.service.UserService;
import ca.dal.comparify.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @param userIAMRequestModel
     * @return
     * @author Harsh Shah
     */
    @PostMapping("/authentication")
    public UserIAMResponseModel authentication(@RequestBody UserIAMRequestModel userIAMRequestModel) {

        if (userIAMRequestModel.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, userIAMRequestModel.getRequiredFields());
        }

        return userService.authenticate(userIAMRequestModel);
    }

    /**
     * @param signupRequest
     * @return
     * @author Harsh Shah
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> register(@RequestBody SignupRequest signupRequest){
        // validate - optional
        System.out.println("Working");
        if(!signupRequest.validate()){
            throw new MissingRequiredFieldException(400, 1000, new ArrayList<>());
        }

        if(!signupRequest.validateEmail())
        {
            throw new InvalidFormatException("Invalid Format",1000,2005);
        }

        if(!signupRequest.HasValidPasswordPattern(signupRequest.getPassword())){
            throw new InvalidFormatException("Invalid Format",1000,2005);
        }

        boolean status = userService.register(signupRequest);

        if(status) {

            UserAuthenticationRequestModel authenticationRequestModel = new UserAuthenticationRequestModel(signupRequest.getUsername(),
                    signupRequest.getPassword());


            if (authenticationRequestModel.isEmpty()) {
                throw new MissingRequiredFieldException(400, 1000, authenticationRequestModel.getRequiredFields());
            }


            status = userService.createUserAuthentication(authenticationRequestModel.getUserIdentifier(),
                    authenticationRequestModel.getSecret());
        }


        return ResponseEntityUtils.returnStatus(status);
    }


    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    @GetMapping("/details")
    public UserDetailsModel getUserDetails(@RequestParam(name = "username") String username) {

        if (username.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, UserDetailsModel.getRequiredFields());
        }

        return userDetailsService.fetchUser(username);
    }


}

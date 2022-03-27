package ca.dal.comparify.user;

import ca.dal.comparify.framework.exception.InvalidFormatException;
import ca.dal.comparify.framework.exception.MissingRequiredFieldException;
import ca.dal.comparify.user.model.SignupRequest;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.service.UserDetailsService;
import ca.dal.comparify.user.service.UserService;
import ca.dal.comparify.utils.ResponseEntityUtils;
import ca.dal.comparify.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Map<String, String>> register(@RequestBody SignupRequest signupRequest){
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

        int status = userService.register(signupRequest);

        if(status == 0) {

            UserIAMRequestModel userIAMRequestModel = new UserIAMRequestModel(signupRequest.getUsername(),
                    signupRequest.getPassword());


            if (userIAMRequestModel.isEmpty()) {
                throw new MissingRequiredFieldException(400, 1000, userIAMRequestModel.getRequiredFields());
            }

            status = userService.createUserIAMInfo(userIAMRequestModel.getUserIdentifier(),
                userIAMRequestModel.getUserSecret());
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

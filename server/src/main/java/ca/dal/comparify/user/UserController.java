package ca.dal.comparify.user;

import ca.dal.comparify.framework.exception.InvalidFormatException;
import ca.dal.comparify.framework.exception.MissingRequiredFieldException;
import ca.dal.comparify.user.model.SignupRequest;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationResponseModel;
import ca.dal.comparify.user.service.UserService;
import ca.dal.comparify.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param authenticationRequestModel
     * @return
     */
    @PostMapping("/authentication")
    public UserAuthenticationResponseModel authentication(@RequestBody UserAuthenticationRequestModel authenticationRequestModel){

        if(authenticationRequestModel.isEmpty()){
            throw new MissingRequiredFieldException(400, 1000, authenticationRequestModel.getRequiredFields());
        }

        return userService.authenticate(authenticationRequestModel);
    }

    /**
     * @param signupRequest
     * @return
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


        HttpStatus httpStatus = status ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(Collections.singletonMap("status", status));

    }


}

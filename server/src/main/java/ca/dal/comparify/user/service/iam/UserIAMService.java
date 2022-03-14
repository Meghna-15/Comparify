package ca.dal.comparify.user.service.iam;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.framework.exception.EntityAlreadyExistsException;
import ca.dal.comparify.security.TokenService;
import ca.dal.comparify.user.enumeration.UserErrorCode;
import ca.dal.comparify.user.exception.authentication.UserAuthenticationException;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.model.iam.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.iam.authentication.UserPrincipal;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel;
import ca.dal.comparify.user.repository.iam.UserIAMRepository;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static ca.dal.comparify.constant.ApplicationConstant.SYSTEM;
import static ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel.CREATE_ACTION;
import static ca.dal.comparify.user.model.iam.authorization.UserAuthorizationRoleEnum.USER;

@Service
public class UserIAMService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserIAMRepository userIAMRepository;

    /**
     * @param userIdentifier
     * @return
     */
    public UserPrincipal fetchUser(String userIdentifier) {

        UserIAMModel userIAMModel = userIAMRepository
                .fetchUserAuthenticationInfo(userIdentifier);

        if (userIAMModel == null) {
            throw new UsernameNotFoundException(ApplicationConstant.CANNOT_FIND_USERNAME);
        }

        return UserPrincipal.create(userIAMModel);
    }

    /**
     * @param requestModel
     * @return
     */
    public UserIAMResponseModel authenticate(UserIAMRequestModel requestModel) {

        UsernamePasswordAuthenticationToken userCredAuthToken = new UsernamePasswordAuthenticationToken(
                requestModel.getUserIdentifier(),
                requestModel.getUserSecret());

        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(userCredAuthToken);
        } catch (DisabledException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2000.getCode());
        } catch (BadCredentialsException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2001.getCode());
        } catch (LockedException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2002.getCode());
        } catch (CredentialsExpiredException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2003.getCode());
        }


        return new UserIAMResponseModel(tokenService.generateToken(auth));
    }

    /**
     * @param userIdentifier
     * @param secret
     * @return
     */
    public boolean createUserIAMInfo(String userIdentifier, String secret) {

        if (isUserExists(userIdentifier)) {
            throw new EntityAlreadyExistsException("User already exists having " + userIdentifier + " User Identifier",
                    400, UserErrorCode.E2004.getCode());
        }

        UserAuthorizationModel authorization = new UserAuthorizationModel(USER, CREATE_ACTION, SYSTEM);

        UserAuthenticationModel authentication = new UserAuthenticationModel(
                passwordEncoder.encode(secret),
                true,
                0,
                DateUtils.addDaysToLocalNow(30),
                DateUtils.addDaysToLocalNow(30),
                new ArrayList<>());

        return userIAMRepository.save(new UserIAMModel(userIdentifier, authentication, authorization));

    }

    private boolean isUserExists(String userIdentifier) {
        return userIAMRepository.isUserExists(userIdentifier);
    }
}

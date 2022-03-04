package ca.dal.comparify.framework.security;

import ca.dal.comparify.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     *
     * @author Harsh Shah
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.fetchUser(username);
    }
}

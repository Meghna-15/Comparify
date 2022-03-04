package ca.dal.comparify.framework.security.filters.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Harsh Shah
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationProviders authenticationProviders;

    /**
     * @param request a
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     *
     * @author Harsh Shah
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication auth = authenticationProviders.getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    /**
     * @param authenticationProviders
     *
     * @author Harsh Shah
     */
    public void setAuthenticationProviders(AuthenticationProviders authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
    }
}

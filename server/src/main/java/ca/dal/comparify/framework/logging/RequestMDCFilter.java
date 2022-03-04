package ca.dal.comparify.framework.logging;

import org.slf4j.MDC;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Harsh Shah
 */
@Component
public class RequestMDCFilter implements Filter {

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     *
     * @author Harsh Shah
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        MDC.put("ClientRemoteAddress", request.getRemoteAddr());
        MDC.put("ClientRequestId", request.getHeader("ClientRequestId")) ;

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
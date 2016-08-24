package com.cheliadina.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author nastya
 */
public class AuthorisationFilter implements Filter {

    public static final String AUTH_ATTR = "login_attr";
    public static final String USER_ATTR = "user_attr";
    public static final int SESSION_TIME_MINUTES = 15;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession();
        LocalDateTime time = (LocalDateTime) httpSession.getAttribute(AUTH_ATTR);
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (!uri.startsWith("/resources")) {
            if (isLoggedIn(time)) {
                if (isLoginIntention(uri)) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/");
                    return;
                }
            } else {
                if (!isLoginIntention(uri)) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/login");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isLoginIntention(String uri) {
        return uri.equalsIgnoreCase("/login")
                || uri.equalsIgnoreCase("/submit-login");
    }

    @Override
    public void destroy() {

    }

    private boolean isLoggedIn(LocalDateTime time) {
        return time != null && !LocalDateTime.now().isAfter(time.plusMinutes(SESSION_TIME_MINUTES));
    }
}

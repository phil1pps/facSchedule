package com.example.facSchedule.config;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do filter...");
        String token = null;
        try {
            token = readServletCookie((HttpServletRequest) servletRequest).get();
            logger.info(token);
        }
        catch (NullPointerException ex) {
            logger.info("no token...");
        }
        catch (NoSuchElementException ex) {
            logger.info("no token...");
        }
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
/*
            Cookie jwtTokenCookie = new Cookie("user-id", token);
            jwtTokenCookie.setMaxAge(86400);
            jwtTokenCookie.setSecure(true);
            jwtTokenCookie.setHttpOnly(true);
            ((HttpServletResponse)servletResponse).addCookie(jwtTokenCookie);*/
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
    public Optional<String> readServletCookie(HttpServletRequest request){
        String name = "user-id";
        return Arrays.stream(request.getCookies())
                .filter(cookie->name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
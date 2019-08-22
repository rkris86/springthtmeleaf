package com.alvyl.springthymeleaf.validations;

import com.alvyl.springthymeleaf.exception.CaptchaException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.DelegatingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginValidationFilter extends UsernamePasswordAuthenticationFilter {
    public LoginValidationFilter() {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String captcha = request.getParameter("captcha");
        String sessionCaptcha = (String) request.getSession(true).getAttribute("CAPTCHA");
        if (captcha.equals(sessionCaptcha)) {
            return super.attemptAuthentication(request, response);
        }
        return null;
    }
}

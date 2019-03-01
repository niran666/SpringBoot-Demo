package com.example.demo.se.ImageCode;

import com.example.demo.Controller.ValidateCodeController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        if(StringUtils.equals("/login",httpServletRequest.getRequestURI())//登陆网址
                && StringUtils.endsWithIgnoreCase(httpServletRequest.getMethod(),"POST")){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        logger.info("进行验证码验证");

        // 获取session中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"validateCode");

        if(StringUtils.isBlank(codeInRequest)){
            throw  new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null || codeInSession.isExpired()){
            throw  new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInRequest,codeInSession.getCode())){
            throw  new ValidateCodeException("验证码错误");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY_IMAGE_CODE);
    }
}
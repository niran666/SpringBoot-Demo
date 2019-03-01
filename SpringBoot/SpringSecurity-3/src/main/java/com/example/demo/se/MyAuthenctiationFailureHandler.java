package com.example.demo.se;
import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        logger.info("进入认证失败处理类");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        //转发到login
//		request.getRequestDispatcher("/login?error="+exception.getMessage()).forward(request, response);
//      response.sendRedirect("/login?error="+objectMapper.writeValueAsString(exception.getMessage()));
        response.getWriter().write( JSON.toJSONString(new Result(false,objectMapper.writeValueAsString(exception.getLocalizedMessage()))));
//        response.getWriter().write(objectMapper.writeValueAsString(exception));
        return;
    }
}
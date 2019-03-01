package com.example.demo.se;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
//        HttpServletRequest request = event.getRequest();
//        JSONObject returnObj = new JSONObject();
//        if (RequestUtils.isAjax(request)) {
//            returnObj.put("status", "0");
//        } else {
//            returnObj.put("status", "-1");
//            returnObj.put("message", "非法登录");
//        }
        logger.info("进入过期处理");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write( JSON.toJSONString(new Result(false,"登陆过期")));
//        response.getWriter().print(returnObj.toJSONString());
//        response.flushBuffer();
    }
}
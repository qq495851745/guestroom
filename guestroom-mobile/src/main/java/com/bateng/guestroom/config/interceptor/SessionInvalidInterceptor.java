package com.bateng.guestroom.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Date;

/**
 * Session失效拦截
 */
public class SessionInvalidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<!DOCTYPE html >");
            buffer.append("<html >");
            buffer.append("<head>");
            buffer.append("<meta charset=\"UTF-8\"/>");
            buffer.append("<script type=\"text/javascript\">").append("window.alert('登录失效，请重新登录');").append("window.location.href=\"/\"").append("</script>");
            buffer.append("</head>");
            buffer.append("</html>");
            OutputStream out = response.getOutputStream();
            out.write(buffer.toString().getBytes("utf-8"));
            out.flush();
            out.close();
            return  false;
        }else
            return true;
    }
}

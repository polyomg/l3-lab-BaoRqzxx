package com.poly.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.poly.Entity.Account;

import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // Lấy user từ session
        Account user = (Account) request.getSession().getAttribute("user");

        // Nếu user đã login
        if (user != null) {
            System.out.println("Access Log: URI=" + request.getRequestURI() +
                    ", Time=" + new Date() +
                    ", User=" + user.getFullname());
        } else {
            System.out.println("Access Log: URI=" + request.getRequestURI() +
                    ", Time=" + new Date() +
                    ", User=GUEST");
        }

        return true; // cho phép tiếp tục request
    }
}
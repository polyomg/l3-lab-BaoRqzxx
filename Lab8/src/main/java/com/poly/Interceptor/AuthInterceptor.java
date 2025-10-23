package com.poly.Interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.poly.Entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);
        Account user = (Account) session.getAttribute("user");
        if(user == null) { // chưa đăng nhập
            response.sendRedirect("/login");
            return false;


        }
        if(uri.startsWith("/admin") && !user.isAdmin()) { // không phải admin
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
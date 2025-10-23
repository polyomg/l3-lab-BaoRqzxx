package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import com.poly.Entity.Account;

@Controller
public class HomeController {

    @GetMapping("/user/home")
    public String home(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "Bạn cần đăng nhập trước khi truy cập trang chủ");
            return "login";
        }
        model.addAttribute("user", user);
        return "user/home"; // sẽ tạo home.html trong templates
    }
}

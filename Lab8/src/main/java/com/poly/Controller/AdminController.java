package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import com.poly.Entity.Account;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminPage(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "Bạn cần đăng nhập trước khi truy cập admin");
            return "login";
        }
        if (!user.isAdmin()) {
            model.addAttribute("message", "Bạn không có quyền truy cập trang admin");
            return "user/home";
        }
        model.addAttribute("user", user);
        return "admin/dashboard"; // sẽ tạo admin.html trong templates
    }
}

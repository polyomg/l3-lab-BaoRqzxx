package com.poly.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.poly.Entity.Account;
import com.poly.Service.AccountService;

@Controller //Bai4,5,6
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String loginForm(Model model) {
        session.invalidate();
        return "login"; // hiển thị form login
    }

    @PostMapping("/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        Account user = accountService.findById(username);

        if (user == null) {
            model.addAttribute("message", "❌ Invalid username!");
            return "/login"; // login thất bại -> vẫn ở trang login
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "❌ Invalid password!");
            return "/login"; // login thất bại -> vẫn ở trang login
        }

        // login thành công
        session.setAttribute("user", user);

        // Nếu trước đó user muốn vào trang bảo mật, redirect về đó
        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri"); // tránh redirect vòng lặp
            return "redirect:" + securityUri;
        }

        // Redirect theo quyền
        if (user.isAdmin()) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/home";
        }
    }

}

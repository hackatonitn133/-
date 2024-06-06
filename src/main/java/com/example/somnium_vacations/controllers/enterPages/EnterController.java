package com.example.somnium_vacations.controllers.enterPages;

import com.example.somnium_vacations.models.UserModel;
import com.example.somnium_vacations.services.users.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EnterController {

    UserService userService;
    @GetMapping("/log")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {

        if (error != null) {
            model.addAttribute("errorMessage", "Неправильный email или пароль");
        }

        return "enterPages/loginPage";
    }

    @GetMapping("/loged")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            String login = principal.getAttribute("name");
            if (email != null && login != null) {
                String dateOfCreationAccount = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
                userService.createUserMail(new UserModel(email, login));
            }
        }
        return "redirect:/";
    }
}

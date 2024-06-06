package com.example.somnium_vacations.controllers.enterPages;

import com.example.somnium_vacations.models.UserModel;
import com.example.somnium_vacations.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @GetMapping("/registration")
    public String registrationPage() {
        return "enterPages/registrationPage";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "confirmPassword") String confirmPassword,
            @RequestParam(name = "phoneNumber") String phoneNumber,
            Model model
    ) {
        if (email != null && password != null && confirmPassword != null && login != null) {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("errorMessage", "Пароли не совпадают!");
                model.addAttribute("email", email);
                model.addAttribute("login", login);
                model.addAttribute("phoneNumber", phoneNumber);

                return "enterPages/registrationPage";
            }

            if(!userService.createUser(new UserModel(email, login, password, phoneNumber))) {
                model.addAttribute("errorMessage", "Пользователь с таким логином уже существует!");
                return "enterPages/registrationPage";
            }
        }

        return "redirect:/";
    }

}

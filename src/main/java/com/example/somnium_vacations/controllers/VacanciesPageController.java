package com.example.somnium_vacations.controllers;

import com.example.somnium_vacations.models.ResponseModel;
import com.example.somnium_vacations.models.UserModel;
import com.example.somnium_vacations.models.VacancyModel;
import com.example.somnium_vacations.services.VacancyService;
import com.example.somnium_vacations.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class VacanciesPageController {

    private VacancyService service;
    private UserService userService;

    @GetMapping("/searchVacancies")
    public String searchVacanciesByKeyword(@Param("keyword") String keyword, Model model) {

        model.addAttribute("vacanciesList", service.findAllVacancies(keyword));

        return "userPages/vacanciesPage";
    }

    @GetMapping("/vacancy/{id}")
    public String getVacancyPageById(@PathVariable(name = "id") Long id,
                                     Model model)
    {

        model.addAttribute("vacancy", service.findVacancyById(id));

        return "userPages/vacancyInfoPage";
    }

    @PostMapping("/vacancy/{id}/sendResponse")
    public String sendResponse(Principal principal, @PathVariable(name = "id") Long id) {

        UserModel user = userService.findUserByPrincipal(principal);
        VacancyModel vacancy = service.findVacancyById(id);
        ResponseModel response = new ResponseModel(user.getFullName(), user.getPhoneNumber(), user.getEmail());

        List<ResponseModel> currentUserResponses = user.getUserResponses();
        currentUserResponses.add(response);
        user.setUserResponses(currentUserResponses);
        userService.saveUser(user);

        List<ResponseModel> currentVacancyResponses = vacancy.getVacancyResponses();
        currentVacancyResponses.add(response);
        vacancy.setVacancyResponses(currentVacancyResponses);
        service.addVacancy(vacancy);

        return "redirect:/searchVacancies`";
    }

}

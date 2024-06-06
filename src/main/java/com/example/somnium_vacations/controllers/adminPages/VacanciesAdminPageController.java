package com.example.somnium_vacations.controllers.adminPages;

import com.example.somnium_vacations.models.VacancyModel;
import com.example.somnium_vacations.repositories.VacancyRepository;
import com.example.somnium_vacations.services.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/adminPages")
@AllArgsConstructor
public class VacanciesAdminPageController {

    private VacancyService service;
    private static final String homeAdminPage = "/adminPages/manageVacancies";

    @GetMapping("/manageVacancies")
    public String manageVacanciesPage(Model model) {

        model.addAttribute("vacanciesList", service.findAllVacancies(""));

        return "adminPages/vacancies/manageVacanciesPage";
    }

    @GetMapping("/manageVacancies/add")
    public String addVacancyPage() {
        return "adminPages/vacancies/addVacancyPage";
    }

    @PostMapping("/manageVacancies/add")
    public String addVacancyPost(
        VacancyModel vacancy
    )
    {
        service.addVacancy(vacancy);
        return "redirect:" + homeAdminPage;
    }

    @GetMapping("/manageVacancies/{id}/setVacancyDisable")
    private String setVacancyDisable(@PathVariable(name = "id") Long id) {
        service.setVacancyClosedStatus(id);
        return "redirect:" + homeAdminPage;
    }

}

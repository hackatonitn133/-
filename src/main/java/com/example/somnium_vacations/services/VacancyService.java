package com.example.somnium_vacations.services;

import com.example.somnium_vacations.models.VacancyModel;
import com.example.somnium_vacations.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    public VacancyModel findVacancyById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<VacancyModel> findAllVacancies(String keyword) {
        if(keyword != null) {
            if(!keyword.equals("")) {
                return repository.search(keyword);
            }
        }

        return repository.findAll();
    }

    public void addVacancy(VacancyModel vacancy) {
        repository.save(vacancy);
    }

    public void setVacancyClosedStatus(Long id) {
        VacancyModel currentVacancy = findVacancyById(id);
        currentVacancy.setVacancyOpen(false);
        repository.save(currentVacancy);
    }

}

package com.example.somnium_vacations.repositories;

import com.example.somnium_vacations.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyModel, Long> {

    @Query("SELECT vacancy FROM VacancyModel vacancy WHERE vacancy.vacancyName LIKE %?1% OR vacancy.vacancyCityLocation LIKE %?1% OR vacancy.vacancySubdivision LIKE %?1%")
    List<VacancyModel> search(String keyword);

}

package com.example.somnium_vacations.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "vacancies")
@AllArgsConstructor
@NoArgsConstructor
public class VacancyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vacancyName")
    private String vacancyName;

    @Column(name = "vacancyDescription", length = 10000)
    private String vacancyDescription;

    @Column(name = "vacancyCityLocation")
    private String vacancyCityLocation;

    @Column(name = "vacancySalary")
    private int vacancySalary;

    @Column(name = "vacancySubdivision")
    private String vacancySubdivision;

    @Column(name = "dateOfVacancyCreation")
    private String dateOfVacancyCreation;

    @Column(name = "isVacancyOpen")
    private boolean isVacancyOpen;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vacancy")
    private List<ResponseModel> vacancyResponses;

    @PrePersist
    private void init() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        dateOfVacancyCreation = formatter.format(new Date());
        isVacancyOpen = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    public String getVacancyCityLocation() {
        return vacancyCityLocation;
    }

    public void setVacancyCityLocation(String vacancyCityLocation) {
        this.vacancyCityLocation = vacancyCityLocation;
    }

    public int getVacancySalary() {
        return vacancySalary;
    }

    public void setVacancySalary(int vacancySalary) {
        this.vacancySalary = vacancySalary;
    }

    public String getVacancySubdivision() {
        return vacancySubdivision;
    }

    public void setVacancySubdivision(String vacancySubdivision) {
        this.vacancySubdivision = vacancySubdivision;
    }

    public String getDateOfVacancyCreation() {
        return dateOfVacancyCreation;
    }

    public void setDateOfVacancyCreation(String dateOfVacancyCreation) {
        this.dateOfVacancyCreation = dateOfVacancyCreation;
    }

    public boolean isVacancyOpen() {
        return isVacancyOpen;
    }

    public void setVacancyOpen(boolean vacancyOpen) {
        isVacancyOpen = vacancyOpen;
    }

    public List<ResponseModel> getVacancyResponses() {
        return vacancyResponses;
    }

    public void setVacancyResponses(List<ResponseModel> vacancyResponses) {
        this.vacancyResponses = vacancyResponses;
    }
}

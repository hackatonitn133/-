package com.example.somnium_vacations.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "responses")
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dateOfResponse")
    private String dateOfResponse;

    @Column(name = "userFullName")
    private String userFullName;

    @Column(name = "userPhoneNumber")
    private String userPhoneNumber;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userSummary")
    private String userSummary;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private UserModel user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private VacancyModel vacancy;

    @PrePersist
    private void initDateOfResponse() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        dateOfResponse = formatter.format(new Date());
    }

    public ResponseModel(String userFullName, String userPhoneNumber, String userEmail) {
        this.userFullName = userFullName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfResponse() {
        return dateOfResponse;
    }

    public void setDateOfResponse(String dateOfResponse) {
        this.dateOfResponse = dateOfResponse;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSummary() {
        return userSummary;
    }

    public void setUserSummary(String userSummary) {
        this.userSummary = userSummary;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public VacancyModel getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyModel vacancy) {
        this.vacancy = vacancy;
    }
}

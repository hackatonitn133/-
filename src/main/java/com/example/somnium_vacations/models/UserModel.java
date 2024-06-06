package com.example.somnium_vacations.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "dateOfCreationAccount")
    private String dateOfCreationAccount;

    @Column(name = "role")
    private String roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<ResponseModel> userResponses;

    public UserModel(String email, String login, String password, String phoneNumber) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public UserModel (String email, String login) {
        this.email = email;
        this.login = login;
    }

    @PrePersist
    private void initDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        dateOfCreationAccount = formatter.format(new Date());
        roles = "ROLE_USER";
    }

    public UserModel(String email, String login, String password, String fullName, String phoneNumber, String dateOfBirth, String dateOfCreationAccount, String roles, List<ResponseModel> userResponses) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfCreationAccount = dateOfCreationAccount;
        this.roles = roles;
        this.userResponses = userResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfCreationAccount() {
        return dateOfCreationAccount;
    }

    public void setDateOfCreationAccount(String dateOfCreationAccount) {
        this.dateOfCreationAccount = dateOfCreationAccount;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<ResponseModel> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<ResponseModel> userResponses) {
        this.userResponses = userResponses;
    }
}

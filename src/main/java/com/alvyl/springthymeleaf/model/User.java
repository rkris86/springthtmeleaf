package com.alvyl.springthymeleaf.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * User Class for Login and Registraction
 * @author Ramakrishna Gurram
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name="username")
    @NotEmpty(message = "Provide your UserName")
    private String username;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    @Email(message = "Provide your Valid Email")
    private String email;
    @Column(name = "name")
    @NotEmpty(message = "Provide your Name")
    private String name;
    @Transient
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

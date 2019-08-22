package com.alvyl.springthymeleaf.model;

import javax.persistence.*;

/**
 * Role Class for Roles
 * @author ramakrishna gurram
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "role")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

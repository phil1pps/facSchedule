package com.example.facSchedule.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotBlank(message = "login is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    private boolean enabled;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username", referencedColumnName="username"))
    @Enumerated(EnumType.STRING)
    private Set<Role> authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set<Role> getAuthorities() {
        return authority;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authority = authorities;
    }
}

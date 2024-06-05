package com.vedruna.tfg.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;  // Considera manejar la seguridad y privacidad adecuadamente
    private String description;
    private int idAvatar;
    private LocalDate createDate;
    private LocalDate editDate;

    // Constructor vacío
    public User() {
    }

    // Constructor con todos los campos
    public User(Long id, String username, String email, String password, String description, int idAvatar, LocalDate createDate, LocalDate editDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.description = description;
        this.idAvatar = idAvatar;
        this.createDate = createDate;
        this.editDate = editDate;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDate editDate) {
        this.editDate = editDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", idAvatar='" + idAvatar + '\'' +
                ", createDate=" + createDate +
                ", editDate=" + editDate +
                '}';
    }
}


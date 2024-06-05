package com.vedruna.tfg.DTO;

public class UserDTO {
    private Long id;
    private String username;

    private String email;
    private String description;
    private int idAvatar;

    public UserDTO() {
    }

    public UserDTO(Long id, String username,String email ,String description, int idAvatar) {
        this.id = id;
        this.username = username;
        this.email=email;
        this.description = description;
        this.idAvatar = idAvatar;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.vedruna.tfg.Auth;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    private int idAvatar;

    // Constructor, getters y setters
    public RegisterRequest(String username, String email, String password, int idAvatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.idAvatar = idAvatar;
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

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
    }
}

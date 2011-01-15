package ru.snslabs.icq.model;

/**
 * модель пользователя
 */
public class UserModel {
    String username;
    String role;

    public UserModel() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel(String username, String role) {
        this.username = username;
        this.role = role;
    }
}

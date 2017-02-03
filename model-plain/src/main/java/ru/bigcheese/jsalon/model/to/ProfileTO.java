package ru.bigcheese.jsalon.model.to;

import ru.bigcheese.jsalon.model.User;

import java.io.Serializable;

public class ProfileTO implements Serializable {

    private String username;
    private String surname;
    private String name;
    private String patronymic;
    private String password;
    private String newPassword;

    public ProfileTO() {}

    public ProfileTO(User user) {
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ProfileTO{" +
                "username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}

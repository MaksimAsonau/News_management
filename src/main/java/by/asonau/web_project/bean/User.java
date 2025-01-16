package by.asonau.web_project.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private UserRole userRole;
    private String name;
    private String surname;
    private String email;
    private String roleName;
    private String imageUrl;
    private LocalDate birthdayDate;
    private LocalDate registrationDate;
    private String address;

    public User(String login, UserRole userRole, String name, String surname, String email, String roleName, String imageUrl, LocalDate birthdayDate, LocalDate registrationDate, String address) {
        this.login = login;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleName = roleName;
        this.imageUrl = imageUrl;
        this.birthdayDate = birthdayDate;
        this.registrationDate = registrationDate;
        this.address = address;
    }

    public User(int id, String login, UserRole userRole, String name, String surname, String email, String roleName, String imageUrl, LocalDate birthdayDate, LocalDate registrationDate, String address) {
        this.id = id;
        this.login = login;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleName = roleName;
        this.imageUrl = imageUrl;
        this.birthdayDate = birthdayDate;
        this.registrationDate = registrationDate;
        this.address = address;
    }

    public User(int id, String login, UserRole userRole) {
        this.id = id;
        this.login = login;
        this.userRole = userRole;
    }

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && userRole == user.userRole && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(roleName, user.roleName) && Objects.equals(imageUrl, user.imageUrl) && Objects.equals(birthdayDate, user.birthdayDate) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, userRole, name, surname, email, roleName, imageUrl, birthdayDate, registrationDate, address);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", userRole=" + userRole +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", registrationDate=" + registrationDate +
                ", address='" + address + '\'' +
                '}';
    }
}

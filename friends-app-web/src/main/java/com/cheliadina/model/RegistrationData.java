package com.cheliadina.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author nastya
 */
public class RegistrationData {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 30)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

package com.example.ecommerce.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(name="user_sequence",
            sequenceName="user_sequence",

    allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dob;
    @Transient
    private Integer age;
    private String email;
    private String username;
    private String password;
    private String addressLine;
    private String mobileNum;


    public User() {
    }

    public User(Long id, String name,LocalDate dob, String email, String username, String password, String addressLine, String mobileNum) {
        this.id = id;
        this.name = name;
        this.dob=dob;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addressLine = addressLine;
        this.mobileNum = mobileNum;
    }

    public User(String name,LocalDate dob, String email, String username, String password, String addressLine, String mobileNum) {
        this.name = name;
        this.dob=dob;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addressLine = addressLine;
        this.mobileNum = mobileNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge(){
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                '}';
    }
}

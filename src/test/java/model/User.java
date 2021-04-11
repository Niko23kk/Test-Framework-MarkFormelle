package model;

import java.util.Objects;

public class User {
    private String userName;
    private String userSurname;
    private String password;
    private String phoneNumber;
    private String email;

    public User(String userName,String userSurname, String password, String phoneNumber, String email) {
        this.userName = userName;
        this.userSurname=userSurname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User(" +
                "username= '" + userName+ "'" +
                ", password= '" + password + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof User)) return false;
        User user=(User) o;
        return Objects.equals(getUsername(),user.getUsername()) &&
                Objects.equals(getPassword(),user.getPassword());
    }
}

package com.example.simplemeds;

public class users {
    private String fullname;
    private String email;
    private String password;
    private String telephone;
    private String age;
    private long uID;

    public users() {
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public long  getuID() {
        return uID;
    }

    public void setuID(long  uID) {
        this.uID = uID;
    }
}

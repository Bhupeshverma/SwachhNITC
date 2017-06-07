package com.example.bhupesh.swachhnew;

/**
 * Created by bhupesh on 29/4/17.
 */

public class Departments {

    String type, email, phone,description;

    public Departments() {
    }

    public Departments(String type, String email, String phone, String description) {
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

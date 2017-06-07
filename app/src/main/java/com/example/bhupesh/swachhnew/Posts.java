package com.example.bhupesh.swachhnew;

/**
 * Created by bhupesh on 7/5/17.
 */

public class Posts {

    String Image, Description, department_email, Department_name, UserName,UserEmail,UserUid;

    public Posts() {
    }

    public Posts(String image, String description, String department_email, String department_name, String userName, String userEmail, String userUid) {
        Image = image;
        Description = description;
        this.department_email = department_email;
        Department_name = department_name;
        UserName = userName;
        UserEmail = userEmail;
        UserUid = userUid;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDepartment_email() {
        return department_email;
    }

    public void setDepartment_email(String department_email) {
        this.department_email = department_email;
    }

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserUid() {
        return UserUid;
    }

    public void setUserUid(String userUid) {
        UserUid = userUid;
    }
}

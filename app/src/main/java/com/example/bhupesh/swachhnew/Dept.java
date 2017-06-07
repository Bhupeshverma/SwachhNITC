package com.example.bhupesh.swachhnew;

/**
 * Created by bhupesh on 4/5/17.
 */

public class Dept {

    String type,description;

    public Dept(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Dept() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.sportify.application.data.entity.User;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public abstract class GenericUser extends AbstractEntity {
   protected String name;
   protected String email;
   @NotNull
   @Column(name = "\"password\"")
   protected String password;

    public GenericUser() {}
    public GenericUser(String name_,
                    String email_,
                    String pass) {
        this.name = name_;
        this.email = email_;
        this.password = pass;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkPass(String pass) {
        return this.password.equals(pass);
    }

}

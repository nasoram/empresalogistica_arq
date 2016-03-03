/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleLogin;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Nelson
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    
    private String user;
    private String password;
    private String message = "";

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Boolean notMenssage(){
        return !this.message.equals("");
    }
    
    public String loginUser(){   
        HandleLogin login = new HandleLogin();
        message = login.validateLogin(user, password);
        if (!message.equals("")) return "index";
        else return "envio";
    }
    
}

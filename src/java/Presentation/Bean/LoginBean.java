/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleLogin;
import DataAccess.Entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
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
    
//    public String loginUser(){   
//        HandleLogin login = new HandleLogin();
//        message = login.validateLogin(user, password);
//        if (!message.equals("")) return "index";
//        else{
//            httpServletRequest.getSession().setAttribute("sesionUsuario", user);
//            return "envio";
//        }
//    }
    public String loginUser(){   
        HandleLogin login = new HandleLogin();
        Usuario usuario;
        usuario = login.validateLogin(user, password);
        if (usuario == null) {
            message = "Datos incorrectos.";
            return "index";
        }
        else{
            httpServletRequest.getSession().setAttribute("IdUsuario", usuario.getId());
            httpServletRequest.getSession().setAttribute("IdNombre", usuario.getNombre());
            httpServletRequest.getSession().setAttribute("IdRol", usuario.getRol());
            message = "Sesión iniciada!";
            return "envio";
        }
    }
    
    public boolean isCliente(){
        String rol = httpServletRequest.getSession().getAttribute("IdRol").toString();
        return rol.equals("Cliente");
    }
    public boolean isOperador(){
        String rol = httpServletRequest.getSession().getAttribute("IdRol").toString();
        return rol.equals("Operador");
    }
    public boolean isAdministrador(){
        String rol = httpServletRequest.getSession().getAttribute("IdRol").toString();
        return rol.equals("Administrador");
    }
    
    public boolean isLogout(){
        return (httpServletRequest.getSession().getAttribute("IdRol").toString() == null);
         
    }
    
    public String logout(){
        httpServletRequest.getSession().removeAttribute("IdUsuario");
        httpServletRequest.getSession().removeAttribute("IdNombre");
        httpServletRequest.getSession().removeAttribute("IdRol");
        return "envio";
    }
}

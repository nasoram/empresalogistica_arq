/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import DataAccess.Entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nelson
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {
    
    private int id;
    private String user;
    private String password;
    private String password2;
    private String message = "";
    private String nombre;
    private String documento;
    private String rol = "Cliente";
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String createUser(){   
        HandleUser usuario = new HandleUser();
        if (password.equals(password2)){
            message = usuario.createUsuario(nombre, documento, rol, password, user);
            if (message.equals("")){ 
                return "registro";
            }else{ 
                //httpServletRequest.getSession().setAttribute("IdUsuario", usuario.getId());
                httpServletRequest.getSession().setAttribute("IdNombre", nombre);
                httpServletRequest.getSession().setAttribute("IdRol", rol);
                return "envio";
            }
        } else {
            message = "Las contraseñas no coinciden";
            return "registro";//Contraseñas no coinciden
        }
        
    }
    
    public String verUsuario() {
        HandleUser usuarioH = new HandleUser();
        Usuario usuario = usuarioH.verUsuario(id);
        
        this.nombre = usuario.getNombre();
        this.user = usuario.getUsuario();
        this.documento = usuario.getDocumento();
        this.password = usuario.getPassword();
        this.rol = usuario.getRol();
        
        return "" + id;
    }
    
    public Boolean noMessage(){
        return !this.message.equals("");
    }
    
}

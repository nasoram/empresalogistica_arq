/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleOperador;
import DataAccess.Entity.Usuario;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author johngonzalez
 */
@Named(value = "operadoresBean")
@RequestScoped
public class OperadoresBean {

    private String user;
    private String password;
    private String message = "";
    private String nombre;
    private String documento;
    private String rol = "Operador";
    private List<Usuario> operadores;
    private String password2;
    private int id;
    private int dato;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    
    
    /**
     * Creates a new instance of OperadoresBean
     */
    public OperadoresBean() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("IdUsuario") != null) {
            this.id = (int) httpServletRequest.getSession().getAttribute("IdUsuario");
        }
        HandleOperador operador = new HandleOperador();
        operadores = operador.listaOperadores(rol);
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

    public List<Usuario> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<Usuario> operadores) {
        this.operadores = operadores;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void listaOperadores() {
        HandleOperador operador = new HandleOperador();
        operadores = operador.listaOperadores(rol);
    }
    
    public String crearOperador() {
        HandleOperador operador = new HandleOperador();
        if (password.equals(password2)){
            message = operador.crearOperador(nombre, documento, rol, password, user);
            if (message.equals("")){
                listaOperadores();
                return "lista";
            }
            else return "crear";
        } else {
            message = "Las contraseñas no coinciden";
            return "crear";//Contraseñas no coinciden
        }
    }
    
    public String verOperador() {
        Integer id;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest=(HttpServletRequest)facesContext.getExternalContext().getRequest();
        
        
        if(httpServletRequest.getSession().getAttribute("idEdit")==null){
            ExternalContext externalContext = facesContext.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            id = new Integer((String)params.get("id"));
            
            httpServletRequest.getSession().setAttribute("idEdit", id);
        }else{
            id = new Integer(httpServletRequest.getSession().getAttribute("idEdit").toString());
            httpServletRequest.getSession().removeAttribute("idEdit");
        }
        
        HandleOperador operador = new HandleOperador();
        
        Usuario usuarioOp = operador.verOperador(id);
        
        this.nombre = usuarioOp.getNombre();
        this.user = usuarioOp.getUsuario();
        this.documento = usuarioOp.getDocumento();
        this.password = usuarioOp.getPassword();
        this.id = id;
        
        return ""+id;
    }
    
    public String editarOperador() {
        HandleOperador operador = new HandleOperador();
        message = operador.editarOperador(id, nombre, documento, password, user);
        if (message.equals("")) {
            return "lista";
        } else {
            return "editar";
        }
    }
    
    public String eliminarOperador() {
        Integer id;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        id = new Integer((String)params.get("id"));
        
        HandleOperador operador = new HandleOperador();
        message = operador.eliminarOperador(id);
        if (message.equals("")) {
            return "";
        } else {
            return "";
        }
    }
    
    public Boolean noMessage() {
        return !this.message.equals("");
    }
    
    public String cerrarSesion() {
        httpServletRequest.getSession().removeAttribute("IdUsuario");
        return "index";
    }

}
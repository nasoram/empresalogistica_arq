/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleServicio;
import DataAccess.Entity.Servicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Named(value = "servicioBean")
@RequestScoped
public class ServicioBean {

    private String nombre;
    private String fecha;
    private String descripcion;
    private int id;
    private String message = "";
    private List<Servicio> servicios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    /**
     * Creates a new instance of OperadoresBean
     */
    public ServicioBean() {
        HandleServicio servicio = new HandleServicio();
        servicios = servicio.listaServicios();
    }
    
    public void listaServicios() {
        HandleServicio servicio = new HandleServicio();
        servicios = servicio.listaServicios();
    }
    
    public String crearServicio() {
        try {
            HandleServicio servicio = new HandleServicio();
            Date fechaF = new SimpleDateFormat("MMMM dd, yyyy").parse(fecha);
            message = servicio.crearServicio(nombre, fechaF, descripcion);
            if (message.equals("")){
                listaServicios();
                return "lista";
            }
            else {
//            message = "";
                return "crear";
            }
        } catch (ParseException ex) {
            return "crear";
        }
    }
    
    public String cargarServicio() {
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
        HandleServicio servicioH = new HandleServicio();
        Servicio servicio = servicioH.verServicio(id);
        this.nombre = servicio.getNombre();
        this.fecha = servicio.getFecha().toString();
        this.id = id;
        this.descripcion = servicio.getDescripcion();
        
        return "" + id;
    }
    
    public String verServicio() {
        Integer id;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        id = new Integer((String)params.get("id"));
        
        HandleServicio servicioH = new HandleServicio();
        Servicio servicio = servicioH.verServicio(id);
        
        this.nombre = servicio.getNombre();
        this.fecha = servicio.getFecha().toString();
        this.id = id;
        this.descripcion = servicio.getDescripcion();
        
        return "" + id;
    }
    
    public String editarServicio() {
        
        HandleServicio servicio = new HandleServicio();
        message = servicio.editarServicio(id, nombre, fecha, descripcion);
        if (message.equals("")) {
            return "lista";
        } else {
            return "editar";
        }
    }
    
    public String eliminarServicio() {
        Integer id;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        id = new Integer((String)params.get("id"));
        
        HandleServicio servicio = new HandleServicio();
        message = servicio.eliminarServicio(id);
        if (message.equals("")) {
            return "";
        } else {
            return "";
        }
    }
    
    public Boolean noMessage() {
        return !this.message.equals("");
    }

}
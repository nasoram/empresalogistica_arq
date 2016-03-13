/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleServicio;
import BusinessLogic.Controller.HandleSolicitud;
import BusinessLogic.Controller.HandleUser;
import DataAccess.Entity.Servicio;
import DataAccess.Entity.Solicitud;
import DataAccess.Entity.Usuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nelson
 */
@Named(value = "solicitudesBean")
@RequestScoped
public class SolicitudesBean {
    
    private int id;
    private int idServicio;
    private int idCliente;
    private Servicio servicio;
    private Usuario cliente;
    private String message = "";
    private List<Solicitud> solicitudes;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;

    /**
     * Creates a new instance of SolicitudesBean
     */
    public SolicitudesBean() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("IdUsuario") != null) {
            this.idCliente = (int) httpServletRequest.getSession().getAttribute("IdUsuario");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public String crearSolicitud(int id){
            this.idServicio = id;
            HandleSolicitud solicitud = new HandleSolicitud();
            HandleServicio servicioH = new HandleServicio();
            HandleUser clienteH = new HandleUser();
            this.servicio = servicioH.verServicio(idServicio);
            this.cliente = clienteH.verUsuario(idCliente);
            
            Date timeStamp = Calendar.getInstance().getTime();
            
            message = solicitud.crearSolicitud(cliente, servicio, timeStamp);

        return message.equals("") ? "solicitado" : "solicitud";
        }
    
    public List<Solicitud> listaSolicitud() {
        HandleSolicitud solicitud = new HandleSolicitud();
        return solicitud.listaSolicitudes();
    }
    
    public void listaSolicitudes() {
        HandleSolicitud solicitud = new HandleSolicitud();
        solicitudes = solicitud.listaSolicitudes();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.SolicitudDAO;
import DataAccess.Entity.Servicio;
import DataAccess.Entity.Solicitud;
import DataAccess.Entity.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class HandleSolicitud {
    
    public List<Solicitud> listaSolicitudes() {
        SolicitudDAO solicitud = new SolicitudDAO();
        List solicitudes = null;
        
        solicitudes = solicitud.listaSolicitudes();
        return solicitudes;
    }
    
    public List<Solicitud> listaSolicitudesSinAtender() {
        SolicitudDAO solicitud = new SolicitudDAO();
        List solicitudes = null;
        
        solicitudes = solicitud.listaSolicitudesSinAtender();
        return solicitudes;
    }
    
    public String crearSolicitud(Usuario usuario, Servicio servicio, Date fecha_recepcion) {
        
        Solicitud solicitud = new Solicitud();
        
        solicitud.setIdUsuario(usuario);
        solicitud.setIdServicio(servicio);
        solicitud.setFechaRecepcion(fecha_recepcion);

        SolicitudDAO solicitudDAO = new SolicitudDAO();
        Solicitud solicitudE = solicitudDAO.persist(solicitud);
        return (solicitudE != null) ? "" : "Solicitud no pudo ser creada!";
    }
    
    public String atenderSolicitud(Usuario operador, Solicitud solicitud, Date fecha_envio) {
        SolicitudDAO solicitudDAO = new SolicitudDAO();
        
        try {
            solicitud.setFechaEnvio(fecha_envio);
            solicitud.setIdOperador(operador.getId());
            
            Solicitud solicitudE = solicitudDAO.persist(solicitud);
            return (solicitudE != null) ? "" : "Solicitud no pudo ser atendida!";
        } catch (Exception e) {
            return "Solicitud no fue atendida!";
        } 
    }
    
    public Solicitud verSolicitud(int id) {
        SolicitudDAO solicitudDAO = new SolicitudDAO();
        Solicitud solicitud = null;
        
        try {
            solicitud = solicitudDAO.searchById(id);
        } catch (Exception e) {
        } finally {
            return solicitud;
        }
    }
    
}

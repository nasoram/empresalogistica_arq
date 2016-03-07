/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ServicioDAO;
import DataAccess.Entity.Servicio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author johngonzalez
 */
public class HandleServicio {

    public HandleServicio() {
    }
    
    public List<Servicio> listaServicios() {
        ServicioDAO servicio = new ServicioDAO();
        List servicios = null;
        
        servicios = servicio.listaServicios();
        return servicios;
    }
    
    public String crearServicio(String nombre, Date fecha) {
        
        Servicio servicio = new Servicio();
        
        servicio.setNombre(nombre);
        servicio.setFecha(fecha);

        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicioE = servicioDAO.persist(servicio);
        if (servicioE != null) {
            return "";
        } else {
            return "Servicio no pudo ser creado!";
        }  
    }
    
    public Servicio verServicio(int id) {
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = null;
        
        try {
            servicio = servicioDAO.searchById(id);
        } catch (Exception e) {
        } finally {
            return servicio;
        }
    }
    
    public String editarServicio(int id, String nombre, String fecha) {
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = null;
        
        try {
            servicio = servicioDAO.searchById(id);
            servicio.setNombre(nombre);
            Date fechaF = new SimpleDateFormat("MMMM dd, yyyy").parse(fecha);
            servicio.setFecha(fechaF);
            
            Servicio servicioE = servicioDAO.persist(servicio);
            if (servicioE != null) {
                return "";
            } else {
                return "Servicio no pudo ser editado!";
            }
        } catch (Exception e) {
            return "Servicio no fue encontrado!";
        } 
    }
    
    public String eliminarServicio(int id) {
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = null;
        
        try {
            servicio = servicioDAO.searchById(id);
            if (servicio != null) servicioDAO.remove(id);
            return "";
        } catch (Exception e) {
            return "Servicio no pudo ser eliminado!";
        }
    }
}

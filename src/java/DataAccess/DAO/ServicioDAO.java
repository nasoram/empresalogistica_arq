/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Servicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nelson
 */
public class ServicioDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpresaLogisticaPU");
    
    public Servicio persist(Servicio servicio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(servicio);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return servicio;  
        }
    }
    
    public Servicio searchByNombre(String name) {
        
        EntityManager em = emf.createEntityManager();
        Servicio servicio = null;
        Query q = em.createNamedQuery("Servicio.findByNombre");
        q.setParameter(1, name);
        
        try {
            servicio = (Servicio) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return servicio;
        }
    }
    
    public List<Servicio> listaServicios() {
        EntityManager em = emf.createEntityManager();
        List<Servicio> servicios = null;
        Query q = em.createNamedQuery("Servicio.findAll");
        
        try {
            servicios = q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
            return servicios;            
        }
    }
    
    public Servicio searchById(int id) {
        EntityManager em = emf.createEntityManager();
        Servicio servicio = null;
        Query q = em.createNamedQuery("Servicio.findById");
        q.setParameter(1, id);
        
        try {
            servicio = (Servicio) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return servicio;
        }
    }
}

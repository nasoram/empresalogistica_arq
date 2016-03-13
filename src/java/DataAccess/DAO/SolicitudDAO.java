/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nelson
 */
public class SolicitudDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpresaLogisticaPU");
    
    public Solicitud persist(Solicitud solicitud) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(solicitud);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return solicitud;  
        }
    }
    
    public List<Solicitud> listaSolicitudes() {
        EntityManager em = emf.createEntityManager();
        List<Solicitud> solicitudes = null;
        Query q = em.createNamedQuery("Solicitud.findAll");
        
        try {
            solicitudes = q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
            return solicitudes;            
        }
    }
    
    public Solicitud searchById(int id) {
        EntityManager em = emf.createEntityManager();
        Solicitud solicitud = null;
        Query q = em.createNamedQuery("Solicitud.findById");
        q.setParameter(1, id);
        
        try {
            solicitud = (Solicitud) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return solicitud;
        }
    }
    
    public void remove(int id) {
        EntityManager em = emf.createEntityManager();
        Solicitud solicitud = em.find(Solicitud.class, id);
        em.getTransaction().begin();
        
        try {
            em.remove(solicitud);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}

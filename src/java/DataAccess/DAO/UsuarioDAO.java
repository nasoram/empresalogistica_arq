/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nelson
 */
public class UsuarioDAO {
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpresaLogisticaPU");
    
    public Usuario persist(Usuario usuario) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(usuario);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return usuario;  
        }
    }
    
    public Usuario searchByUsuario(String user) {
        
        EntityManager em = emf.createEntityManager();
        Usuario usuario = null;
        Query q = em.createNamedQuery("Usuario.findByUsuario");
        q.setParameter(1, user);
        
        try {
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return usuario;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import java.util.List;
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
            em.merge(usuario);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return usuario;  
        }
    }
    
    public void remove(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Presentation.Bean.OperadoresBean.eliminarOperador.handler.removeeeeee()");
        em.getTransaction().begin();
        System.out.println("Presentation.Bean.OperadoresBean.eliminarOperador.handler.removeeeeee22222222222222()");
        System.out.println(usuario.getId());

        try {
            em.remove(usuario);
            System.out.println("Presentation.Bean.OperadoresBean.eliminarOperador.handler.removeeeeee333333333333()");
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
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
    
    public List<Usuario> listaOperadores(String rol) {
        EntityManager em = emf.createEntityManager();
        List<Usuario> operadores = null;
        Query q = em.createNamedQuery("Usuario.findByRol");
        q.setParameter(1, rol);
        
        try {
            operadores = q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
            return operadores;            
        }
    }
    
    public Usuario searchById(int id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = null;
        Query q = em.createNamedQuery("Usuario.findById");
        q.setParameter(1, id);
        
        try {
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return usuario;
        }
    }
}

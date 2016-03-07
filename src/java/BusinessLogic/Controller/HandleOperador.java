/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.Entity.Usuario;
import java.util.List;

/**
 *
 * @author johngonzalez
 */
public class HandleOperador {

    public HandleOperador() {
    }
    
    public List<Usuario> listaOperadores(String rol) {
        UsuarioDAO usuario = new UsuarioDAO();
        List operadores = null;
        
        operadores = usuario.listaOperadores(rol);
        return operadores;
    }
    
    public String crearOperador(String nombre, String documento, String rol, String password, String usuario) {
        
        Usuario user = new Usuario();
        
        user.setNombre(nombre);
        user.setDocumento(documento);
        user.setRol(rol);
        user.setPassword(password);
        user.setUsuario(usuario);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioE = usuarioDAO.persist(user);
        if (usuarioE != null) {
            return "";
        } else {
            return "Operador logístico no pudo ser creado!";
        }  
    }
    
    public Usuario verOperador(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        
        try {
            usuario = usuarioDAO.searchById(id);
        } catch (Exception e) {
        } finally {
            return usuario;
        }
    }
    
    public String editarOperador(int id, String nombre, String documento, String password, String user) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        
        try {
            usuario = usuarioDAO.searchById(id);
            
            usuario.setNombre(nombre);
            usuario.setDocumento(documento);
            usuario.setPassword(password);
            usuario.setUsuario(user);

            Usuario usuarioE = usuarioDAO.persist(usuario);
            if (usuarioE != null) {
                return "";
            } else {
                return "Operador logístico no pudo ser editado!";
            }
        } catch (Exception e) {
            return "Operador logístico no fue encontrado!";
        } 
    }
    
    public String eliminarOperador(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        
        System.out.println("Presentation.Bean.OperadoresBean.eliminarOperador.handler()");
        
        try {
            usuario = usuarioDAO.searchById(id);
            usuarioDAO.remove(usuario);
            System.out.println("Presentation.Bean.OperadoresBean.eliminarOperador.handler.try()");
            return "";
        } catch (Exception e) {
            return "Operador logístico no pudo ser eliminado!";
        }
    }
}

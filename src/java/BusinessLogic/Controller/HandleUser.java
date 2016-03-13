/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.Entity.Usuario;
import DataAccess.Entity.Usuario;

/**
 *
 * @author Nelson
 */
public class HandleUser {
    
    public String createUsuario(String nombre, String documento, String rol, String password, String usuario){
        
        Usuario user = new Usuario();
        
        user.setNombre(nombre);
        user.setDocumento(documento);
        user.setRol(rol);
        user.setPassword(password);
        user.setUsuario(usuario);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioE = usuarioDAO.persist(user);
        if (usuarioE != null) {
            return "Su cuenta ha sido creada.";
        } else {
            return "";
        }        
    }

    public Usuario verUsuario(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        
        try {
            usuario = usuarioDAO.searchById(id);
        } catch (Exception e) {
        } finally {
            return usuario;
        }
    }
    
    
}

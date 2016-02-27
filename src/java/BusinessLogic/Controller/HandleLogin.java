/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.Entity.Usuario;

/**
 *
 * @author Nelson
 */
public class HandleLogin {

    public HandleLogin() {
    }
    
    public String createUsuario(Integer id, String nombre, String documento, String rol, String password, String usuario){
        
        Usuario user = new Usuario();
        
        user.setId(id);
        user.setNombre(nombre);
        user.setDocumento(documento);
        user.setRol(rol);
        user.setPassword(password);
        user.setUsuario(usuario);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioE = usuarioDAO.persist(user);
        if (usuarioE != null) {
            return "la cuenta ha sido creada, su cuenta de usuario es " + user.getUsuario() + ".";
        } else {
            return "la cuenta no pudo ser creada.";
        }        
    }
    
    public String validateLogin(String usuario, String password){
        String result = "";
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioR = usuarioDAO.searchByUsuario(usuario);
        if (usuarioR != null) {
            if (usuarioR.getPassword().equals(password))
                result = "Su cuenta de usuario es: " + usuarioR.getUsuario() + "\ny su contraseña: " + usuarioR.getPassword();
            else result = "La contraseña no es correcta.";
        } else {
            result = "la cuenta no existe.";
        } 
        
        return result;
    }
    
}

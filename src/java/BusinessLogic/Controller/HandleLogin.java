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
    
//    public String validateLogin(String usuario, String password){
//        String result = "";
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        Usuario usuarioR = usuarioDAO.searchByUsuario(usuario);
//        if (usuarioR != null) {
//            if (usuarioR.getPassword().equals(password))
//                result = "";//"Su cuenta de usuario es: " + usuarioR.getUsuario() + "\ny su contraseña: " + usuarioR.getPassword();
//            else result = "La contraseña es incorrecta.";
//        } else {
//            result = "La cuenta no existe.";
//        } 
//        
//        return result;
//    }
    
    public Usuario validateLogin(String usuario, String password){
        String result = "";
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioR = usuarioDAO.searchByUsuario(usuario);
        if (usuarioR != null) {
            return usuarioR.getPassword().equals(password) ? usuarioR : null;
        } else {
            return null;
        } 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

/**
 *
 * @author johngonzalez
 */
public class HandleLogin {
    public String  verifyCredential(String user, String password){
        if (user.equals("usuario") && password.equals("pass")) {
            return "Usted está logueado!";
        } else {
            return "Usuario o contraseña incorrecto.";
        }
    }
}

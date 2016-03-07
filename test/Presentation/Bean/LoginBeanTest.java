/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.Entity.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nelson
 */
public class LoginBeanTest {
    
    Usuario usuario = new Usuario();
    
    public LoginBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
//        usuario.setDocumento("N/A");
//        usuario.setId(1);
//        usuario.setNombre("Fulanito");
//        usuario.setRol("NINGUNO");
        usuario.setUsuario("melissa");
        usuario.setPassword("1234");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginUser method, of class LoginBean.
     */
    @Test
    public void testLoginUser() {
        System.out.println("LoginUser Test");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        Usuario usuarioTest = usuarioDAO.searchByNombre(usuario.getUsuario());
//        assertEquals(usuario.getUsuario(), usuarioTest.getUsuario());
    }
    
}
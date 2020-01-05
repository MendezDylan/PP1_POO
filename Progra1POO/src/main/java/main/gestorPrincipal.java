/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.ControladorLogin;
import modelo.Usuario;
import vista.VentanaLogin;
import javax.mail.MessagingException;
import api.Mail;
import api.Sms;

/**
 *
 * @author Fabian Navarro
 */
public class gestorPrincipal {

    public static void main(String[] args) throws MessagingException {
        //Mail.enviarCorreo("Mensaje de prueba", "dylan97cr@gmail.com");
        //Sms.enviarMensaje("Mensaje de prueba", "85211357");
        
        Usuario modelo = new Usuario();
        VentanaLogin vista = new VentanaLogin();
        //AgregarSalaForm vista2 = new AgregarSalaForm();
        //Sala modelo2 = new Sala();

        ControladorLogin controladorUsuario = new ControladorLogin(vista, modelo);

        //ControladorSalaDAO controladorSala = new ControladorSalaDAO(vista2, modelo2);
        controladorUsuario.vista.setVisible(true);
        controladorUsuario.vista.setLocationRelativeTo(null);

        //controladorSala.vista.setVisible(true);
        //controladorSala.vista.setLocationRelativeTo(null);
    }
}

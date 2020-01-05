/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Dylan Mendez
 */
public class Mail {
  public static void enviarCorreo(String pMensaje, String pCorreoReceptor) throws MessagingException{
    try {
      // TODO add your handling code here:
      Properties props = new Properties();
      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      props.setProperty("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.starttls.enable", "true");
      props.setProperty("mail.smtp.port", "587");
      props.setProperty("mail.smtp.auth", "true");
      
      Session session = Session.getDefaultInstance(props);
      
      String correoRemitente = "dylanmentec@gmail.com";
      String contrasena = "Tec-1234";
      String correoReceptor = pCorreoReceptor;
      String asunto = "SalasTEC";
      String mensaje = pMensaje;
      
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(correoRemitente));
      
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
      message.setSubject(asunto);
      message.setText(mensaje);
      
      Transport t = session.getTransport("smtp");
      
      t.connect(correoRemitente,contrasena);
      t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
      t.close();
      
      JOptionPane.showMessageDialog(null, "Correo enviado");
      
    } catch (AddressException ex) {
      Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(null, "Correo NO enviado");
    } catch (MessagingException ex) {
      Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(null, "Correo NO enviado");
    }
  }
}

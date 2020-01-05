/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Dylan Mendez
 */
public class Sms {

  public static final String ACCOUNT_SID = "AC309f60cd13b078bd0aac090c6a25c5f0";
  public static final String AUTH_TOKEN = "236f2ad1ed9ddfbecba6d97c7a576fcb";  

  public static void enviarMensaje(String mensaje, String numero){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+506"+numero),
        new PhoneNumber("+12055510838"), mensaje).create();

    System.out.println(message.getSid());
  }
}

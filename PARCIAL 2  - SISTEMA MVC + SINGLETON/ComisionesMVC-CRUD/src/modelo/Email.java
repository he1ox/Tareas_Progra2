package modelo;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




/**
 *
 * @author georg
 */
public class Email {


    public static void enviarMail(Usuario user,String pass) {

        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(props);
            String correoRemitente = "sistemadeventasgt@gmail.com";
            String passwordRemitente = "prueba123";
            
            String correoReceptor = user.getCorreo();
            String asunto = "REGISTRO ÉXITOSO.";
            String mensaje = "Hola "+user.getNombre()+", gracias por registrarte! "
                    + "\n\nTus credenciales son: "
                    + "\nNombre de usuario: " +user.getUsuario()
                    + "\nContraseña: " + pass
                    + "\n\nCodificado por @he1ox - Jorge López"
                    + "\nGitHub: https://github.com/he1ox";
            
            
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));
            
            
            //Receptor normal
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje);
            
            try ( //Lo enviamos
                Transport t = session.getTransport("smtp")) {
                t.connect(correoRemitente,passwordRemitente);
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                System.out.println("Correo electronico enviado al : " + user.getCorreo());
            }
            
        } catch (AddressException ex) {
            ex.printStackTrace(System.out);
        } catch (MessagingException ex) {
            ex.printStackTrace(System.out);
        }
    }

}


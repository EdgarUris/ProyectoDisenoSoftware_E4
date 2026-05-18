/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructuras;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author EdgarUris
 * @author JeniferFl
 */
public class SistemaCorreoElectronico implements ISistemaCorreoElectronico{
    
    private String correoDe = "dentistadental255@gmail.com";
    private String contra = "aboj kwkc xoou aizq";
    
    private Properties propiedades;
    private Authenticator auth;

    public SistemaCorreoElectronico(){
        propiedades = new Properties();
        propiedades.put("mail.smtp.host","smtp.gmail.com");
        propiedades.put("mail.smtp.ssl.trust","smtp.gmail.com");
        
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.user", correoDe);
        propiedades.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        propiedades.setProperty("mail.smtp.auth", "true");
        
        auth = new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(correoDe, contra);
            }
        };
    }
    
    /**
     * 
     * @param correoPara el correo al que llegará el mensaje
     * @param mot el motivo del mensaje 
     * @param cont el contenido del mensaje
     */
    @Override
    public void enviarCorreo(String correoPara, String mot, String cont){
        correoPara = correoPara.trim();
        
        Session mSession = Session.getInstance(propiedades, auth);
        Message mensaje = new MimeMessage(mSession);
        
        try{
            mensaje.setFrom(new InternetAddress(correoDe));
            InternetAddress[] direccionPara = 
                {new InternetAddress(correoPara)};
            
            mensaje.setRecipients(Message.RecipientType.TO, direccionPara);
            mensaje.setSubject(mot);
            mensaje.setSentDate(new Date());
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(cont, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mensaje.setContent(multipart);
            
            Transport.send(mensaje);
            System.out.println("Mensaje enviado");
            
        }catch(MessagingException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    /**
     * 
     * @param correoPara el correo al que llegará el mensaje
     * @param mot el motivo del mensaje
     * @param cont el contenido del mensaje
     * @param archivos los archivos a adjuntar en el mensaje
     */
    @Override
    public void enviarCorreoConArchivos(String correoPara, String mot, String cont, File[] archivos){
        correoPara = correoPara.trim();
        
        Session mSession = Session.getInstance(propiedades, auth);
        Message mensaje = new MimeMessage(mSession);
        
        try{
            mensaje.setFrom(new InternetAddress(correoDe));
            InternetAddress[] direccionPara = 
                {new InternetAddress(correoPara)};
            
            mensaje.setRecipients(Message.RecipientType.TO, direccionPara);
            mensaje.setSubject(mot);
            mensaje.setSentDate(new Date());
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(cont, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            MimeBodyPart archivosAdjuntos;
            for (File archivo : archivos) {
                archivosAdjuntos = new MimeBodyPart();
                archivosAdjuntos.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                archivosAdjuntos.setFileName(archivo.getName());
                multipart.addBodyPart(archivosAdjuntos);
            }
            
            mensaje.setContent(multipart);
            Transport.send(mensaje);
            
            System.out.println("Mensaje enviado");
            
        }catch(MessagingException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}

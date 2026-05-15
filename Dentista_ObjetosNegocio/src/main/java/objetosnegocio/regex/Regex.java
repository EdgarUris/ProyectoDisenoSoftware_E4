/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EdgarUris
 */
public class Regex {
    
    public Regex(){
    
    }
    
    public boolean validarCorreo(String correo){
        Pattern patCorreo = Pattern.compile("^(?!.*\\.{2})[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher m = patCorreo.matcher(correo);
        return m.matches();
    }
    
    public boolean validarTelefono(String telefono){
        Pattern patTelefono = Pattern.compile("^(\\d){10}$");
        Matcher m = patTelefono.matcher(telefono);
        return m.matches();
    }
    
}

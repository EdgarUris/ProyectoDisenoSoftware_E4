/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exception;

/**
 *
 * @author EdgarUris
 */
public class EntityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>EntityNotFoundException</code> without detail
     * message.
     */
    public EntityNotFoundException() {
    
    }

    /**
     * Constructs an instance of <code>EntityNotFoundException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EntityNotFoundException(String msg) {
        super(msg);
    }
    
    /**
     * 
     * @param message el mensaje detallado
     * @param cause causa
     */
    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}

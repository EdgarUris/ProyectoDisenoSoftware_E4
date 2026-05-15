/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package objetosnegocio.Excepciones;

/**
 *
 * @author EdgarUris
 */
public class BOException extends Exception {

    /**
     * Creates a new instance of <code>ObjetoNegocioException</code> without
     * detail message.
     */
    public BOException() {
    }

    /**
     * Constructs an instance of <code>ObjetoNegocioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BOException(String msg) {
        super(msg);
    }
}

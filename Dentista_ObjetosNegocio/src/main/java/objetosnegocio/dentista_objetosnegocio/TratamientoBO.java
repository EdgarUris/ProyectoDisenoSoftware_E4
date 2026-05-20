/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import DAOs.ITratamientoDAO;
import DAOs.TratamientoDAO;
import Exception.DAOException;
import entidades.Tratamiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import objetosnegocio.Excepciones.BOException;


public class TratamientoBO implements ITratamientoBO {
    
    private ITratamientoDAO tDAO;
    
    public TratamientoBO(){
        tDAO = new TratamientoDAO();
    }

    @Override
    public List<Tratamiento> listarTodos() throws BOException {
        try{
            List<Tratamiento> obtenerTodos = tDAO.findAll(100);
            if(obtenerTodos.size() == 0){
                throw new BOException("No existen tratamientos");
            }
            return obtenerTodos;
        }catch(Exception e){
            System.out.println("Error al obtener los tratamientos");
            return new ArrayList<>();
        }
    }

    @Override
    public Tratamiento buscarPorNombre(String nombre) throws BOException {
        try {
            Optional<Tratamiento> t = tDAO.findByNombre(nombre);
            if(!t.isPresent()){
                throw new BOException("Tratamiento no encontrado");
            }
            return t.get();
        } catch (DAOException ex) {
            System.getLogger(TratamientoBO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
    }

    @Override
    public boolean agregar(String nombre, Double costo) throws BOException {
        try {
            if(nombre.trim().isEmpty()){
                throw new BOException("El nombre del tratamiento no puede estar vacio");
            }
            if(costo < 0){
                throw new BOException("El costo de un tratamiento no puede ser negativo");
            }
            Tratamiento t = new Tratamiento();
            t.setCosto(costo);
            t.setNombre(nombre);
            return tDAO.create(t);
        } catch (DAOException ex) {
            System.getLogger(TratamientoBO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import DAOs.IDentistaDAO;
import Exception.DAOException;
import entidades.Dentista;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import objetosnegocio.Excepciones.BOException;


public class DentistaService implements IDentistaService {
    
    private final IDentistaDAO dDAO;
    
    public DentistaService(IDentistaDAO dao){
        this.dDAO = dao;
    }
    
    @Override
    public boolean registrar(String nombre, String especialidad) throws BOException {
        if(nombre.trim().isEmpty()){
            throw new BOException("El nombre del medico esta vacio");
        }
        if(especialidad.trim().isEmpty()){
            throw new BOException("La especialidad esta vacia");
        }
        try {
            return dDAO.create(new Dentista(nombre, especialidad));
        } catch (DAOException ex) {
            return false;
        }
    }

    @Override
    public boolean editar(String folio, String nombre, String especialidad) throws BOException {
        try {
            Optional<Dentista> d = dDAO.findByFolio(folio);
            if(d.get() != null){
                throw new BOException("Dentista no encontrado con el folio: "+folio);
            }
            if(nombre.trim().isEmpty()){
                throw new BOException("El nombre del dentista está vacio");
            }
            if(especialidad.trim().isEmpty()){
                throw new BOException("La especialidad esta vacia");
            }
            
            Dentista cambiado = d.get();
            cambiado.setNombre(nombre);
            cambiado.setEspecialidad(especialidad);
            
            return dDAO.update(cambiado);
            
        } catch (DAOException ex) {
            System.getLogger(DentistaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(String folio) throws BOException {
        try {
            Optional<Dentista> p = dDAO.findByFolio(folio);
            if(p.get() == null){
                throw new BOException("No se pudo encontrar el dentista");
            }
            Dentista eliminado = p.get();
            return dDAO.deleteById(eliminado.getId());
        } catch (DAOException ex) {
            return false;
        }
    }

    @Override
    public List<Dentista> listar(int limite) throws BOException {
        try {
            return dDAO.findAll(limite);
        } catch (DAOException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Dentista> listarPorEspecialidad(String especialidad) throws BOException {
        try {
            return dDAO.findByEspecialidad(especialidad);
        } catch (DAOException ex) {
            return new ArrayList<>();
        }
    }
    
    
    
}

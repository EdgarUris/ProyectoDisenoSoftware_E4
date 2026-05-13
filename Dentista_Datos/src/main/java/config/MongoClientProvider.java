/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author EdgarUris
 */
public enum MongoClientProvider {
    
    INSTANCE;

    private MongoClient cliente;
    private String nombreDB = "DentistaDB";
    private String url = "mongodb://localhost:27017/";

    /*
     * inicializa el cliente de mongo
     * syncronized para evitar que se inicialice mas de 1 cliente
     */
    public synchronized void init() {
        if (cliente == null) { //solo se crea si no existe
            cliente = MongoClients.create(MongoConfig.buildSettings(this.url));
            //cerrar la conexion si se apaga
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try { 
                    cliente.close(); 
                } catch (Exception ignored) {}
            }));
        }
    }

    /**
     * @return cliente de mongo inicializado
     */
    public MongoClient getClient() {
        if (cliente == null)
            throw new IllegalStateException("MongoClientProvider no inicializado. Llama a init() antes.");
        return cliente;
    }

    /**
     * @return la base de datos configurada
     */
    public MongoDatabase database() {
        return getClient().getDatabase(this.nombreDB);
    }

    /**
     * @param collectionName Nombre de la colección
     * @param clase tipo de documento u objeto
     * @return la coleccion de mongo del objeto especificado
     */
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clase) {
        if (cliente == null){
            throw new IllegalStateException("MongoClientProvider no inicializado. Llama a init() antes.");
        }
        MongoDatabase db = cliente.getDatabase(this.nombreDB);
        return db.getCollection(collectionName, clase);
    }
}

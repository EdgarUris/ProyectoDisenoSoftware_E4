/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author EdgarUris
 */
public class MongoConfig {
    
    private MongoConfig(){
    
    }
    
    public static MongoClientSettings buildSettings(String uri) {
        // ============================================
        // Se crea el objeto con la cadena de conexión
        // ============================================
        ConnectionString connectionString = new ConnectionString(uri);

        // ============================================
        // Se define el "CodecRegistry"
        // ============================================
        /**
         * MongoDB necesita saber cómo convertir los objetos Java (POJOs) a
         * documentos BSON y viceversa. Para eso usamos un "CodecRegistry".
         *
         * fromRegistries() combina varios registros de codecs: - El
         * predeterminado de MongoDB. - Uno nuevo que permite manejar objetos
         * Java automáticamente.
         */
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // ============================================
        // Se construye el objeto de configuración final
        // ============================================
        /**
         * - applyConnectionString(): aplica los datos de conexión. -
         * codecRegistry(): asocia el registro que traduce entre POJOs y BSON. -
         * build(): crea el objeto final inmutable (MongoClientSettings).
         */
        return MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(pojoCodecRegistry)
            .build();
    }
    
}

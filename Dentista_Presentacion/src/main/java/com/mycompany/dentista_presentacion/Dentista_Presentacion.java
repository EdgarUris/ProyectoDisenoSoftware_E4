/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dentista_presentacion;

import config.MongoClientProvider;
import inicio.MainFrame;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author EdgarUris
 */
public class Dentista_Presentacion {

    public static void main(String[] args) throws BOException {
        MongoClientProvider.INSTANCE.init();
        MainFrame iniciar = new MainFrame();
        iniciar.setVisible(true);
    }
    
}

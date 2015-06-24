/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Gabriel
 */
public class SocketCliente {
    Socket cliente;
    PrintStream saida;
    public ObjectOutputStream outputStream;
    public final static int FILE_SIZE = 6022386;
    FileInputStream fIn;
    FileOutputStream fOut;
    BufferedOutputStream bos;
    BufferedInputStream bis;
    OutputStream os;
    
    public SocketCliente() throws IOException{
        //Cria conezão
        cliente = new Socket("127.0.0.1", 12345);
        System.out.println("O Cliente se conectou ao servidor!");        
        outputStream = new ObjectOutputStream(cliente.getOutputStream());

    }
    
    public void enviaMSG(Mensagem msg) throws UnknownHostException, IOException{
        if(msg.getTipo() == 1){
            outputStream.writeObject(new Mensagem(msg.getTipo(), msg.getConteudo()));
        }
        else if(msg.getTipo() == 2){
            File file = new File(msg.getFile().getPath());
            outputStream.writeObject(new Mensagem(msg.getTipo(), file));
            System.out.println("Enviado");
        }
    }
    
}

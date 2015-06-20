package com.model;

import com.view.JFrameChat;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel.calderaro
 */
public class Envia {


    private String host;
    private int porta;
    Socket cliente;
    PrintStream saida; 
   
   
    public Envia(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    
    public void conecta() throws UnknownHostException, IOException{
        cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor!");
    }
//   public void executaMSG(String menssagem) throws UnknownHostException, IOException {
//
//
//        // thread para receber mensagens do servidor
//        JFrameChat r = new JFrameChat(cliente.getInputStream());
//        new Thread(r).start();
//
//        // lê msgs do teclado e manda pro servidor
//        Scanner s = new Scanner(System.in);
//        PrintStream saida = new PrintStream(cliente.getOutputStream());
//        while (s.hasNextLine()) {
//          saida.println(s.nextLine());
//        }
//
//        saida.close();
//        s.close();
//        cliente.close();    
//   }
    
    public void EnviaMSG(String menssagem) throws UnknownHostException, IOException {


     // thread para receber mensagens do servidor
     JFrameChat r = new JFrameChat(cliente.getInputStream());
     new Thread(r).start();

     // lê msgs do teclado e manda pro servidor
    saida = new PrintStream(cliente.getOutputStream());
    saida.println(menssagem);
    

    saida.close();
    cliente.close();
}
    
//    public void close() throws IOException
//    {
//        saida.close();
//        cliente.close(); 
//    }
       
}

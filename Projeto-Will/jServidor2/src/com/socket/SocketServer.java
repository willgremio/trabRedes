/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import com.view.JFrameChatServidor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class SocketServer {

    private ServerSocket serverSocket;
    private Socket socket;
    public ObjectOutputStream outputStream;
    Socket serv;

    public SocketServer(JFrameChatServidor chat) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    serverSocket = new ServerSocket(12345);
                    System.out.println("Server On");

                    new Thread(new Monitoramento()).start();
                    while (true) {
                        socket = serverSocket.accept();
                        new Thread(new ListenerSocket(socket)).start();
                        //outputStream = new ObjectOutputStream(serv.getOutputStream());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void enviaMSG(Mensagem msg) throws UnknownHostException, IOException {
        if (msg.getTipo() == 1) {
            outputStream.writeObject(new Mensagem(msg.getTipo(), msg.getConteudo()));
        } else if (msg.getTipo() == 2) {
            File file = new File(msg.getFile().getPath());
            outputStream.writeObject(new Mensagem(msg.getTipo(), file));
            System.out.println("Enviado para o cliente");
        }
    }

    private class ListenerSocket implements Runnable {

        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;

        public ListenerSocket(Socket socket) throws IOException {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            Mensagem msg = null;
            try {
                while ((msg = (Mensagem) inputStream.readObject()) != null) {
                    //Ser for Mensagem que veio do servidor
                    if (msg.getTipo() == 1) {
                        System.out.println(msg.getConteudo());
                    } //Se for arquivo que veio do servidor
                    else if (msg.getTipo() == 2) {
                        System.out.println(salvar(msg));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*Salva arquivo em um diretório*/

        private String salvar(Mensagem msg) throws FileNotFoundException, IOException {
            FileInputStream fileInputStream = new FileInputStream(msg.getFile());
            FileOutputStream fileOutputStream = new FileOutputStream("C://Users//User//Desktop//Servidor//" + msg.getFile().getName());

            FileChannel fin = fileInputStream.getChannel();
            FileChannel fout = fileOutputStream.getChannel();

            long size = fin.size();

            fin.transferTo(0, size, fout);
            return "Arquivo salvo em 'C:/Users/GabrielDesktop/Servidor/'";
        }

    }

}

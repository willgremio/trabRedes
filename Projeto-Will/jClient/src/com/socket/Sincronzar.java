/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

/**
 *
 * @author User
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Sincronzar {

    Socket cliente;
    public ObjectOutputStream outputStream;  

    public void sincronizar() {
        File folder = new File("C:\\Users\\User\\Desktop\\Cliente");
        String[] srcFiles = listFilesForFolder(folder);
        File file = null;

        try {
            cliente = new Socket("127.0.0.1", 12345);
            outputStream = new ObjectOutputStream(cliente.getOutputStream());
            for (int i = 0; i < srcFiles.length; i++) {
                if (srcFiles[i] != null) {
                    file = new File("C:\\Users\\User\\Desktop\\Cliente\\" + srcFiles[i]);
                    outputStream.writeObject(new Mensagem(2, file));
                }
            }

            System.out.println("Enviado");            
        } catch (IOException ex) {
            Logger.getLogger(Sincronzar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        folder = null;
        file = null;
    }

    public String[] listFilesForFolder(final File folder) {
        int k = 0;
        String files[] = new String[50];
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files[k] = fileEntry.getName().toString();
                k++;

            }
        }

        return files;
    }
}

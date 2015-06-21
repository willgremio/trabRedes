/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizaarq;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author gabriel.calderaro
 */
public class SincronizaArq {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Diretorio diretorio = new Diretorio();
        ArrayList <String> arqFaltando = new ArrayList<>();
        ArrayList <String> arqServidor = new ArrayList<>();
        
        File file = new File("C:\\Users\\gabriel.calderaro\\Desktop\\Servidor");
        File listFile[] = file.listFiles();
        
        diretorio.preencheArrayARQ();
        
        for (int i = 0; i < listFile.length; i++) {
        File arquivos = listFile[i];
        arqServidor.add(arquivos.getName());
        }
        System.out.println("Arquivos Servidor: "+arqServidor);
        
        arqFaltando = diretorio.verificaDiretorios(arqServidor);
        System.out.println("Arquivos Faltando: "+arqFaltando);
    }
}

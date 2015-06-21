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
public class Diretorio {
  private String diretorio;
  private ArrayList <String> listaArq = new ArrayList<>();

    public Diretorio() {
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public ArrayList<String> getListaArq() {
        return listaArq;
    }

    public void setListaArq(ArrayList<String> listaArq) {
        this.listaArq = listaArq;
    }
  
  
    public void preencheArrayARQ(){
        File file = new File("C:\\Users\\gabriel.calderaro\\Desktop\\Cliente");
        File listFile[] = file.listFiles();
        for (int i = 0; i < listFile.length; i++) {
            File arquivos = listFile[i];
            listaArq.add(arquivos.getName());
        }
        System.out.println("Arquivos Cliente:  "+listaArq);
    }
    
    public ArrayList<String> verificaDiretorios(ArrayList<String> listaArq2){
        ArrayList<String> novoArray = new ArrayList<>();
        for(String nomeArq : listaArq){
            int CONT = 0;
            for (String nomeArq2 : listaArq2) {
                if(nomeArq.equals(nomeArq2)){
                   break; 
                }else{
                    CONT++;
                }
            }
            if(CONT >= listaArq2.size()){
                novoArray.add(nomeArq);
            }
        }
        
        return novoArray;
    }
}

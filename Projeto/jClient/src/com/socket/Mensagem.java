/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import java.io.File;
import java.io.Serializable;


/**
 *
 * @author Gabriel
 */
public class Mensagem implements Serializable{
    //Tipo 1 mensagem, tipo 2 Arquivos
    public int tipo;
    public String conteudo;
    public File file;

    //Objeto construido para mensagem
    public Mensagem(int tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    //Objeto construido para arquivo
    public Mensagem(int tipo, File file) {
        this.tipo = tipo;
        this.file = file;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    
}

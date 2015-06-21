/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;
import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.Socket;
	 
	public class ClientTransfer {
	    public static void main(String[] args) {
	 
	        //Criando Classe cliente para receber arquivo
	        ClientTransfer cliente = new ClientTransfer();
	 
	        //Solicitando arquivo
	        cliente.getFileFromServeR();
	    }
	 
	    private void getFileFromServeR() {
	        Socket sockServer = null;
	        FileOutputStream fos = null;
	        InputStream is = null;
	 
	        try {
	            // Criando conexão com o servidor
	            System.out.println("Conectando com Servidor porta 13267");
	            sockServer = new Socket("192.168.0.102", 13267);
	            is = sockServer.getInputStream();
	 
	            // Cria arquivo local no cliente
	            fos = new FileOutputStream(new File("C:\\Users\\Gabriel\\Desktop\\source-copy.zip"));
	            System.out.println("Arquivo Local Criado C:\\Users\\Gabriel\\Desktop\\source-copy.zip");
	             
	            // Prepara variaveis para transferencia
	            byte[] cbuffer = new byte[1024];
	            int bytesRead;
	 
	            // Copia conteudo do canal
	            System.out.println("Recebendo arquivo...");
	            while ((bytesRead = is.read(cbuffer)) != -1) {
	                fos.write(cbuffer, 0, bytesRead);
	                fos.flush();
	            }
	             
	            System.out.println("Arquivo recebido!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (sockServer != null) {
	                try {
	                    sockServer.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            }
	 
	            if (fos != null) {
	                try {
	                    fos.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            }
	 
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        }
	 
	    }
	}
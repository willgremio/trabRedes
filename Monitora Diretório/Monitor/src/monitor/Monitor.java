/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author gabriel.calderaro
 */
public class Monitor {

    public static void main(String[] args) {

        Path path = Paths.get("C:\\Users\\gabriel\\Desktop");
        WatchService watchService = null;
        try{
            watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, 
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_DELETE);

        }catch(IOException io){
            io.printStackTrace();
        }
        WatchKey key = null;

        while(true){
            try{
                key = watchService.take();

                for(WatchEvent<?> event : key.pollEvents()){
                    Kind<?> kind = event.kind();
                    System.out.println("Evento em "+ event.context().toString() + " " + kind);
                }
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }

            boolean reset = key.reset();
            if(!reset){
                break;
            }
        }





    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Napster
 */
public class Listener implements Runnable{
    
    ServerSocket server;
    
    public Listener(ServerSocket server)
    {
        this.server = server;
    }
    
    
    public void run()
    {
        try
        {
            System.out.println();
            System.out.println("******************************");
            System.out.println("CURRENT THREAD: " + Thread.currentThread().getName());
            System.out.println("******************************");
            
            
            System.out.println();
            
            System.out.println("Waiting for a client ....");
            
            
            Socket socket = server.accept();
           
            System.out.println("Client request accepted ! Connected to " + socket.getLocalAddress());
            
            Listener con = new Listener(server);
            Thread thread = new Thread(con);    
            thread.start();
            
            new editor(server,socket).setVisible(true);
            
        }
        catch(Exception e)
        {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }        
    }
}

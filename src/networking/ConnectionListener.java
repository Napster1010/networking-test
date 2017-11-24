/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * This class is used to establish connection with a client. Multiple threads of this class can run together (Server side)
 * @author Napster
 */
public class ConnectionListener implements Runnable{
    
    ServerSocket server;
    
    public ConnectionListener(ServerSocket server)
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
            
            
            DataInputStream dis = new DataInputStream(socket.getInputStream()); //Input Stream
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); //Output Stream
            
            ActiveClients active = new ActiveClients();
            active.addClient(socket, dos);
           
            ConnectionListener con = new ConnectionListener(server);
            Thread thread = new Thread(con);    
            thread.start();

            //Server side logic
            String read;
            int pos=0;
            while(true)
            {
                read = dis.readUTF();

                if(!(read.startsWith("!DELETE!")))
                    pos = dis.readInt();

                if(read.equals("~"))
                    break;
                else
                {
                    for(DataOutputStream d: active.getWritingStreams())
                    {
                        if(d!=dos)
                        {
                            d.writeUTF(read);       
                            if(!(read.startsWith("!DELETE!")))
                                d.writeInt(pos);
                        }
                    }
                }
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Napster
 */
public class Client {
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost", 23);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String read;
                        
            while(true)
            {
                read = dis.readUTF();
                if(read.equals("~"))
                    break;
                else
                    System.out.print(read);                
            }
                                   
            Thread.currentThread().interrupt();
            dis.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

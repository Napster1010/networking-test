/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Napster
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
            ServerSocket server = new ServerSocket(23);
            System.out.println("Server Started !");            
            
            System.out.println();
            
            System.out.println("Waiting for a client ....");
            
            Socket socket = server.accept();
            System.out.println("Client request accepted ! Connected to " + socket.getLocalSocketAddress());
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String input = dis.readUTF();
            System.out.println("READ " + input + " from client !");
            input = input.concat(" from server");
            
            System.out.println("Sending " + input + " to client .....");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            dos.writeUTF(input);
            
            System.out.println("Successfully sent response to the client !");
            
            server.close();
            socket.close();
            dis.close();
            dos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

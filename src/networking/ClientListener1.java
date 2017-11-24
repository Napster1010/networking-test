/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.JTextArea;

/**
 * This class is used to retrieve data from server and reflect the change on the editor (Client side)
 * @author Napster
 */
public class ClientListener1 implements Runnable{    
    DataInputStream dis;
    JTextArea jtextArea1;
    String read,deleteString;
    int start,end;
    int pos;
    
    public ClientListener1(DataInputStream dis,JTextArea jTextArea1)
    {
        this.dis = dis;
        this.jtextArea1 = jTextArea1;
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                read = dis.readUTF();
                
                if(!(read.startsWith("!DELETE!")))
                    pos = dis.readInt();
                
                if(read.equals("~"))
                    break;
                else
                {
                    if(read.startsWith("!DELETE!"))
                    {
                        deleteString = read.substring(8);
                        StringTokenizer split = new StringTokenizer(deleteString, "!");
                        start = Integer.parseInt(split.nextToken());
                        end = Integer.parseInt(split.nextToken());
                        
                        jtextArea1.replaceRange("", start, end);
                    }
                    else
                        jtextArea1.insert(read, pos);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

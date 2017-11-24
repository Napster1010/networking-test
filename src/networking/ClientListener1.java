/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Napster
 */
public class ClientListener1 implements Runnable{    
    DataInputStream dis;
    JTextArea jtextArea1;
    String read;
    
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
                if(read.equals("~"))
                    break;
                else
                    jtextArea1.setText(jtextArea1.getText() + read);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

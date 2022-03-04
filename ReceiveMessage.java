package serverclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ReceiveMessage extends Thread {

String msg = "";
DataInputStream dis = null;
JTextArea txt_area = null;

public ReceiveMessage(DataInputStream d, JTextArea a){
	this.dis = d;
	this.txt_area = a;
}

public void run(){
	while(true)
	{
		
		try {
			msg = dis.readUTF();
			txt_area.append("\n" +this.getName()+":"+msg);
		} catch (IOException ex) {
			Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null,ex);
		}
	}
}
}

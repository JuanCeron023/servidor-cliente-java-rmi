package serverclient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.TextArea;

public class ClientForm extends JFrame {
	

	Socket server = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;

	
	

	private JScrollPane scroll;
	private JPanel contentPane;
	private JTextArea txt_RecMsg;
	private JTextField txt_msg;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm frame = new ClientForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientForm() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txt_RecMsg = new JTextArea();
		txt_msg = new JTextField();
		
		JButton btn_iniciar = new JButton("Connect");
		btn_iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					server= new Socket("127.0.0.1",6969);
					JOptionPane.showMessageDialog(null, "Conectando al servidor");
					dis = new DataInputStream(server.getInputStream());
					dos = new DataOutputStream(server.getOutputStream());
					ReceiveMessage clientThread = new ReceiveMessage(dis, txt_RecMsg);
					clientThread.setDaemon(true);
					clientThread.setName("Server");
					clientThread.start();
				}
				catch (UnknownHostException e2) {
					JOptionPane.showMessageDialog(null, "Conexion fallida");
				}
				catch (IOException e3) {
					JOptionPane.showMessageDialog(null, "Conexion fallida");
				}
				
			}
		});
		btn_iniciar.setBounds(35, 35, 89, 23);
		contentPane.add(btn_iniciar);
		
		JButton btn_Recibir = new JButton("Recieve");
		btn_Recibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String msg = dis.readUTF();
					txt_RecMsg.append("\n" + msg);
					
				} catch (Exception e2) {
					Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE,null,e2);
				}
				
				
			}
		});
		btn_Recibir.setBounds(324, 114, 89, 23);
		contentPane.add(btn_Recibir);
		
		JButton btn_Enviar = new JButton("Send");
		btn_Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dos.writeUTF(txt_msg.getText());
				} catch (Exception e2) {
					Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE,null,e2);
				}
				
			}
		});
		btn_Enviar.setBounds(324, 185, 89, 23);
		contentPane.add(btn_Enviar);
		
		
		

		txt_RecMsg.setBounds(35, 73, 278, 91);
		scroll = new JScrollPane ( txt_RecMsg );
        scroll.setBounds(35, 73, 278, 91);    
		contentPane.add(scroll);
		

		txt_msg.setBounds(35, 186, 279, 20);
		contentPane.add(txt_msg);
		txt_msg.setColumns(10);
	}
}

package serverclient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ServerForm extends JFrame {


	Socket client = null;
	ServerSocket server = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	
	private JScrollPane scroll;
	private JTextField txtTxtmsg;
	private JPanel contentPane;
	private JTextArea txt_RecMsg;	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerForm frame = new ServerForm();
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
	public ServerForm() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txt_RecMsg = new JTextArea();



		JButton btn_Conectar = new JButton("Start Server");
		btn_Conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					server= new ServerSocket(6969);
					client= server.accept();
					JOptionPane.showMessageDialog(null, "Cliente Aceptado");
					dos = new DataOutputStream(client.getOutputStream());
					dis = new DataInputStream(client.getInputStream());
					ReceiveMessage serverThread = new ReceiveMessage(dis,txt_RecMsg);
					serverThread.setDaemon(true);
					serverThread.setName("Cliente");
					serverThread.start();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Cliente no Disponible");
					Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE,null,e2);
				}
			
				
			}});
		btn_Conectar.setBounds(30, 52, 134, 23);
		contentPane.add(btn_Conectar);
		
		JButton btn_enviar = new JButton("Send");
		btn_enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String msg = txtTxtmsg.getText();
				try {
					dos.writeUTF(msg);
					
				} catch (IOException e2) {
					Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE,null,e2);
				}
				
				
				
			}
		});
		btn_enviar.setBounds(260, 215, 89, 23);
		contentPane.add(btn_enviar);
		
		txtTxtmsg = new JTextField();
		txtTxtmsg.setBounds(40, 216, 212, 20);
		contentPane.add(txtTxtmsg);
		txtTxtmsg.setColumns(10);
		

		txt_RecMsg.setBounds(36, 98, 221, 107);
		scroll = new JScrollPane ( txt_RecMsg );
        scroll.setBounds(36, 98, 221, 107);    
		contentPane.add(scroll);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection cn =  Basededatos.conectar();
					String msg = txtTxtmsg.getText();
					PreparedStatement pst = cn.prepareStatement(
							"select * from usuarios where nombre = ?");
							  pst.setString(1, msg);
					            ResultSet rs = pst.executeQuery();
					            if (rs.next()) {
					            	txtTxtmsg.setText(msg + " " + rs.getString("apellido"));
								} else {
									JOptionPane.showMessageDialog(null, "Dato no encontrado en base de datos");
								}
					            cn.close();
				} catch (Exception e) {
					System.out.print("error en conexion " + e);
				}
				
				
			}
		});
		btnNewButton.setBounds(267, 158, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}

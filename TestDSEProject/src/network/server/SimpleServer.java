package network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import management.Benutzerverwaltung;

/**
 * 
 * Diese Klasse stellt den eigentlichen Serverprozess dar. Mittels der Generalisierungsbeziehung
 * von Thread wird die Nebenläufigkeit ermöglicht.
 *
 */
public class SimpleServer extends Thread {

	private static Benutzerverwaltung benver = Benutzerverwaltung.getInstance();

	private Socket clientSocket;

	private OutputStream rawOut;
	private ObjectOutputStream outObj;
	private DataOutputStream outData;

	private InputStream rawIn;
	private DataInputStream inData;

	public SimpleServer(Socket clientsocket) {
		this.clientSocket = clientsocket;
		start();
	}

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Server gestartet(Port " + serverSocket.getLocalPort() + ")");
			try {
				while (true) {
					System.out.println("Server wartet auf eine neue Verbindung!");
					new SimpleServer(serverSocket.accept());
				}
			} catch (IOException e) {
				System.err.println("SimpleServer:main: Connetion Failed");
				System.exit(1);
			}

		} catch (IOException e) {
			System.err.println("SimpleServer:main: " + e.getMessage());
			System.exit(1);
		}
	}

	public void run() {
		System.out.println("Neuer Thread erfolgreich gestartet(für IP-Adresse: "+this.clientSocket.getInetAddress()+")!");

		try {
		//anlegen der Streams(out und dann in)
			rawOut = this.clientSocket.getOutputStream();
			outObj = new ObjectOutputStream(rawOut);
			outData = new DataOutputStream(rawOut);

			rawIn = this.clientSocket.getInputStream();
			inData = new DataInputStream(rawIn);

			
			String eingabe;

			while((eingabe = inData.readUTF())!=null && !eingabe.equals("ende")){
				String[] eingabeGesplittet = eingabe.trim().split("-");
				String anfrage = eingabeGesplittet[0];
	
				System.out.println("Befehl von Client("+this.clientSocket.getInetAddress()+"): "+anfrage);
				if (anfrage.equals("ende")){
					System.out.println("Client("+this.clientSocket.getInetAddress()+") beendete Verbindung mittels Eingabe 'ende'");
					break;
				}
	
				if (anfrage.equals("getBenutzer"))
					outObj.writeObject(benver.getBenutzerListe());
	
				if (anfrage.equals("pruefeLogin")) {
					System.out.println(eingabeGesplittet[1] + " " + eingabeGesplittet[2]);
					outData.writeUTF("loginBestätigt");
					;
				}
				
				if(anfrage.equals("getBenutzerByUname")){
					System.out.println("Client("+this.clientSocket.getInetAddress()+"): "+anfrage+", Uname: "+eingabeGesplittet[1]);
					outObj.writeObject(benver.getBenByUsername(eingabeGesplittet[1]));
				}
				
			}

			System.out.println("Server: Beende Connection mit Client("+this.clientSocket.getInetAddress()+")");
			outObj.flush();
			outObj.close();
			outData.close();
			rawOut.close();

			//inObj.close();
			rawIn.close();
		}catch(IOException e)
		{
			System.err.println("SimpleServer:run: bei Client("+this.clientSocket.getInetAddress()+") " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

}

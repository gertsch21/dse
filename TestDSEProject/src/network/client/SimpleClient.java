package network.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import modell.*;

public class SimpleClient {

	private Socket toSocket;
	private OutputStream rawOut;
	private DataOutputStream out;
	
	private InputStream rawIn;
	private DataInputStream dataIn;
	private ObjectInputStream objIn;
	
	public SimpleClient(){
		this(1234);
	}
	public SimpleClient(int port){
		this(port,"localhost");
	}
	public SimpleClient(int port, String host){
		try {
			this.toSocket = new Socket(host, port);
			
			this.rawOut = toSocket.getOutputStream();
			this.out = new DataOutputStream(rawOut);
			
			this.rawIn = toSocket.getInputStream();
			this.objIn = new ObjectInputStream(rawIn);
			this.dataIn = new DataInputStream(rawIn);
		} catch (UnknownHostException e) {
			System.err.println("SimpleClient:UnkownHost("+e.getMessage()+")!");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("SimpleClient:IOException("+e.getMessage()+")!");
			System.exit(1);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		SimpleClient client = new SimpleClient(1234,"localhost");
		
		DataOutputStream out = client.getOut();
		ObjectInputStream objIn = client.getobjIn();
		
		
		
		
		
		
		List<Benutzer> liste = client.getBenutzerListe();
		
		
		out.writeUTF("pruefeLogin"+"-"+"gertsch"+"-"+"hallo");
		
		System.out.println("Loginantwort von Server: "+client.getDataIn().readUTF());
		
		out.writeUTF("getBenutzerByUname gertsch");
	
		System.out.println("User empfangen: "+((Person)objIn.readObject()).toString());
		
		out.writeUTF("ende");
		
		for(Benutzer a : liste)
			System.out.println(a);
		System.out.println("Client: erfolgreich beendet!");

	}
	
	@SuppressWarnings("unchecked")
	public List<Benutzer> getBenutzerListe(){
		try {
			this.getOut().writeUTF("getBenutzer");
			return (List<Benutzer>)this.getobjIn().readObject();
		} catch (IOException e) {
			System.err.println("SimpleClient:getPersonenListe: "+e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println("SimpleClient:getPersonenListe: "+e.getMessage());
			return null;
		}
	}
	public DataOutputStream getOut() {
		return out;
	}
	public ObjectInputStream getobjIn() {
		return objIn;
	}
	public DataInputStream getDataIn() {
		return dataIn;
	}

}

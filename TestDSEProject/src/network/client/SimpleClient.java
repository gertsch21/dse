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
	
	public void beenden(){
		try {
			out.writeUTF("ende");
		} catch (IOException e) {
			System.err.println("SimpleClient:beenden: Fehler beim beenden("+e.getMessage()+")");
		}
	}
	
	public boolean pruefeLogin(String username, String password){
		try{
			this.getOut().writeUTF("pruefeLogin-"+username+"-"+password);
			return (boolean)this.dataIn.readBoolean();
			
		}catch(IOException e){
			System.err.println("SimpleClient:pruefeLogin: "+e.getMessage());
			return false;
		}
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
	
	@SuppressWarnings("unchecked")
	public List<Produkt> getProduktListe(){
		try {
			this.getOut().writeUTF("getProdukte");
			return (List<Produkt>)this.getobjIn().readObject();
		} catch (IOException e) {
			System.err.println("SimpleClient:getProduktListe: "+e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println("SimpleClient:getProduktListe: "+e.getMessage());
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

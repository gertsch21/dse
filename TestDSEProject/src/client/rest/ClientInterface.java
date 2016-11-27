package client.rest;

public interface ClientInterface {
	
	public void pruefeLoginXml(String usern, String pwd);
	public boolean pruefeLoginPlain(String usern, String pwd);

}

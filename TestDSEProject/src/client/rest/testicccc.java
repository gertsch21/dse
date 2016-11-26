package client.rest;

public class testicccc {

	public static void main(String[] args) {
		ClientInterface hhhh = new RestClient();
		
		hhhh.pruefeLoginXml("gertsch", "hallo");
		hhhh.pruefeLoginPlain("gertsch", "hallo");

	}

}

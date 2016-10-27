package Admin_GUI;

import network.server.SimpleServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by josefweber on 27.10.16.
 */
public class PhysicalServer extends Thread  {

    private ServerSocket serverSocket;

    public void run(){
        try {
            serverSocket = new ServerSocket(1234);
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

    public void shutdown() throws IOException {
        this.serverSocket.close();
        System.out.println("Closing Serversocket port: " + serverSocket.getLocalPort());

    }

}

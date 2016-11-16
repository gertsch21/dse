package junitTest;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;

import Admin_GUI.PhysicalServer;
import network.server.Server;

public class ServerStartJunitTest {
	Socket clientsocket = new Socket();
	PhysicalServer testStart = new PhysicalServer();
	@Test
	public void test() {
		Server test = new Server(clientsocket);
		testStart.run();
	}

}

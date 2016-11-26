package webservice.rest;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;



public class ServerStartUp {
	
	public final static String URL = "http://localhost:9999/"; 
	
	
	 public static void main(String[] args) {
	    	
         JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
            sf.setResourceClasses(UserService.class);
            sf.setResourceProvider(UserService.class, new SingletonResourceProvider(new UserService()));
            sf.setAddress(URL);
            Server server = sf.create();    
            
             
           
    }

}

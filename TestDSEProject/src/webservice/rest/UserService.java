package webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import management.Benutzerverwaltung;
/**
 * RootResource 
 * @author 
 *
 */

@Path("/user")
public class UserService {
	
	Benutzerverwaltung benutzer = Benutzerverwaltung.getInstance();

	@GET
    @Path("/login/{username}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String einloggenText(@PathParam("username") String usern, @PathParam("password") String pwd) {
		if(benutzer.pruefeLogin(usern, pwd)==true) return	"Willkommen   " + usern;
		else return "Bitte ueberpruefen Sie Benutzername und/oder Passwort";
		
			
		
	}

	@GET
    @Path("login/{username}/{password}")
    @Produces(MediaType.TEXT_XML)
    public String einloggen(@PathParam("username") String usern, @PathParam("password") String pwd) {
		if(benutzer.pruefeLogin(usern, pwd)==true)    return "<?xml version=\"1.0\"?>" + "Willkommen " +  usern + "</result>";
		else return "<?xml version=\"1.0\"?>"+ "Bitte ueberpruefen Sie Benutzername und/oder Passwort" +"</result>";

    }
     
    
     
    
}
package Client_GUI;

import client.rest.RestClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by josefweber on 29.10.16.
 */
public class ControllerHome {

    @FXML
    private Button logoutbtn;

    @FXML private Label welcomeName;
    private RestClient cl;
    private String name;


    public void setUsername(String name){
        this.name = name;
        welcomeName.setText("Welcome " + name);
    }

    public void setCl(RestClient cl) {
        this.cl = cl;
    }

    public void logout() throws IOException {
        System.out.println("Logging Out..");

        System.out.println("Username: " + name);

       // cl.beenden();
        System.out.println("Verbindung beendet ..");

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home = new Scene(root);
        Stage homeStage = (Stage) logoutbtn.getScene().getWindow();
        homeStage.setScene(home);
        homeStage.show();
    }
}

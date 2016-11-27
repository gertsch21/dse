package Client_GUI;

import client.rest.RestClient;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.Socket;

public class ControllerLogin {

    @FXML private Button loginbtn;
    @FXML private Button registerloginBtn;
    @FXML private TextField unameinput;
    @FXML private TextField pwinput;
    @FXML private Label loginerror;
    @FXML private Label serverstatus;

    RestClient cl;

    public void loginClicked() throws IOException {
        String name = unameinput.getText();
        String pass = pwinput.getText();

        if (name.length() == 0 || pass.length() == 0){
            //System.out.println("No valid Data");
            loginerror.setText("Insert all fields");
        } else {
            System.out.println("Login attempt: " + unameinput.getText() + " , " + pwinput.getText().toString());

            if (available(9999)) {

                cl = new RestClient();

                Boolean x = cl.pruefeLoginPlain(name, pass);

                if (x) {
                    //System.out.println("Login Sucess: " + name);

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = fxmlLoader.load();

                    ControllerHome ch = fxmlLoader.<ControllerHome>getController();
                    ch.setUsername(name);
                    ch.setCl(cl);

                    Scene scene = new Scene(root);
                    Stage homeStage = (Stage) loginbtn.getScene().getWindow();

                    homeStage.setScene(scene);
                    homeStage.show();

                } else {
                    //System.out.println("login failed");
                    loginerror.setText("Login failed");
                    loginerror.setTextFill(Color.web("#BF0000"));
                }
            } else {
                System.out.println("System: Offline");
                serverstatus.setText("System: Offline");
                serverstatus.setTextFill(Color.web("#BF0000"));
            }

        }

    }

    public static boolean available(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    public void registerClicked() throws IOException {
        System.out.println("pressed Registration in Login..");

        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        Scene regiScene = new Scene(root);
        Stage regiStage = (Stage) registerloginBtn.getScene().getWindow();
        regiStage.setScene(regiScene);
        regiStage.show();
    }

    public void checkClicked(){
        if (available(9999)){
            System.out.println("System: Online");
            serverstatus.setText("System: Online");
            serverstatus.setTextFill(Color.web("#168500"));
        } else {
            System.out.println("System: Offline");
            serverstatus.setText("System: Offline");
            serverstatus.setTextFill(Color.web("#BF0000"));
        }
    }
}

package Client_GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by josefweber on 29.10.16.
 */
public class ControllerRegistration {

    @FXML private Button backLoginBtn;

    @FXML private TextField nameinput;
    @FXML private TextField mailinput;
    @FXML private TextField pwinput;
    @FXML private TextField pwinput2;

    @FXML private Label err1;
    @FXML private Label err2;
    @FXML private Label err3;
    @FXML private Label err4;
    @FXML private Label errMain;

    public void clickLoginBack() throws IOException {
        System.out.println("Going back to login");

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene back = new Scene(root);
        Stage regiStage = (Stage) backLoginBtn.getScene().getWindow();
        regiStage.setScene(back);
        regiStage.show();
    }

    public void clickRegister(){
        String name = nameinput.getText();
        String mail = mailinput.getText();
        String pw = pwinput.getText();
        String pw2= pwinput2.getText();

        if (name.length() == 0){
            err1.setText("!");
            System.out.println("Name = 0");
        } else {
            err1.setText("");
        }
        if (mail.length() == 0){
            err2.setText("!");
            System.out.println("Mail = 0");
        }
        else {
            err2.setText("");
        }
        if (pw.length() == 0){
            err3.setText("!");
            System.out.println("Pw = 0");
        }  else {
            err3.setText("");
        }

        if(pw.length() == 0 || pw.length() == 0 || pw.length() == 0){
            errMain.setText("Fill in all the Fields!");
        }  else {
            errMain.setText("");
        }


    }



}

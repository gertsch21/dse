package Admin_GUI;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

// Import für ActionEvent nicht das Selbe!!!
//import java.awt.event.ActionEvent;


//implements EventHandler<ActionEvent>
public class ServerGui extends Application implements Runnable  {


    Thread i;
    Button b2;
    Button b1;

    Stage window;
    Scene scene0, scene1, scene2;

    public static void main(String[] args) {

        Runnable task = new ServerGui();
        task.run();
    }

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        Label label1 = new Label("Welcome Admin");
        window.setTitle("Admin Controller");


        b1 = new Button();
        b1.setText("Start Server");

        Button exampleAlert = new Button();
        exampleAlert.setText("Show Team");

        Button closeButton = new Button();
        closeButton.setText("Close Program");

        // -> lambda notation
        b1.setOnAction(e -> {
            try {
                startServer();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            window.setScene(scene2);
            System.out.println("Switched to Scene 2..");
        });

        exampleAlert.setOnAction(e -> {
            AlertBox.display("Admins:","Joe, Mirza, Gerhart, Sneza");
        });

        closeButton.setOnAction(e -> {
            closePrg();
        });

        // Schließt AUCH wenn das Fenster rechts oben geschlossen wird
        window.setOnCloseRequest(e -> {
            // ohne consume wird der Bool Wert ignoriert
            e.consume();
            closePrg();
        });


        //  ** ** ** ** ** ** ** ** ** **

        // Layout 1
        // VBox stacks vertically
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,b1,exampleAlert,closeButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 600, 300);

        //  ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **

        b2 = new Button();
        b2.setText("Stop Server");

        b2.setOnAction(e -> {
            //AlertBox.display("This is a Title", "Thats a Message");
            boolean result = ConfirmBox.display("Stop Server", "Do you really want to Stop the Server?");
            if (result == true){
                try {
                    stopServer();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                window.setScene(scene1);
                System.out.println("Back to Scene 1..");
            }

        });

        //  ** ** ** ** ** ** ** ** ** **

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(b2);
        scene2 = new Scene(layout2, 600, 300);

        // ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **

        // Default - Startup
        window.setScene(scene1);
        window.show();
    }


    public void startServer() throws IOException {
        System.out.println("Starting Server..");
        System.out.println("new Thread ..");
        i = new PhysicalServer();
        i.start();
    }

    public void stopServer() throws IOException {
        System.out.println("Stopping Server..");
        ((PhysicalServer)i).shutdown();
        i.stop();

    }

    private void closePrg(){
        Boolean answer = ConfirmBox.display("", "Close the Admin Program?");
        if (answer){
            System.out.println("Closing Program..");
            System.out.println("Logging to Console..");
            window.close();
        }
    }


}

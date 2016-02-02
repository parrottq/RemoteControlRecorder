package com.parrott;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
	    launch(args);
    }

    private Stage window;
    private Scene scene;

    private TextField filePath;
    private Button fileBrowser;
    private HBox fileBox;

    private TextField ipToTransmit;
    private Button startTransmitter;
    private HBox transmitBox;

    private Button startReceiving;
    private HBox receiverBox;

    private VBox bigBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        filePath = new TextField();
        fileBrowser = new Button("...");
        fileBox = new HBox(filePath, fileBrowser);

        ipToTransmit = new TextField();
        startTransmitter = new Button("Transmit");
        transmitBox = new HBox(ipToTransmit, startTransmitter);
        transmitBox.setSpacing(5);

        startReceiving = new Button("Receive");
        receiverBox = new HBox(startReceiving);

        bigBox = new VBox(transmitBox, receiverBox, fileBox);
        bigBox.setSpacing(20);
        bigBox.setPadding(new Insets(5,10,5,10));

        scene = new Scene(bigBox);
        window.setScene(scene);
        window.show();

    }
}

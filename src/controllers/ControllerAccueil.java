package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAccueil {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void PageClient(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageClient.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void PageAbonnement(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAbonnement.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void PageRevue(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageRevue.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void PagePeriodicite(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PagePeriodicite.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void closeProject(ActionEvent actionEvent) {
    }
}

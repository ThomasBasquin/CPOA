package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.metier.Periodicite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.ControllerPeriodicite.periodiciteToModify;

public class ControllerModifierPeriodicite implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private TextField txt_PerioLib;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_PerioLib.setText(periodiciteToModify.getLibelle());
    }

    public void changePerio(ActionEvent actionEvent) throws IOException {
        Periodicite periodicite = new Periodicite(periodiciteToModify.getId(),txt_PerioLib.getText());
        persistance.getPeriodiciteDAO().update(periodicite);

        root = FXMLLoader.load(getClass().getResource("../vue/PagePeriodicite.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModPerio(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../vue/PagePeriodicite.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}

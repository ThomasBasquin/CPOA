package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAccueil implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static DaoFactory persistance;

    @FXML
    private RadioButton radio_Sql;
    @FXML
    private RadioButton radio_ListMemoire;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = DaoFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
    }

    @FXML
    public void PageClient(ActionEvent actionEvent) throws IOException { //switch to client's page
        if (!radio_ListMemoire.isSelected() && !radio_Sql.isSelected()) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("ERREUR PERSISTANCE");
            dialog.setContentText("Veuillez choisir la methode de Stockage");
            dialog.showAndWait();
        } else {
            root = FXMLLoader.load(getClass().getResource("../vue/PageClient.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void PageAbonnement(ActionEvent actionEvent) throws IOException { //switch to Abonnement's page
        if (!radio_ListMemoire.isSelected() && !radio_Sql.isSelected()) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("ERREUR PERSISTANCE");
            dialog.setContentText("Veuillez choisir la methode de Stockage");
            dialog.showAndWait();
        } else {
            root = FXMLLoader.load(getClass().getResource("../vue/PageAbonnement.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void PageRevue(ActionEvent actionEvent) throws IOException { //switch to Revue's page
        if (!radio_ListMemoire.isSelected() && !radio_Sql.isSelected()) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("ERREUR PERSISTANCE");
            dialog.setContentText("Veuillez choisir la methode de Stockage");
            dialog.showAndWait();
        } else {
            root = FXMLLoader.load(getClass().getResource("../vue/PageRevue.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void PagePeriodicite(ActionEvent actionEvent) throws IOException { //switch to Periodicite's page
        if (!radio_ListMemoire.isSelected() && !radio_Sql.isSelected()) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("ERREUR PERSISTANCE");
            dialog.setContentText("Veuillez choisir la methode de Stockage");
            dialog.showAndWait();
        } else {
            root = FXMLLoader.load(getClass().getResource("../vue/PagePeriodicite.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void closeProject(ActionEvent actionEvent) { //button close project
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void getDao(ActionEvent actionEvent) { // choose between SQL & Liste Memoire
        if(radio_Sql.isSelected()) {
            persistance = DaoFactory.getDAOFactory(Persistance.MYSQL);
        } else if (radio_ListMemoire.isSelected()) {
            persistance = DaoFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
        }
    }

    public static DaoFactory getPersistance() {
        return persistance;
    }

}

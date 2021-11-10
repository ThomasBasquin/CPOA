package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Client;
import model.metier.Periodicite;
import model.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPeriodicite implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private Button btn_PerioAccueil;
    @FXML
    private Button btn_PerioCreate;
    @FXML
    private Button btn_PerioMod;
    @FXML
    private Button btn_PerioSupp;
    @FXML
    private ChoiceBox<String> cb_PerioLib;
    @FXML
    private TableColumn<Periodicite, Integer> col_PerioID;
    @FXML
    private TableColumn<Periodicite, String> col_PerioLibelle;
    @FXML
    private TableView<Periodicite> table_Perio;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_PerioID.setCellValueFactory(new PropertyValueFactory<Periodicite , Integer>("id_periodicite"));
        col_PerioLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));
    }

    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void createPerio(ActionEvent actionEvent) {
        Periodicite periodicite = new Periodicite(0, cb_PerioLib.getValue());
        persistance.getPeriodiciteDAO().create(periodicite);
    }

    public void changePerio(ActionEvent actionEvent) { // Modify the parameter of a Client
    }

    public void deletePerio(ActionEvent actionEvent) { // Delete a Periodicite
        persistance.getPeriodiciteDAO().delete(table_Perio.getSelectionModel().getSelectedItem()); // delete the periodicite in the Liste Memoire of the selectionned row
        table_Perio.getItems().removeAll(table_Perio.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)
    }



}

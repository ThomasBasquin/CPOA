package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Revue;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAbonnement implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private ChoiceBox<Client> cb_AboClient;
    @FXML
    private ChoiceBox<Revue> cb_AboRevue;
    @FXML
    private TextField txt_AboDebut;
    @FXML
    private TextField txt_AboFin;
    @FXML
    private TableColumn<Abonnement, Integer> col_AboClient;
    @FXML
    private TableColumn<Abonnement, Date> col_AboDebut;
    @FXML
    private TableColumn<Abonnement, Date> col_AboFin;
    @FXML
    private TableColumn<Abonnement, Integer> col_AboRevue;
    @FXML
    private TableView<Abonnement> table_Abo;


    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cb_AboClient.setItems(FXCollections.observableArrayList(persistance.getClientDAO().findAll()));
        this.cb_AboRevue.setItems(FXCollections.observableArrayList(persistance.getRevueDAO().findAll()));
        col_AboClient.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("Id_client"));
        col_AboRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("Id_Revue"));
        col_AboDebut.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_deb"));
        col_AboFin.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_fin"));
    }

    public void createAbo(ActionEvent actionEvent) { //
        Abonnement abonnement = new Abonnement(1, txt_AboDebut.getText(), txt_AboFin.getText(), cb_AboClient.getId(), cb_AboRevue.getValue());
        persistance.getAbonnementDAO().create(abonnement);
    }

    public void changeAbo(ActionEvent actionEvent) { // Modify the parameter
    }

    public void deleteAbo(ActionEvent actionEvent) {
        persistance.getAbonnementDAO().delete(table_Abo.getSelectionModel().getSelectedItem());
        table_Abo.getItems().removeAll(table_Abo.getSelectionModel().getSelectedItem());
    }
}

package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.dao.Dao;
import model.dao.factory.DaoFactory;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAbonnement implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Abonnement aboToModify;
    private DaoFactory persistance = ControllerAccueil.getPersistance();
    public static int index;

    public static int getIndex() {
        return index;
    }

    @FXML
    private ChoiceBox<Client> cb_AboClient;
    @FXML
    private ChoiceBox<Revue> cb_AboRevue;

    @FXML
    private TableView<Abonnement> table_Abo;
    @FXML
    private TableColumn<Abonnement, Integer> col_AboID;
    @FXML
    private TableColumn<Abonnement, Client> col_AboClient;
    @FXML
    private TableColumn<Abonnement, Revue> col_AboRevue;
    @FXML
    private TableColumn<Abonnement, Date> col_AboDebut;
    @FXML
    private TableColumn<Abonnement, Date> col_AboFin;

    @FXML
    private DatePicker dp_AboDebut;
    @FXML
    private DatePicker dp_AboFin;


    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_AboID.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        col_AboClient.setCellValueFactory(new PropertyValueFactory<Abonnement, Client>("id_client"));
        col_AboRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, Revue>("id_revue"));
        col_AboDebut.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("date_deb"));
        col_AboFin.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("date_fin"));

        cb_AboClient.setItems(FXCollections.observableArrayList(persistance.getClientDAO().findAll()));
        cb_AboRevue.setItems(FXCollections.observableArrayList(persistance.getRevueDAO().findAll()));

        table_Abo.getItems().addAll(persistance.getAbonnementDAO().findAll());
    }

    private boolean erreurAbo() {

        String errorMessage = "Il y a des erreurs dans vos saisies :\n\n";
        boolean error = false;

        if (cb_AboClient.getSelectionModel().isEmpty()) {
            errorMessage += "Veuillez selectionner un client !\n";
            error = true;
        }
        if (cb_AboRevue.getSelectionModel().isEmpty()) {
            errorMessage += "Veuillez selectionner une revue !\n";
            error = true;
        }
        if (dp_AboDebut.getValue() == null) {
            errorMessage += "Veuillez saisir une date de debut !\n";
            error = true;
        }
        if (dp_AboFin.getValue() == null) {
            errorMessage += "Veuillez selectionner une date de fin !\n";
            error = true;
        }
        if (error) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("Probleme de saisies");
            dialog.setContentText(errorMessage);
            dialog.showAndWait();
        }
        return error;
    }

    public void createAbo(ActionEvent actionEvent) {
        if (!erreurAbo()) {
            //LocalDate of the DatePicker convert to Date
            LocalDate localDateDeb = dp_AboDebut.getValue();
            Instant instantDeb = Instant.from(localDateDeb.atStartOfDay(ZoneId.systemDefault()));
            Date dateDeb = Date.from(instantDeb);

            LocalDate localDateFin = dp_AboFin.getValue();
            Instant instantFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
            Date dateFin = Date.from(instantFin);

            Abonnement abonnement = new Abonnement(0, dateDeb, dateFin, cb_AboClient.getValue().getId_client(), cb_AboRevue.getValue().getId_revue());
            persistance.getAbonnementDAO().create(abonnement);

            // actualized the table
            table_Abo.getItems().clear();
            table_Abo.getItems().addAll(persistance.getAbonnementDAO().findAll());
        }
    }

    public void PageModifierAbonnement(ActionEvent actionEvent) throws IOException{
        if (table_Abo.getSelectionModel().getSelectedItem() == null) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setResizable(false);
            dialog.setTitle("Information :");
            dialog.setContentText("Veuillez selectionnez une revue a modifier dans le tableau!");
            dialog.showAndWait();
        } else {
            aboToModify=table_Abo.getSelectionModel().getSelectedItem();
            index = 1;

            root = FXMLLoader.load(getClass().getResource("../vue/PageModifierAbonnement.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    public void deleteAbo(ActionEvent actionEvent) {
        ObservableList<Abonnement> items = table_Abo.getItems();

        if (items.isEmpty()) { //Error - the table is empty, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Erreur !");
            dialog.setContentText("Il n'y a plus rien a supprimer!");
            dialog.showAndWait();
        } else if (table_Abo.getSelectionModel().getSelectedItem() == null) { //Error -  no item selectionned in the table, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("Attention !");
            dialog.setContentText("Veuillez selectionnez une revue a supprimer dans le tableau!");
            dialog.showAndWait();
        } else { // delete the revue in the bdd, and in the table
            persistance.getAbonnementDAO().delete(table_Abo.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
            table_Abo.getItems().removeAll(table_Abo.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)
        }
    }
}

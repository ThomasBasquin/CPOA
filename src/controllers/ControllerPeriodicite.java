package controllers;

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
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Periodicite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPeriodicite implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();
    public static Periodicite periodiciteToModify;
    public static int index;

    public static int getIndex() {
        return index;
    }

    @FXML
    private TextField txt_PerioLib;

    @FXML
    private Button btn_PerioAccueil;
    @FXML
    private Button btn_PerioCreate;
    @FXML
    private Button btn_PerioMod;
    @FXML
    private Button btn_PerioSupp;

    @FXML
    private TableView<Periodicite> table_Perio;
    @FXML
    private TableColumn<Periodicite, Integer> col_PerioID;
    @FXML
    private TableColumn<Periodicite, String> col_PerioLibelle;


    @FXML
    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_PerioID.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("id"));
        col_PerioLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));

        table_Perio.getItems().addAll(persistance.getPeriodiciteDAO().findAll());
    }

    private boolean emptyFields(TextField txt) {
        boolean empty = false;
        if (txt.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }

    private boolean noNumbers(TextField txt) {
        boolean numbers = false;
        if (txt.getText().trim().matches(".*\\d.*")) {
            numbers = true;
        }
        return numbers;
    }

    private boolean erreurPerio() {

        String errorMessage = "Il y a des erreurs dans vos saisies :\n\n";
        boolean error = false;

        if (emptyFields(txt_PerioLib)) {
            errorMessage = errorMessage + "Veuillez saisir un libelle !\n";
            error = true;
        } else if (noNumbers(txt_PerioLib)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autorise dans le libelle !\n";
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


    public void createPerio(ActionEvent actionEvent) {
        if (!erreurPerio()) {
            Periodicite perio = new Periodicite(0, txt_PerioLib.getText());
            persistance.getPeriodiciteDAO().create(perio);

            // actualized the table
            table_Perio.getItems().clear();
            table_Perio.getItems().addAll(persistance.getPeriodiciteDAO().findAll());
        }

    }

    public void PageModifierPeriodicite(ActionEvent actionEvent) throws IOException{
        if (table_Perio.getSelectionModel().getSelectedItem() == null) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setResizable(false);
            dialog.setTitle("Information :");
            dialog.setContentText("Veuillez selectionnez une periodicite a modifier dans le tableau!");
            dialog.showAndWait();
        } else {
            periodiciteToModify=table_Perio.getSelectionModel().getSelectedItem();
            index = 1;
            root = FXMLLoader.load(getClass().getResource("../vue/PageModifierPerio.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void deletePerio(ActionEvent actionEvent) {
        ObservableList<Periodicite> items = table_Perio.getItems();

        if(items.isEmpty()) { //Error - the table is empty, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Erreur !");
            dialog.setContentText("Il n'y a plus rien a supprimer!");
            dialog.showAndWait();
        } else if(table_Perio.getSelectionModel().getSelectedItem() == null) { //Error -  no item selectionned in the table, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("Attention !");
            dialog.setContentText("Veuillez selectionnez une periodicite a supprimer dans le tableau!");
            dialog.showAndWait();
        } else { // delete the periodicite in the bdd, and in the table
            persistance.getPeriodiciteDAO().delete(table_Perio.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
            table_Perio.getItems().removeAll(table_Perio.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)

        }
    }
}

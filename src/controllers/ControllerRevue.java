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
import model.dao.factory.DaoFactory;
import model.metier.Periodicite;
import model.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRevue implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Revue revueToModify;
    private DaoFactory persistance = ControllerAccueil.getPersistance();
    public static int index;

    public static int getIndex() {
        return index;
    }

    @FXML
    private TextArea txt_RevueDesc;
    @FXML
    private TextField txt_RevueTarif;
    @FXML
    private TextField txt_RevueTitre;

    @FXML
    private ChoiceBox<Periodicite> cb_RevuePerio;

    @FXML
    private TableView<Revue> table_Revue;
    @FXML
    private TableColumn<Revue, String> col_RevueDesc;
    @FXML
    private TableColumn<Revue, Integer> col_RevueID;
    @FXML
    private TableColumn<Revue, Periodicite> col_RevuePeriodicite;
    @FXML
    private TableColumn<Revue, Float> col_RevueTarif;
    @FXML
    private TableColumn<Revue, String> col_RevueTitre;
    @FXML
    private TableColumn<Revue, String> col_RevueVisuel;


    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_RevueID.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_revue"));
        col_RevueTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        col_RevueDesc.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        col_RevueTarif.setCellValueFactory(new PropertyValueFactory<Revue, Float>("tarif_numeros"));
        col_RevueVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));
        col_RevuePeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, Periodicite>("id_periodicite"));

        cb_RevuePerio.setItems(FXCollections.observableArrayList(persistance.getPeriodiciteDAO().findAll()));

        table_Revue.getItems().addAll(persistance.getRevueDAO().findAll());
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

    private boolean erreurRevue() {

        String errorMessage = "Il y a des erreurs dans vos saisies :\n\n";
        boolean error = false;

        if (emptyFields(txt_RevueTitre)) {
            errorMessage += "Veuillez saisir un titre !\n";
            error = true;
        } else if (noNumbers(txt_RevueTitre)) {
            errorMessage += "Aucun chiffre n'est autorise dans le titre !\n";
            error = true;
        }
        if (txt_RevueDesc.getText().trim().equals("")) {
            errorMessage += "Veuillez saisir une description !\n";
            error = true;
        } else if (txt_RevueDesc.getText().trim().matches(".*\\d.*")) {
            errorMessage += "Aucun chiffre n'est autorise pour la saisie de la description !\n";
            error = true;
        }
        if (emptyFields(txt_RevueTarif)) {
            errorMessage += "Veuillez saisir un tarif !\n";
            error = true;
        } else if (!noNumbers(txt_RevueTarif)) {
            errorMessage += "Veillez a saisir un nombre pour le tarif !\n";
            error = true;
        }
        if (cb_RevuePerio.getSelectionModel().isEmpty()) {
            errorMessage += "Veuillez selectionner une periodicite !\n";
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

    public void createRevue(ActionEvent actionEvent) {
        if (!erreurRevue()) {
            Revue revue = new Revue(0, cb_RevuePerio.getValue().getId(), txt_RevueDesc.getText(), Float.parseFloat(txt_RevueTarif.getText()), txt_RevueTitre.getText(), "");
            persistance.getRevueDAO().create(revue);

            // actualized the table
            table_Revue.getItems().clear();
            table_Revue.getItems().addAll(persistance.getRevueDAO().findAll());
        }
    }

    public void PageModifierRevue(ActionEvent actionEvent) throws IOException{
        if (table_Revue.getSelectionModel().getSelectedItem() == null) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setResizable(false);
            dialog.setTitle("Information :");
            dialog.setContentText("Veuillez selectionnez une revue a modifier dans le tableau!");
            dialog.showAndWait();
        } else {
            revueToModify=table_Revue.getSelectionModel().getSelectedItem();
            index = 1;

            root = FXMLLoader.load(getClass().getResource("../vue/PageModifierRevue.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    public void deleteRevue(ActionEvent actionEvent) {
        ObservableList<Revue> items = table_Revue.getItems();

        if(items.isEmpty()) { //Error - the table is empty, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Erreur !");
            dialog.setContentText("Il n'y a plus rien a supprimer!");
            dialog.showAndWait();
        } else if(table_Revue.getSelectionModel().getSelectedItem() == null) { //Error -  no item selectionned in the table, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("Attention !");
            dialog.setContentText("Veuillez selectionnez une revue a supprimer dans le tableau!");
            dialog.showAndWait();
        } else { // delete the revue in the bdd, and in the table
            persistance.getRevueDAO().delete(table_Revue.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
            table_Revue.getItems().removeAll(table_Revue.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)

        }
    }
}

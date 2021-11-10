package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.Dao;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Client ClientToModify;
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private TextField txt_ClientNom;
    @FXML
    private TextField txt_ClientPrenom;
    @FXML
    private TextField txt_ClientVoie;
    @FXML
    private TextField txt_ClientNVoie;
    @FXML
    private TextField txt_ClientVille;
    @FXML
    private TextField txt_ClientPays;
    @FXML
    private TextField txt_ClientZip;


    @FXML
    public  TableView<Client> table_Client;
    @FXML
    private TableColumn<Client, Integer> col_ClientID;
    @FXML
    private TableColumn<Client, String> col_ClientNom;
    @FXML
    private TableColumn<Client, String> col_ClientPrenom;
    @FXML
    private TableColumn<Client, String> col_ClientAdresse;



    public void PageAccueil(ActionEvent actionEvent) throws IOException { // Back to Accueil
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) { //Initialize

        col_ClientID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_client"));
        col_ClientNom.setCellValueFactory(new PropertyValueFactory<Client, String>("Nom"));
        col_ClientPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("Prenom"));
        col_ClientAdresse.setCellValueFactory(new PropertyValueFactory<Client, String>("AdresseComplete"));

        table_Client.getItems().addAll(persistance.getClientDAO().findAll());

    }

    public void createClient(ActionEvent actionEvent) { // Create a Client
        Client client = new Client(txt_ClientNom.getText(),txt_ClientPrenom.getText(),txt_ClientNVoie.getText(), txt_ClientVille.getText(),txt_ClientPays.getText(),txt_ClientVoie.getText(),txt_ClientZip.getText(), 3);
        persistance.getClientDAO().create(client);

        // actualized the table
        table_Client.getItems().addAll(client);

    }

    @FXML
    public void PageModifier(ActionEvent actionEvent) throws IOException { //switch to Periodicite's page

        if (table_Client.getSelectionModel().getSelectedItem() == null) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setResizable(false);
            dialog.setTitle("Information :");
            dialog.setContentText("Veuillez selectionnez un client a modifier dans le tableau!");
            dialog.showAndWait();
        } else {
            ClientToModify=table_Client.getSelectionModel().getSelectedItem();

            root = FXMLLoader.load(getClass().getResource("../vue/PageModifier.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void deleteClient(ActionEvent actionEvent) { // Delete a Client
        ObservableList<Client> items = table_Client.getItems();

        if(items.isEmpty()) { //Error - the table is empty, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Erreur !");
            dialog.setContentText("Il n'y a plus rien a supprimer!");
            dialog.showAndWait();
        } else if(table_Client.getSelectionModel().getSelectedItem() == null) { //Error -  no item selectionned in the table, nothing to delete
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setResizable(false);
            dialog.setTitle("Attention !");
            dialog.setContentText("Veuillez selectionnez un client a supprimer dans le tableau!");
            dialog.showAndWait();
        } else { // delete the client in the bdd, and in the table
            persistance.getClientDAO().delete(table_Client.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
            table_Client.getItems().removeAll(table_Client.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)

        }
    }
}
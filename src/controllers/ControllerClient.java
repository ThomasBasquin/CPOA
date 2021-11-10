package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private TableView<Client> table_Client;
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
        col_ClientNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        col_ClientPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        col_ClientAdresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresseComplete"));

        table_Client.getItems().addAll(persistance.getClientDAO().findAll());
    }

    public void createClient(ActionEvent actionEvent) { // Create a Client
        Client client = new Client(txt_ClientNom.getText(),txt_ClientPrenom.getText(),txt_ClientNVoie.getText(), txt_ClientVille.getText(),txt_ClientPays.getText(),txt_ClientVoie.getText(),txt_ClientZip.getText(), 1);
        persistance.getClientDAO().create(client);
    }

    public void changeClient(ActionEvent actionEvent) { // Modify the parameter of a Client
    }

    public void deleteClient(ActionEvent actionEvent) { // Delete a Client
        persistance.getClientDAO().delete(table_Client.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
        table_Client.getItems().removeAll(table_Client.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)
    }
}
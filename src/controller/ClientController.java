package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.Dao;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int idClient;

    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TableColumn<Client,Integer> clientIdTableColumn;
    @FXML
    private TableColumn<Client,String> clientNomTableColumn;
    @FXML
    private TableColumn<Client,String> clientPrenomTableColumn;
    @FXML
    private TableColumn<Client,String> clientAdresseTableColumn;

    @FXML private TextField getTxt_NomClient;

    @FXML private TextField getTxt_PrenomClient;

    @FXML private TextField getTxt_VoieClient;

    @FXML private TextField getTxt_NumVoieClient;

    @FXML private TextField getTxt_VilleClient;

    @FXML private TextField getTxt_PaysClient;

    @FXML private TextField getTxt_ZipClient;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientIdTableColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("Id"));
        clientNomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Nom"));
        clientPrenomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Prenom"));
        clientAdresseTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Adresse"));

    }


    private boolean verificationErreur() {
        String errorMessage = "";
        boolean noBlank = true;

        if (getTxt_NomClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un nom de Client. \n";
            noBlank = false;
        } else if (getTxt_NomClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            errorMessage = errorMessage + "Veillez à ne pas saisir de chiffres dans le nom du Client. \n";
            noBlank = false;
        }
        if (getTxt_PrenomClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un prénom de Client. \n";
            noBlank = false;
        } else if (getTxt_PrenomClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            errorMessage = errorMessage + "Veillez à ne pas saisir de chiffres dans le prénom du Client. \n";
            noBlank = false;
        }
        if (getTxt_NumVoieClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un numéro de rue. \n";
            noBlank = false;
        } else if (!(getTxt_NumVoieClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            errorMessage = errorMessage + "Saisir un nombre pour le numéro de rue\n";
            noBlank = false;
        }
        if (getTxt_VoieClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir une voie. \n";
            noBlank = false;
        } else if (getTxt_VoieClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            errorMessage = errorMessage + "Veillez à ne pas saisir de chiffres dans le nom d'une voie'. \n";
            noBlank = false;
        }
        if (getTxt_VilleClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un nom de Ville. \n";
            noBlank = false;
        } else if (getTxt_VilleClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            errorMessage = errorMessage + "Veillez à ne pas saisir de chiffres dans le nom d'une ville'. \n";
            noBlank = false;
        }
        if (getTxt_PaysClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un Pays. \n";
            noBlank = false;
        } else if (getTxt_PaysClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            errorMessage = errorMessage + "Veillez à ne pas saisir de chiffres dans le nom du Pays. \n";
            noBlank = false;
        }
        if (getTxt_ZipClient.getText().trim().equals("")) {
            errorMessage = errorMessage + "Veuillez saisir un code postal. \n";
            noBlank = false;
        }
        if (!(getTxt_ZipClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            errorMessage = errorMessage + "Saisir un nombre pour le Code Postal\n";
            noBlank = false;
        }
        if (!noBlank) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Erreur");
            dialog.setContentText(errorMessage);
            dialog.showAndWait();
        }
        return noBlank;
    }

    @FXML
    void creationClient(ActionEvent event) {
        if (verificationErreur()) {
            Client client = new Client(getTxt_NomClient.getText(), getTxt_PrenomClient.getText(), getTxt_NumVoieClient.getText(), getTxt_VilleClient.getText(), getTxt_PaysClient.getText(), getTxt_VoieClient.getText(), getTxt_ZipClient.getText(), idClient);
            DaoFactory.getClientDAO().create(client);
        }
    }
}

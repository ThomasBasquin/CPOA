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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.dao.Dao;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Client;
import model.normalize.ProcessAdresse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Client ClientToModify;
    @FXML
    public static Button btn_ClientMod;
    private DaoFactory persistance = ControllerAccueil.getPersistance();
    public static int index;

    public static int getIndex() {
       return index;
    }


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

    private boolean erreurClient() {

        String errorMessage = "Il y a des erreurs dans vos saisies :\n\n";
        boolean error = false;

        if (emptyFields(txt_ClientNom)) {
            errorMessage = errorMessage + "Veuillez saisir un nom !\n";
            error = true;
        } else if (noNumbers(txt_ClientNom)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autorise dans le nom !\n";
            error = true;
        }
        if (emptyFields(txt_ClientPrenom)) {
            errorMessage += "Veuillez saisir un prenom !\n";
            error = true;
        } else if (noNumbers(txt_ClientPrenom)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autorise pour la saisie du prenom !\n";
            error = true;
        }
        if (emptyFields(txt_ClientVoie)) {
            errorMessage = errorMessage + "Veuillez saisir une Voie !\n";
            error = true;
        } else if (noNumbers(txt_ClientVoie)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autorise pour la saisie d'une Voie !\n";
            error = true;
        }
        if (emptyFields(txt_ClientNVoie)) {
            errorMessage = errorMessage + "Veuillez saisir un numero de rue !\n";
            error = true;
        } else if (!noNumbers(txt_ClientNVoie)) {
            errorMessage = errorMessage + "Veillez a saisir un nombre dans numero rue !\n";
            error = true;
        }
        if (emptyFields(txt_ClientVille)) {
            errorMessage = errorMessage + "Veuillez saisir une Ville !\n";
            error = true;
        } else if (noNumbers(txt_ClientVille)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autorise pour la saisie d'une Ville !\n";
            error = true;
        }
        if (emptyFields(txt_ClientPays)) {
            errorMessage = errorMessage + "Veuillez saisir un Pays !\n";
            error = true;
        } else if (noNumbers(txt_ClientPays)) {
            errorMessage = errorMessage + "Aucun chiffre n'est autoriseé pour la saisie du Pays !\n";
            error = true;
        }
        if (emptyFields(txt_ClientZip)) {
            errorMessage = errorMessage + "Veuillez saisir un code Postal !\n";
            error = true;
        } else if (!noNumbers(txt_ClientZip)) {
            errorMessage = errorMessage + "Veillez a saisir un nombre dans le code postal !\n";
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

    public void createClient(ActionEvent actionEvent) { // Create a Client
        if (!erreurClient()) {
            Client client = new Client(txt_ClientNom.getText(), txt_ClientPrenom.getText(), txt_ClientNVoie.getText(), txt_ClientVille.getText(), txt_ClientPays.getText(), txt_ClientVoie.getText(), txt_ClientZip.getText(), 3);
            ProcessAdresse.normalize(client);
            persistance.getClientDAO().create(client);

            // actualized the table
            table_Client.getItems().clear();
            table_Client.getItems().addAll(persistance.getClientDAO().findAll());
        }
    }

    @FXML
    public void PageModifierClient(ActionEvent actionEvent) throws IOException { //switch to Periodicite's page

        if (table_Client.getSelectionModel().getSelectedItem() == null) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setResizable(false);
            dialog.setTitle("Information :");
            dialog.setContentText("Veuillez selectionnez un client a modifier dans le tableau!");
            dialog.showAndWait();
        } else {
            ClientToModify=table_Client.getSelectionModel().getSelectedItem();

            root = FXMLLoader.load(getClass().getResource("../vue/PageModifierClient.fxml"));
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

    @FXML
    public void importCSV() throws IOException {  // Import Csv File
        FileChooser chooseFile = new FileChooser();
        File fichierCsv = chooseFile.showOpenDialog(null);
        String line = "";
        try
        {
            FileReader fileReader = new FileReader(fichierCsv.getAbsolutePath());
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null)   //loops through every line until null found
            {
                String[] row = line.split(";");
                Client client = new Client(row[0],row[1],row[2],row[3],row[4],row[5],row[6],Integer.parseInt(row[7]));
                ProcessAdresse.normalize(client);
                persistance.getClientDAO().create(client);
                table_Client.getItems().addAll(client);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
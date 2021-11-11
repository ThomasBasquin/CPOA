package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.metier.Client;
import model.normalize.ProcessAdresse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.ControllerClient.ClientToModify;

public class ControllerModifierClient implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //initialize de textField
        txt_ClientNom.setText(ClientToModify.getNom());
        txt_ClientPrenom.setText(ClientToModify.getPrenom());
        txt_ClientVoie.setText(ClientToModify.getVoie());
        txt_ClientNVoie.setText(ClientToModify.getNoRue());
        txt_ClientVille.setText(ClientToModify.getVille());
        txt_ClientPays.setText(ClientToModify.getPays());
        txt_ClientZip.setText(ClientToModify.getCode_postal());
    }

    public void changeClient(ActionEvent actionEvent) throws IOException { // modify the client selectionned, and come back to PageClient
        Client client = new Client(txt_ClientNom.getText(),txt_ClientPrenom.getText(),txt_ClientNVoie.getText(), txt_ClientVille.getText(),txt_ClientPays.getText(),txt_ClientVoie.getText(),txt_ClientZip.getText(),ClientToModify.getId_client());
        ProcessAdresse.normalize(client);
        persistance.getClientDAO().update(client);

        root = FXMLLoader.load(getClass().getResource("../vue/PageClient.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void closeMod(ActionEvent actionEvent) throws IOException { //switch to client's page, without modify the client
        root = FXMLLoader.load(getClass().getResource("../vue/PageClient.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

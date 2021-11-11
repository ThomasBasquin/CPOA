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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static controllers.ControllerAbonnement.aboToModify;

public class ControllerModifierAbonnement implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private ChoiceBox<Client> cb_AboClient;
    @FXML
    private ChoiceBox<Revue> cb_AboRevue;
    @FXML
    private DatePicker dp_AboDebut;
    @FXML
    private DatePicker dp_AboFin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_AboClient.setItems(FXCollections.observableArrayList(persistance.getClientDAO().findAll()));
        cb_AboRevue.setItems(FXCollections.observableArrayList(persistance.getRevueDAO().findAll()));
    }

    public void changeAbo(ActionEvent actionEvent) throws IOException {
        LocalDate localDateDeb = dp_AboDebut.getValue();
        Instant instantDeb = Instant.from(localDateDeb.atStartOfDay(ZoneId.systemDefault()));
        Date dateDeb = Date.from(instantDeb);

        LocalDate localDateFin = dp_AboFin.getValue();
        Instant instantFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
        Date dateFin = Date.from(instantFin);

        Abonnement abonnement = new Abonnement(aboToModify.getId_abonnement(),dateDeb,dateFin,cb_AboClient.getValue().getId_client(),cb_AboRevue.getValue().getId_revue());
        persistance.getAbonnementDAO().update(abonnement);

        root = FXMLLoader.load(getClass().getResource("../vue/PageAbonnement.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModAbo(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../vue/PageAbonnement.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.metier.Periodicite;
import model.metier.Revue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.ControllerRevue.revueToModify;

public class ControllerModifierRevue implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory persistance = ControllerAccueil.getPersistance();


    @FXML
    private ChoiceBox<Periodicite> cb_RevuePerio;
    @FXML
    private TextArea txt_RevueDesc;
    @FXML
    private TextField txt_RevueTarif;
    @FXML
    private TextField txt_RevueTitre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_RevueTitre.setText(revueToModify.getTitre());
        txt_RevueDesc.setText(revueToModify.getDescription());
        txt_RevueTarif.setText(Float.toString(revueToModify.getTarif_numeros()));
        cb_RevuePerio.setItems(FXCollections.observableArrayList(persistance.getPeriodiciteDAO().findAll()));
    }

    public void changeRevue(ActionEvent actionEvent) throws IOException {
        Revue revue = new Revue(revueToModify.getId_revue(),cb_RevuePerio.getValue().getId(),txt_RevueDesc.getText(),Float.parseFloat(txt_RevueTarif.getText()),txt_RevueTitre.getText(),"");
        persistance.getRevueDAO().update(revue);

        root = FXMLLoader.load(getClass().getResource("../vue/PageRevue.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModRevue(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../vue/PageRevue.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

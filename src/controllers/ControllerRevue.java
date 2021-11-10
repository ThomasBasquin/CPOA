package controllers;

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
    private DaoFactory persistance = ControllerAccueil.getPersistance();

    @FXML
    private Button btn_RevueAccueil;
    @FXML
    private Button btn_RevueCreate;
    @FXML
    private Button btn_RevueMod;
    @FXML
    private Button btn_RevueSupp;
    @FXML
    private TableColumn<Revue, String> col_RevueDesc;
    @FXML
    private TableColumn<Revue, Integer> col_RevueID;
    @FXML
    private TableColumn<Revue, Integer> col_RevuePeriodicite;
    @FXML
    private TableColumn<Revue, Float> col_RevueTarif;
    @FXML
    private TableColumn<Revue, String> col_RevueTitre;
    @FXML
    private TableColumn<Revue, String> col_RevueVisuel;
    @FXML
    private TableView<Revue> table_Revue;
    @FXML
    private TextField txt_RevueDesc;
    @FXML
    private TextField txt_RevueTarif;
    @FXML
    private TextField txt_RevueTitre;
    @FXML
    private ChoiceBox<Integer> cb_RevuePerio;

    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.table_Revue.getItems().clear();
        col_RevueID.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_revue"));
        col_RevueTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        col_RevueDesc.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        col_RevueVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));
        col_RevueTarif.setCellValueFactory(new PropertyValueFactory<Revue, Float>("tarif_numeros"));
        col_RevuePeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_periodicite"));

        table_Revue.getItems().addAll(persistance.getRevueDAO().findAll());
    }

    public void createRevue(ActionEvent actionEvent) {
            Revue revue = new Revue(0, cb_RevuePerio.getValue(),txt_RevueDesc.getText(), Float.parseFloat(txt_RevueTarif.getText()), txt_RevueTitre.getText(), "");
            persistance.getRevueDAO().create(revue);
    }

    public void changeRevue(ActionEvent actionEvent) { // Modify the parameter
    }

    public void deleteRevue(ActionEvent actionEvent) { // Delete a Revue
        persistance.getRevueDAO().delete(table_Revue.getSelectionModel().getSelectedItem()); // delete the client in the Liste Memoire of the selectionned row
        table_Revue.getItems().removeAll(table_Revue.getSelectionModel().getSelectedItem()); //delete the selectionned row (visual)
    }
}


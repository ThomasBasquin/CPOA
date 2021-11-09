package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.factory.DaoFactory;
import model.dao.factory.Persistance;
import model.metier.Periodicite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPeriodicite {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DaoFactory dao = DaoFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);

    @FXML
    private TextField txt_PerioLibelle;

    @FXML
    private TableView table_Perio;

    @FXML
    private TableColumn <Periodicite, Integer>integerTableColumn;

    @FXML
    private TableColumn <Periodicite, String> stringTableColumn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        integerTableColumn.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("Id"));
        stringTableColumn.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("Libelle"));
        refresh();
    }

    private void refresh() {
        table_Perio.getItems().clear();
        this.table_Perio.getItems().addAll(dao.getPeriodiciteDAO().findAll());
    }

    public void PageAccueil(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/PageAccueil.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void addPeriodicite(ActionEvent actionEvent) {
        String errorMessage = "";
        boolean ok = true;

        if (txt_PerioLibelle.getText().trim().equalsIgnoreCase("")){
            errorMessage = "Veuillez saisir un libelle";
            ok = false;
        }
        else if (txt_PerioLibelle.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")){
            errorMessage = errorMessage + " Veuillez ne saisir que des lettres";
            ok = false;
        }

        if (ok) {
            Periodicite pe = new Periodicite(0,txt_PerioLibelle.getText());
            DaoFactory.getDAOFactory(Persistance.MYSQL).getPeriodiciteDAO().create(pe);
        }else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Problème");
            dialog.setHeaderText("Problème dans le libelle");
            dialog.setContentText(errorMessage);
            dialog.showAndWait();
        }
    }



}

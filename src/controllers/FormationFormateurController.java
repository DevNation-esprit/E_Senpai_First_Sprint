/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FormationService;
import services.ListData;

/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class FormationFormateurController implements Initializable {

    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private Button accueilBtn;
    @FXML
    private Button profilBtn;
    @FXML
    private Button formationsBtn;
    @FXML
    private Button test_quizBtn;
    @FXML
    private Button chatBtn;
    @FXML
    private Button blogBtn;
    @FXML
    private Button deconnectBtn;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TextField formationInput;
    @FXML
    private TableView<Formation> formationTable;
    @FXML
    private TableColumn<Formation, String> idColumn;
    @FXML
    private TableColumn<Formation, String> NomFormateurColumn;
    @FXML
    private TableColumn<Formation, String> titreColumn;
    @FXML
    private TableColumn<Formation, String> noteColumn;
    @FXML
    private TableColumn<Formation, String> descriptionColumn;
    @FXML
    private TableColumn<Formation, String> actionColumn;
    User currentUser;
    private ListData list = new ListData();
    @FXML
    private AnchorPane gestionUserPane;
    @FXML
    private Button chercherFormationBtn;
    @FXML
    private Button afficherBtn;
    @FXML
    private Button AjouterFormationbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleAccueilBtn(ActionEvent event) {
    }

    @FXML
    private void handleProfilBtn(ActionEvent event) {
    }

    @FXML
    private void HandleFormationsBtn(ActionEvent event) {
    }

    @FXML
    private void handleTestQuizBtn(ActionEvent event) {
    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
    }

    @FXML
    private void handleBlogBtn(ActionEvent event) {
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {
    }

    @FXML
    private void handleChercherBtn(ActionEvent event) {
        String texte = formationInput.getText();
        ObservableList<Formation> formationfiltre = FXCollections.observableArrayList();
        FormationService fr = FormationService.getInstance();
        formationfiltre = fr.getFormationUserByTitreObservable(texte, currentUser.getId().get());
        
        formationTable.setItems(formationfiltre);
        idColumn.setCellValueFactory(cell -> cell.getValue().getId().asString());
        NomFormateurColumn.setCellValueFactory(cell -> cell.getValue().getId_formateur().asString());
        titreColumn.setCellValueFactory(cell -> cell.getValue().getTitre());
        noteColumn.setCellValueFactory(cell -> cell.getValue().getNote().asString());
        descriptionColumn.setCellValueFactory(cell -> cell.getValue().getDescription());

    }

    public void initDataa(User u) {
        this.currentUser = u;
        FormationService fs = FormationService.getInstance();
         ObservableList<Formation> formation1 = FXCollections.observableArrayList(fs.getFormationByIdFormateur(u.getId().get())); 
        formationTable.setItems(formation1);
        idColumn.setCellValueFactory(cell -> cell.getValue().getId().asString());
        NomFormateurColumn.setCellValueFactory(cell -> cell.getValue().getId_formateur().asString());
        titreColumn.setCellValueFactory(cell -> cell.getValue().getTitre());
        noteColumn.setCellValueFactory(cell -> cell.getValue().getNote().asString());
        descriptionColumn.setCellValueFactory(cell -> cell.getValue().getDescription());
        Callback<TableColumn<Formation, String>, TableCell<Formation, String>> cellFactory
                = (param) -> {
                    final TableCell<Formation, String> cell = new TableCell<Formation, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button consulteButton = new Button("Modifier");
                        consulteButton.setOnAction(event -> {
                            Formation f = getTableView().getItems().get(getIndex());
                            System.out.println("wajdi");
                        });
                        setGraphic(consulteButton);
                        setText(null);
                    }
                }

            };

                    return cell; //To change body of generated lambdas, choose Tools | Templates.
                };
        actionColumn.setCellFactory(cellFactory);

    }

    @FXML
    private void handleAfficherBtn(ActionEvent event) {
    }

    @FXML
    private void handleUserTableMouseClicked(MouseEvent event) {
    }

    @FXML
    private void handleAjouterFormation(ActionEvent event) {
        try {
            FormationService fs = FormationService.getInstance();
            Formation f = new Formation();
            f.setId_formateur(currentUser.getId().get());
            f.setTitre("");
            f.setNote(0);
            f.setDescription("");
            fs.insertFormation(f);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AjouterFormation.fxml"));
            Parent page2 = loader.load();
            AjouterFormationController controller = loader.getController();
            controller.initDataa(currentUser,f);
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
              
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

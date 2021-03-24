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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FormationService;
import services.ListData;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class FormationController implements Initializable {

    private ListData list = new ListData();
    private ListData lists = new ListData();
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
    private Button deconnectBtn;
    @FXML
    private TableView<Formation> formationTable;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private Button blogBtn;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TextField formationInput;
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
    private TableColumn actionColumn;
    User currentUser;
    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane gestionUserPane;
    @FXML
    private Button chercherFormationBtn;
    @FXML
    private Button afficherBtn;
    @FXML
    private ImageView mesformationsbtn;
    @FXML
    private ScrollPane ScrollFormationId;
    @FXML
    private GridPane GridFormationId;
    @FXML
    private VBox Vfomation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FormationService fs = FormationService.getInstance();
        List<Formation> formations = fs.getAllFormation();
        UserService us = UserService.getInstance();
        List<User> users = us.getAllUsers();
        int column =0;
        int row = 1;
        try {

            for (Formation f : formations) {
                for (User u : users) {
                    if (u.getId().get() == f.getId_formateur().get()) {
                        FXMLLoader fXMLLoader = new FXMLLoader();
                        fXMLLoader.setLocation(getClass().getResource("/views/CardFormation.fxml"));
                        VBox vbox = fXMLLoader.load();
                        CardFormationController cardFormationController = fXMLLoader.getController();
                        cardFormationController.setData(f, u);
                        if (column == 3){
                            column=0;
                        ++row;
                    }
                    GridFormationId.add(vbox, column++, row);
                    GridPane.setMargin(vbox, new Insets(10));

                    }
                }
            }
        } catch (IOException e) {
        }

//        formationTable.setItems(list.getFormation());
//        idColumn.setCellValueFactory(cell -> cell.getValue().getId().asString());
//        NomFormateurColumn.setCellValueFactory(cell -> cell.getValue().getId_formateur().asString());
//        titreColumn.setCellValueFactory(cell -> cell.getValue().getTitre());
//        noteColumn.setCellValueFactory(cell -> cell.getValue().getNote().asString());
//        descriptionColumn.setCellValueFactory(cell -> cell.getValue().getDescription());
//        Callback<TableColumn<Formation, String>, TableCell<Formation, String>> cellFactory
//                = (param) -> {
//                    final TableCell<Formation, String> cell = new TableCell<Formation, String>() {
//
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setGraphic(null);
//                        setText(null);
//
//                    } else {
//                        final Button consulteButton = new Button("Consulter");
//                        consulteButton.setOnAction(event -> {
//                            Formation f = getTableView().getItems().get(getIndex());
//                            System.out.println("wajdi");
//                        });
//                        setGraphic(consulteButton);
//                        setText(null);
//                    }
//                }
//
//            };
//
//                    return cell; //To change body of generated lambdas, choose Tools | Templates.
//                };
//        actionColumn.setCellFactory(cellFactory);
    }

    @FXML
    private void handleAccueilBtn(ActionEvent event) {
    }

    @FXML
    private void handleTestQuizBtn(ActionEvent event) {
    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {
    }

    @FXML
    private void handleProfilBtn(ActionEvent event) {
    }

    @FXML
    private void HandleFormationsBtn(ActionEvent event) {
    }

    @FXML
    private void handleBlogBtn(ActionEvent event) {
    }

    @FXML
    private void handleChercherBtn(ActionEvent event) {
        String texte = formationInput.getText();
        ObservableList<Formation> formationfiltre = FXCollections.observableArrayList();
        FormationService fr = FormationService.getInstance();
        formationfiltre = fr.getFormationByTitreObservable(texte);

        formationTable.setItems(formationfiltre);
        idColumn.setCellValueFactory(cell -> cell.getValue().getId().asString());
        NomFormateurColumn.setCellValueFactory(cell -> cell.getValue().getId_formateur().asString());
        titreColumn.setCellValueFactory(cell -> cell.getValue().getTitre());
        noteColumn.setCellValueFactory(cell -> cell.getValue().getNote().asString());
        descriptionColumn.setCellValueFactory(cell -> cell.getValue().getDescription());
    }

    @FXML
    private void handleAfficherBtn(ActionEvent event) {
        FormationService fs = FormationService.getInstance();
        List<Formation> formation = fs.getAllFormation();
        formationTable.setItems(list.getFormation());
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
                        final Button consulteButton = new Button("Consulter");
                        consulteButton.setOnAction(event -> {
                            Formation f = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You have clicked \n" + f.getTitre() + "with id formateur \n" + f.getId_formateur());
                            alert.show();
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
    private void handleMesFormation(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/FormationFormateur.fxml"));
            Parent page2 = loader.load();
            FormationFormateurController controller = loader.getController();
            controller.initDataa(currentUser);
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initDataa(User u) {
        this.currentUser = u;
        welcomeLabel.setText("Bienvenue Mr " + currentUser.getNom().get());

    }

    @FXML
    private void handleUserTableMouseClicked(MouseEvent event) {
    }

}

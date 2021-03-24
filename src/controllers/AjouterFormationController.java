/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import entities.Slide;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FormationService;
import services.ListData;
import services.SlideService;

/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class AjouterFormationController implements Initializable {

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
    private Button btnAjouterSlide;
    @FXML
    private Button btnChoisirSlide;
    @FXML
    private TextField UrlSlide;
    User currentUser;
    private ListData list = new ListData();

    Formation formation;
    @FXML
    private Hyperlink annulerBtn;
    @FXML
    private TextField titreFormationId;
    @FXML
    private TextArea desreptionId;
    String fcs;
    @FXML
    private ChoiceBox<Integer> OrdreId;
    Formation f = new Formation();
    @FXML
    private TableView<?> SlideTable;
    @FXML
    private TableColumn<?, ?> UrlColumn;
    @FXML
    private TableColumn<?, ?> OrdreColumn;
    @FXML
    private TableColumn<?, ?> ActionColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desreptionId.setWrapText(true);
        OrdreId.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        
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
    private void handleAjouterSlide(ActionEvent event) {
        FormationService fs = FormationService.getInstance();
        f = fs.getFormationMaxByIdFormateur(currentUser.getId().get());
        SlideService ss = SlideService.getInstance();
        Slide s = new Slide();
        if (event.getSource() == btnAjouterSlide) {
            if (UrlSlide.getText().equals("") || OrdreId.getValue() == null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("INFORMATION INCOMPLETE");
                alert.setHeaderText(null);
                alert.setContentText("Compléter les informations pour ajouter un slide");
                alert.showAndWait();
            } else {
                int index = fcs.lastIndexOf('.');
                if (index > 0) {
                    String extension = fcs.substring(index + 1);

                    if (extension.equals("pdf")) {
                        s.setImage_slide("");
                        s.setVideo_slide("");
                        s.setText_slide(fcs);
                    } 
                    else if (extension.equals("mp4")) {
                        s.setImage_slide("");
                        s.setVideo_slide(fcs);
                        s.setText_slide("");
                    } else {
                        s.setImage_slide(fcs);
                        s.setVideo_slide("");
                        s.setText_slide("");
                    }
                    s.setId_formation(f.getId().get());
                    s.setOrdre(OrdreId.getValue());
                    ss.insertSlide(s);
                }

            }

        }

    }

    @FXML
    private void handleChoisirSlide(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", " *.pdf"), new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"), new FileChooser.ExtensionFilter("Videos", "*.mp4"));
        File SelectedFile = fc.showOpenDialog(null);
        SlideService ss = SlideService.getInstance();
        Slide s = new Slide();

        if (SelectedFile != null) {
            UrlSlide.setText(SelectedFile.getAbsolutePath());
            fcs = SelectedFile.toString();

        }
    }

    public void initDataa(User u, Formation f) {
        this.currentUser = u;
        this.formation = f;
    }

    @FXML
    private void handleAnnulerBtn(ActionEvent event) {
        FormationService fs = FormationService.getInstance();
        f = fs.getFormationMaxByIdFormateur(currentUser.getId().get());
        SlideService ss =SlideService.getInstance();

        if (event.getSource() == annulerBtn) {
            ss.DeleteSlide(f.getId().get());
            fs.DeleteFormation(f.getId().get(), currentUser.getId().get());
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
    }
    private void update (ActionEvent event){
//         this.currentUser = u;
        FormationService fs = FormationService.getInstance();
//        ObservableList<Slide> slide = FXCollections.observableArrayList(fs.getFormationByIdFormateur(u.getId().get())); 
//        SlideTable.setItems(slide);
//        idColumn.setCellValueFactory(cell -> cell.getValue().getId().asString());
//        NomFormateurColumn.setCellValueFactory(cell -> cell.getValue().getId_formateur().asString());
//        titreColumn.setCellValueFactory(cell -> cell.getValue().getTitre());
//        noteColumn.setCellValueFactory(cell -> cell.getValue().getNote().asString());
//        descriptionColumn.setCellValueFactory(cell -> cell.getValue().getDescription());
       

        
    }
}

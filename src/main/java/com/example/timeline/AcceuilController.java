package com.example.timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcceuilController {

    Stage mainStage;

    @FXML
    void onClickCollection(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Collection.fxml"));
        Scene collectionScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Collection");
        mainStage.setScene(collectionScene);
        CollectionController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickJouer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ParametrePartie.fxml"));
        Scene ParametrePartieScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("ParametrePartie");
        mainStage.setScene(ParametrePartieScene);
        ParametrePartieController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickParametre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Parametre.fxml"));
        Scene parametreScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Parametre");
        mainStage.setScene(parametreScene);
        ParametreController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickRegle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Regles.fxml"));
        Scene reglesScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Regles");
        mainStage.setScene(reglesScene);
        ReglesController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickReprendre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Jouer.fxml"));
        Scene jouerScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Jeux");
        mainStage.setScene(jouerScene);
        JouerController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }
}
package com.example.timeline.controller;

import com.example.timeline.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcceuilController {

    public Stage mainStage;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    void onClickCollection(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Collection.fxml"));
        Scene collectionScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Collection");
        mainStage.setScene(collectionScene);
        CollectionController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickJouer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ParametrePartie.fxml"));
        Scene ParametrePartieScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("ParametrePartie");
        mainStage.setScene(ParametrePartieScene);
        ParametrePartieScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        ParametrePartieController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickParametre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Parametre.fxml"));
        Scene parametreScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Parametre");
        mainStage.setScene(parametreScene);
        ParametreController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickRegle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Regles.fxml"));
        Scene reglesScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Regles");
        mainStage.setScene(reglesScene);
        ReglesController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    @FXML
    void onClickReprendre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Jouer.fxml"));
        Scene jouerScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Jeux");
        mainStage.setScene(jouerScene);
        JouerController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }
}
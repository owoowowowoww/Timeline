package com.example.timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ParametrePartieController {
    private static final int NB_JOUEUR = 1;
    private int nbJoueur;
    public Stage mainStage;

    @FXML
    private TextField timeAnswer;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    void onClickSetNbJoueur(ActionEvent event) {
        Button sourceButton = (Button)event.getSource();
        String val = sourceButton.getText();
        if(val.equals("1 joueur")){
            nbJoueur = 1;
        }
        if(val.equals("2 joueurs")){
            nbJoueur = 2;
        }
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    @FXML
    void onClickJouer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Jouer.fxml"));
        Scene jouerScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Jeux");
        mainStage.setScene(jouerScene);
        JouerController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    public int getTimeAnswer(){
        return Integer.parseInt(timeAnswer.getText());
    }
}

package com.example.timeline;

import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import com.example.timeline.controller.AcceuilController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Acceuil.fxml"));
        Parent root = fxmlLoader.load();
        AcceuilController controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 950, 635);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        controller.mainStage = stage;
        stage.show();

    }

    public static void main(String[] args) {



        Deck.load("data/data.json");
        Deck jeuDeCartes = new Deck("Test");
        Deck jeuDeCartes2 = new Deck("Test2");


        try {

            for (Card carte : jeuDeCartes.getCards()) {
                System.out.println(carte);
            }



            Card nCarte = new Card(
                    "Carte n°4",
                    2006,
                    "",
                    "Description de la carte n°4"
            );

            jeuDeCartes2.addCarte(nCarte);

            Deck.save("data/data.json");

        } catch (Exception e) {
            e.printStackTrace();
        }

        launch();
    }
}
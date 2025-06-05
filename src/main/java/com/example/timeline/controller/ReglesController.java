package com.example.timeline.controller;

import com.example.timeline.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;



public class ReglesController {
    Stage mainStage;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }
    @FXML
    private TextFlow regleFlow;

    @FXML
    public void initialize() {
        regleFlow.getChildren().addAll(
                new Text("🟢 Début du jeu\n") {{
                    getStyleClass().add("regle-section-title");
                }},
                new Text("Chaque joueur reçoit 4 cartes tirées au hasard.\nUne carte est placée au centre de la frise : c’est le point de départ temporel.\nLes cartes sont affichées sans leur date. Tu devras deviner leur place !\n\n") {{
                    getStyleClass().add("regle-texte");
                }},
                new Text("🔄 Ton tour de jeu\n") {{
                    getStyleClass().add("regle-section-title");
                }},
                new Text("Choisis une carte de ta main.\nMets-la dans la frise chronologique à l’endroit où tu penses que l’événement s’est produit.\n✅ Si la position est correcte : la carte est placée définitivement.\n❌ Si c’est faux : la carte est défaussée et tu reçois une nouvelle.\n\n") {{
                    getStyleClass().add("regle-texte");
                }},
                new Text("🏁 Fin de partie\n") {{
                    getStyleClass().add("regle-section-title");
                }},
                new Text("Le jeu continue jusqu’à ce qu’un joueur n’ait plus aucune carte en main.\nIl ou elle est déclaré·e vainqueur !") {{
                    getStyleClass().add("regle-texte");
                }}
        );
    }


    @FXML
    void onClickRetour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Acceuil.fxml"));
        Scene acceuilScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Acceuil");
        mainStage.setScene(acceuilScene);
        acceuilScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        AcceuilController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }
}

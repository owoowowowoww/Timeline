package com.example.timeline.view;

import java.io.IOException;

import com.example.timeline.controller.CardOnHandController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardViewOnBoard extends VBox implements CardView {

    private Label cardTitle;
    private ImageView cardImage;

    public CardViewOnBoard(CardOnHandController controller) {
        super();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timeline/CardView.fxml"));
        try {
            Parent root = loader.load();
            root.setScaleX(0.8);
            root.setScaleY(0.8);

            this.setAlignment(Pos.CENTER);
            this.getChildren().add(root);

            cardTitle = (Label) root.lookup("#title");
            cardImage = (ImageView) root.lookup("#image");

            // Initialise l'affichage
            controller.setView(this);
            controller.initView();

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte (CardViewOnBoard)");
            e.printStackTrace();
        }
    }

    @Override
    public void setTitle(String text) {
        cardTitle.setText(text);
    }

    @Override
    public void setCardImage(Image image) {
        cardImage.setImage(image);
    }
}

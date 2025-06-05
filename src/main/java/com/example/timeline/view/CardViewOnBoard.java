package com.example.timeline.view;

import java.io.IOException;

import com.example.timeline.controller.CardOnBoardController;
import com.example.timeline.controller.CardOnHandController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardViewOnBoard extends VBox implements CardView {

    private CardOnBoardController boardController;
    private CardOnHandController handController;

    private Label cardTitle;
    private ImageView cardImage;
    private Label cardDate;

    public CardViewOnBoard(CardOnBoardController controller) {
        this(controller, false);
    }

    // Pour hand
    public CardViewOnBoard(CardOnHandController controller) {
        this(controller, false);
    }

    public CardViewOnBoard(CardOnBoardController controller, boolean cardIsSelected) {
        this.boardController = controller;
        init(cardIsSelected);
    }

    public CardViewOnBoard(CardOnHandController controller, boolean cardIsSelected) {
        this.handController = controller;
        init(cardIsSelected);
    }

    private void init(boolean cardIsSelected) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timeline/CardView.fxml"));
        try {
            Parent root = loader.load();
            root.setScaleX(cardIsSelected ? 1.0 : 0.8);
            root.setScaleY(cardIsSelected ? 1.0 : 0.8);

            this.setAlignment(Pos.CENTER);
            this.getChildren().add(root);

            cardTitle = (Label) root.lookup("#title");
            cardImage = (ImageView) root.lookup("#image");
            cardDate = (Label) root.lookup("#date");

            if (boardController != null) {
                boardController.setView(this);
                boardController.initView();
            } else if (handController != null) {
                handController.setView(this);
                handController.initView();
            }

            this.setOnMouseClicked(_ -> selection());

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

    public void setCardDate(int date) {
        cardDate.setText("Date : " + date);
    }

    private void selection() {
        if (boardController != null) {
            boardController.selectActionDescription();
        } else if (handController != null) {
            handController.selectAction();
        }
    }

}

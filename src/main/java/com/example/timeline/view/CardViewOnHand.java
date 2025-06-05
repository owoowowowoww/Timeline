package com.example.timeline.view;

import java.io.IOException;

import com.example.timeline.controller.CardOnHandController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CardViewOnHand extends VBox {

    private CardOnHandController controller;

    private ImageView cardImage;
    private Label cardTitle;

    public CardViewOnHand(CardOnHandController controller, boolean cardIsSelected) {
        super();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/timeline/CardView.fxml"));
        try {
            this.controller = controller;
            Parent root = loader.load();
            root.setScaleX(0.8);
            root.setScaleY(0.8);
            controller.setView(this);
            cardTitle = ((Label) root.lookup("#title"));
            cardImage = ((ImageView) root.lookup("#image"));
            this.setAlignment(Pos.CENTER);

            if (cardIsSelected) {
                root.setScaleX(1.);
                root.setScaleY(1.);
            }

            this.getChildren().add(root);
            controller.initView();

            this.setOnMouseClicked(_ -> {
                selection();
            });

            this.setOnDragDetected(event -> {
                Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString("card");
                db.setContent(content);

                SnapshotParameters snapParams = new SnapshotParameters();
                snapParams.setFill(Color.TRANSPARENT);
				db.setDragView(
						this.snapshot(snapParams, null),
						event.getX(),
						event.getY()
				);

				event.consume();
            });

            this.setOnDragDone(event -> {
                if (!event.isDropCompleted()) {
                    this.setVisible(true);
                }
            });

        } catch (IOException e) {
            System.err.println("Problem while loading the card fxml");
        }
    }


    private void selection() {
        controller.selectAction();
    }

    public void setTitle(String text) {
        cardTitle.setText(text);
    }

    public void setCardImage(Image image) {
        cardImage.setImage(image);
    }

}

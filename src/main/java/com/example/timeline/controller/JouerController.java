package com.example.timeline.controller;

import com.example.timeline.Main;
import com.example.timeline.board.PileOfCards;
import com.example.timeline.board.Player;
import com.example.timeline.board.Game;
import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import com.example.timeline.view.CardViewOnBoard;
import com.example.timeline.view.CardViewOnHand;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JouerController {

    public Stage mainStage;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    private Label scoreLabel;

    @FXML
    private Label titreDeck;

    @FXML
    private Label timeWatch;

    @FXML
    private HBox playerHand;

    @FXML
    private HBox board;

    @FXML
    private ScrollPane scrollPane;

    private Game model;

    private Timeline chrono;

    private Card selectedCard;

    private final Region dropPreview = new Region();

    private int nbJoueur = 1;

    private int seconds = 10;

    private int initialSeconds;

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        initialSeconds = seconds;
        timeWatch.setText(Integer.toString(seconds));
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }


    public JouerController() {
        super();
    }

    public void initialization() {
        initUI();
        Deck deck = new Deck("Les langages de programmation");
        List<Player> players = new ArrayList<>();
        Player player = new Player("Manu");
        players.add(player);
        model = new Game(players, deck, 3);
        initUIFromModel();
        dropPreview.setStyle("-fx-border-color: #892cb0; -fx-border-width: 3; -fx-background-color: rgba(255,255,255,0.2);");
        dropPreview.setMinSize(70, 100);
        dropPreview.setMaxSize(70, 200);
        setupBoardDropZone();

    }

    private void initUI() {
        playerHand.getChildren().clear();
        selectedCard = null;
    }

    private void initUIFromModel() {
        titreDeck.setText(model.getDeck().getTitle());
        playerHand.getChildren().clear();
        displayPlayerHand();
        displayBoard();
    }

    private void refresh() {
        Platform.runLater(() -> {
            initUIFromModel();
            updateScore();
        });
    }

    private void updateScore() {
        if (model != null && scoreLabel != null) {
            int score = model.getScore();
            scoreLabel.setText("Score : " + score);
        }
    }

    private void displayPlayerHand() {
        PileOfCards hand = model.getPlayerHand();
        for (Card aCard : hand.getPileOfCards()) {
            CardOnHandController controller = new CardOnHandController(aCard, this);
            CardViewOnHand view = new CardViewOnHand(controller, aCard.equals(selectedCard));

            playerHand.getChildren().add(view);
        }
    }

    private void displayBoard() {
        List<Card> boardCards = model.getTimeline();
        board.getChildren().clear(); // important !

        for (Card aCard : boardCards) {
            CardOnHandController controller = new CardOnHandController(aCard, this);
            CardViewOnBoard view = new CardViewOnBoard(controller);
            board.getChildren().add(view);
        }
    }

    public void newGameAction() {
        initialization();
    }

    public void setCardSelected(Card controlledCard) {
        selectedCard = controlledCard;
        playerHand.getChildren().clear();
        displayPlayerHand();
    }


    private void setupBoardDropZone() {
        board.setOnDragOver(event -> {
            if (event.getGestureSource() != board && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
                int position = calculateDropPosition(event.getX());
                board.getChildren().remove(dropPreview);
                if (position >= 0 && position <= board.getChildren().size()) {
                    board.getChildren().add(position, dropPreview);
                }
            }
            event.consume();
        });

        board.setOnDragExited(event -> {
            board.getChildren().remove(dropPreview);
        });

        board.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && selectedCard != null) {
                int position = calculateDropPosition(event.getX());
                model.playTurn(selectedCard, position);
                selectedCard = null;
                success = true;
                refresh();
            }
            board.getChildren().remove(dropPreview);
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private int calculateDropPosition(double mouseX) {
        int position = 0;
        for (int i = 0; i < board.getChildren().size(); i++) {
            var node = board.getChildren().get(i);
            if (node == dropPreview) continue;
            double nodeX = node.getLayoutX();
            double nodeWidth = node.getBoundsInParent().getWidth();
            double nodeCenter = node.localToParent(node.getBoundsInLocal()).getMinX() + nodeWidth / 2;
            if (mouseX < nodeCenter) {
                return position;
            }
            position++;
        }
        return position;
    }

    @FXML
    public void initialize() {
        startTimeWatch();
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double deltaY = event.getDeltaY();
            double speed = 5;

            scrollPane.setHvalue(scrollPane.getHvalue() - (deltaY * speed) / scrollPane.getWidth());
            event.consume();
        });
    }


    //Gestion du timer

    private void updateChrono() {
        seconds--;

        if (seconds == 0) {
            timeWatch.setStyle("-fx-text-fill: white");
            timeWatch.setText("STOP");
            if (nbJoueur == 1) {
                openWindowsWinner();
                chrono.stop();
            } else {
                doFadeOutEnding();
            }
        } else if (seconds == 3) {
            timeWatch.setStyle("-fx-text-fill: red");
            timeWatch.setText(String.format("%02d", seconds));
        } else if (seconds <= 3) {
            timeWatch.setText(String.format("%02d", seconds));
        } else {
            timeWatch.setStyle("-fx-text-fill: white");
            timeWatch.setText(String.format("%02d", seconds));
        }
    }

    private void openWindowsWinner() {
        try {
            Stage winnerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("winner.fxml"));
            Scene winnerScene = new Scene(fxmlLoader.load());
            WinnerController controller = fxmlLoader.getController();
            winnerStage.setScene(winnerScene);
            winnerStage.initModality(Modality.WINDOW_MODAL);
            winnerStage.initOwner(mainStage);
            winnerStage.show();
            winnerScene.getStylesheets().add(Main.class.getResource("styleAcceuil.css").toString());
            controller.mainStage = winnerStage;
        } catch (IOException e) {
            System.err.println("Problem while loading the card fxml");
        }
    }

    private void doFadeOutEnding() {
        chrono.stop();
        PauseTransition showMessageTime = new PauseTransition(Duration.seconds(3));
        showMessageTime.setOnFinished(e -> {
            seconds = initialSeconds;
            timeWatch.setStyle("-fx-text-fill: white");
            timeWatch.setText(String.format("%02d", seconds));
            startTimeWatch();
        });
        showMessageTime.play();
    }

    private void startTimeWatch() {
        chrono = new javafx.animation.Timeline(new KeyFrame(Duration.seconds(1), e -> updateChrono()));
        chrono.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        chrono.play();
    }

}

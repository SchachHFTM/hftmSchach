package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Square;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Piece extends ImageView {
    private Color color;
    public String type;
    public int x;
    public int y;
    ArrayList<String> possibleMoves;

    public Piece(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        addEventHandler();
    }

    private void addEventHandler() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                checkPossibleMoves();
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void checkPossibleMoves() {
    }

    public Square getSquareByName(String name) {

        // TODO: Richtig implementieren
        /*
         * for (Square square : GameController) {
         * if (square.name.equals(name)) {
         * return square;
         * }
         * }
         */
        return new Square(1, 1);
    }
}

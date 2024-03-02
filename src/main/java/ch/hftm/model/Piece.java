package ch.hftm.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView implements Serializable {
    protected EColorPiece color;
    public String type;
    public int x;
    public int y;
    public ArrayList<String> possibleMoves;

    // Constructor
    public Piece(EColorPiece color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Getter and Setter
    public void setPiece(Image image) {
        this.setImage(image);
    }

    public void setImage() {
        String imagePath = "/pieces/" + this.color + this.type + ".png";
        Class<? extends Piece> obj = getClass();
        InputStream inputStream = obj.getResourceAsStream(imagePath);
        // InputStream inputStream = getClass().getResourceAsStream(imagePath);

        try {
            if (inputStream != null) {
                this.setPiece(new Image(inputStream));
            } else {
                System.out.println("Image could not be loaded:" + imagePath);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid image path: " + imagePath);
        }
    }

    public EColorPiece getColor() {
        return color;
    }

    public void setColorPiece(EColorPiece color) {
        this.color = color;
    }

    public int getPieceX() {
        return x;
    }

    public int getPieceY() {
        return y;
    }

    public Square getSquareByName(String name, ArrayList<Square> squares) {
        for (Square square : squares) {
            if (square.getName().equals(name)) {
                return square;
            }
        }

        return null;
    }

    public Piece getPieceByName(String name, ArrayList<Square> squares) {
        for (Square square : squares) {
            if (square.getName().equals(name)) {
                return square.getPiece();
            }
        }

        return null;
    }

    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        return possibleMoves;
    }

    public boolean isOpponentPiece(int x, int y, ArrayList<Square> squares) {
        Piece piece = getPieceByName(Coordinates.fromCoordinatesToNotation(x, y), squares);
        if (piece != null) {
            return piece.getColor() != this.getColor();
        }
        return false;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

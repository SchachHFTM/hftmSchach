package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import javafx.scene.paint.Color;

public class King extends Piece {

    public King(Color color, int x, int y) {
        super(color, x, y);
        this.type = "King";
    }

    @Override
    public void checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add(Coordinates.fromCoordinates(x, y - 1).getNotation());
        moves.add(Coordinates.fromCoordinates(x + 1, y - 1).getNotation());
        moves.add(Coordinates.fromCoordinates(x + 1, y).getNotation());
        moves.add(Coordinates.fromCoordinates(x + 1, y + 1).getNotation());
        moves.add(Coordinates.fromCoordinates(x, y + 1).getNotation());
        moves.add(Coordinates.fromCoordinates(x - 1, y + 1).getNotation());
        moves.add(Coordinates.fromCoordinates(x - 1, y).getNotation());
        moves.add(Coordinates.fromCoordinates(x - 1, y - 1).getNotation());

        for (String move : moves) {
            if (getSquareByName(move) != null) {
                // TODO: getPiecesByName Methodik
                if (getSquareByName(move).occupied) {
                    possibleMoves.add(move);
                }

            }
        }

    }

}

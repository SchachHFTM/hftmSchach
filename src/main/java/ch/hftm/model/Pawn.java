package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;

public class Pawn extends Piece {

    // Constructor
    public Pawn(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Pawn";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        int direction = (getColor() == EColorPiece.WHITE) ? 1 : -1;

        // Forward move
        int forwardX = x;
        int forwardY = y + direction;
        Square forwardSquare = getSquareByName(Coordinates.convertToNotation(forwardX, forwardY), squares);
        if (forwardSquare != null && !forwardSquare.occupied) {
            possibleMoves.add(forwardSquare.getName());
        }

        // Initial two-square move
        if ((direction == 1 && y == 6) || (direction == -1 && y == 1)) {
            int doubleForwardY = y + 2 * direction;
            Square doubleForwardSquare = getSquareByName(Coordinates.convertToNotation(x, doubleForwardY), squares);
            if (doubleForwardSquare != null && !doubleForwardSquare.occupied && !forwardSquare.occupied) {
                possibleMoves.add(doubleForwardSquare.getName());
            }
        }

        // Diagonal capture
        int[] captureX = { x - 1, x + 1 };
        for (int x : captureX) {
            int diagonalY = y + direction;
            Square diagonalSquare = getSquareByName(Coordinates.convertToNotation(x, diagonalY), squares);
            if (diagonalSquare != null && diagonalSquare.occupied
                    && diagonalSquare.getPiece().getColor() != getColor()) {
                possibleMoves.add(diagonalSquare.getName());
            }
        }

        return possibleMoves;
    }

}
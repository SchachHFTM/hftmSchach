package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Pawn extends Piece {

    // Constructor
    public Pawn(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Pawn";

    }

    @Override
    public ArrayList<String> checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        this.possibleMoves = new ArrayList<>();

        int direction = (color == EColorPiece.BLACK) ? 1 : -1;

        // Check one square forward
        String forwardMove = Coordinates.fromCoordinatesToNotation(x, y - direction);
        if (getSquareByName(forwardMove) != null && !getSquareByName(forwardMove).occupied) {
            possibleMoves.add(forwardMove);

            // Check two squares forward if pawn is in starting position
            if ((color == EColorPiece.BLACK && y == 6) || (color == EColorPiece.WHITE && y == 1)) {
                String doubleForwardMove = Coordinates.fromCoordinatesToNotation(x, y - 2 * direction);
                if (getSquareByName(doubleForwardMove) != null && !getSquareByName(doubleForwardMove).occupied) {
                    possibleMoves.add(doubleForwardMove);
                }
            }
        }

        // Check diagonal captures
        if (x + 1 < 8) {
            String rightDiagonalMove = Coordinates.fromCoordinatesToNotation(x + 1, y - direction);
            if (getSquareByName(rightDiagonalMove) != null && getSquareByName(rightDiagonalMove).occupied) {
                possibleMoves.add(rightDiagonalMove);
            }
        }
        if (x - 1 >= 0) {
            String leftDiagonalMove = Coordinates.fromCoordinatesToNotation(x - 1, y - direction);
            if (getSquareByName(leftDiagonalMove) != null && getSquareByName(leftDiagonalMove).occupied) {
                possibleMoves.add(leftDiagonalMove);
            }
        }
        System.out.println(possibleMoves);
        return possibleMoves;
    }

}
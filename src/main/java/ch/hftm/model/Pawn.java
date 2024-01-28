package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Pawn extends Piece {

    //Constructor
    public Pawn(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Pawn";

    }

    public void checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        this.possibleMoves = new ArrayList<>();

        int direction = (color == EColorPiece.BLACK) ? 1 : -1;

        // Check one square forward
        String forwardMove = Coordinates.fromCoordinatesToNotation(x, y + direction);
        if (getSquareByName(forwardMove) != null && !getSquareByName(forwardMove).occupied) {
            possibleMoves.add(forwardMove);

            // Check two squares forward if pawn is in starting position
            if ((color == EColorPiece.BLACK && y == 1) || (color == EColorPiece.WHITE && y == 6)) {
                String doubleForwardMove = Coordinates.fromCoordinatesToNotation(x, y + 2 * direction);
                if (getSquareByName(doubleForwardMove) != null && !getSquareByName(doubleForwardMove).occupied) {
                    possibleMoves.add(doubleForwardMove);
                }
            }
        }

        // Check diagonal captures
        String rightDiagonalMove = Coordinates.fromCoordinatesToNotation(x + 1, y + direction);
        String leftDiagonalMove = Coordinates.fromCoordinatesToNotation(x - 1, y + direction);

        if (getSquareByName(rightDiagonalMove) != null && getSquareByName(rightDiagonalMove).occupied) {
            possibleMoves.add(rightDiagonalMove);
        }

        if (getSquareByName(leftDiagonalMove) != null && getSquareByName(leftDiagonalMove).occupied) {
            possibleMoves.add(leftDiagonalMove);
        }
    }

    // @Override
    // public void checkPossibleMoves() {
    //     int x = this.x;
    //     int y = this.y;
    //     ArrayList<String> moves = new ArrayList<>();
    //     this.possibleMoves = new ArrayList<>();
    //     if (color == EColorPiece.BLACK) {
    //         if (getSquareByName(Coordinates.fromCoordinatesToNotation(x, y + 1)).occupied) {
    //             moves.add(Coordinates.fromCoordinatesToNotation(x, y + 1));
    //         }
    //         moves.add(Coordinates.fromCoordinatesToNotation(x + 1, y + 1));
    //         moves.add(Coordinates.fromCoordinatesToNotation(x - 1, y + 1));

    //         if (y == 1) {
    //             if (getSquareByName(Coordinates.fromCoordinatesToNotation(x, y + 2)).occupied) {
    //                 moves.add(Coordinates.fromCoordinatesToNotation(x, y + 2));
    //             }
    //         }

    //     } else if (color == EColorPiece.WHITE) {
    //         if (getSquareByName(Coordinates.fromCoordinatesToNotation(x, y + 1)).occupied) {
    //             moves.add(Coordinates.fromCoordinatesToNotation(x, y - 1));
    //         }
    //         moves.add(Coordinates.fromCoordinatesToNotation(x + 1, y - 1));
    //         moves.add(Coordinates.fromCoordinatesToNotation(x - 1, y - 1));
    //         if (y == 6) {
    //             if (getSquareByName(Coordinates.fromCoordinatesToNotation(x, y - 2)).occupied) {
    //                 moves.add(Coordinates.fromCoordinatesToNotation(x, y - 2));
    //             }
    //         }

    //     }

    //     Coordinates.fromCoordinatesToNotation(x + 2, y + 1);

    //     for (String move : moves) {
    //         if (getSquareByName(move) != null) {
    //             // TODO: getPiecesByName Methodik
    //             if (getSquareByName(move).occupied) {
    //                 possibleMoves.add(move);
    //             }

    //         }
    //     }

    // }

}

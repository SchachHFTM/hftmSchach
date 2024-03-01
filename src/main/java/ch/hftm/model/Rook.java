package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;

public class Rook extends Piece {

    // Constructor
    public Rook(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Rook";

    }

    @Override
    public ArrayList<String> checkPossibleMoves() {
        this.possibleMoves = new ArrayList<>();

        int[][] rookMoves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] move : rookMoves) {
            int dx = move[0];
            int dy = move[1];

            for (int i = 1; i < 8; i++) {
                int newX = x + dx * i;
                int newY = y + dy * i;

                if (!isValidPosition(newX, newY)) {
                    break;
                }

                Square square = getSquareByName(Coordinates.fromCoordinatesToNotation(newX, newY));

                if (square.occupied) {
                    if (isOpponentPiece(newX, newY)) {
                        possibleMoves.add(Coordinates.fromCoordinatesToNotation(newX, newY));
                    }
                    break;
                }

                possibleMoves.add(Coordinates.fromCoordinatesToNotation(newX, newY));

            }
        }
        return possibleMoves;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private boolean isOpponentPiece(int x, int y) {
        Piece piece = getPieceByName(Coordinates.fromCoordinatesToNotation(x, y));
        if (piece != null) {
            return piece.getColor() != this.getColor();
        }
        return false;
    }
}

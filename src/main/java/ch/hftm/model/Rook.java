package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Rook extends Piece {

    // Constructor
    public Rook(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Rook";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
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

                Square square = getSquareByName(Coordinates.fromCoordinatesToNotation(newX, newY), squares);
                if (square.occupied) {
                    if (isOpponentPiece(newX, newY, squares)) {
                        possibleMoves.add(square.getName());
                    }
                    break;
                }

                possibleMoves.add(Coordinates.fromCoordinatesToNotation(newX, newY));

            }
        }
        return possibleMoves;
    }
}

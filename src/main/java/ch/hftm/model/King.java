package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;

public class King extends Piece {
    // Constructor
    public King(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "King";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        int[][] kingMoves = {
                { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 },
                { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }
        };

        for (int[] move : kingMoves) {
            int dx = move[0];
            int dy = move[1];
            int newX = x + dx;
            int newY = y + dy;

            Square targetSquare = getSquareByName(Coordinates.convertToNotation(newX, newY), squares);
            if (targetSquare != null && (isOpponentPiece(newX, newY, squares) || !targetSquare.occupied)) {
                possibleMoves.add(targetSquare.getName());
            }
        }

        return possibleMoves;
    }
}

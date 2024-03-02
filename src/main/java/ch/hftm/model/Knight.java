package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;

public class Knight extends Piece {

    // Constructor
    public Knight(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Knight";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        int[][] knightMoves = {
                { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 },
                { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }
        };

        for (int[] move : knightMoves) {
            int dx = move[0];
            int dy = move[1];
            int newX = x + dx;
            int newY = y + dy;

            Square targetSquare = getSquareByName(Coordinates.convertToNotation(newX, newY), squares);

            if (targetSquare != null && (!targetSquare.occupied || isOpponentPiece(newX, newY, squares))) {
                possibleMoves.add(targetSquare.getName());
            }
        }

        return possibleMoves;
    }
}

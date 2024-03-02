package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Bishop extends Piece {

    // Constructor
    public Bishop(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Bishop";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        int[][] bishopMoves = {
                { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }
        };

        for (int[] move : bishopMoves) {
            int dx = move[0];
            int dy = move[1];

            int newX = x + dx;
            int newY = y + dy;

            while (isValidPosition(newX, newY)) {
                Square targetSquare = getSquareByName(Coordinates.convertToNotation(newX, newY), squares);
                if (!targetSquare.occupied) {
                    possibleMoves.add(targetSquare.getName());
                } else if (isOpponentPiece(newX, newY, squares)) {
                    possibleMoves.add(targetSquare.getName());
                    break;
                } else {
                    break;
                }

                newX += dx;
                newY += dy;
            }
        }

        return possibleMoves;
    }

}

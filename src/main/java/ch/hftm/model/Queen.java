package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Queen extends Piece {

    // Constructor
    public Queen(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Queen";

    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        // Define the directions in which a queen can move: horizontally, vertically,
        // and diagonally
        int[][] directions = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, // Horizontal and vertical
                { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } // Diagonal
        };

        // Iterate over all possible directions
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            // Iterate over the squares in the direction until we reach the board edge or an
            // occupied square
            for (int i = 1; i < 8; i++) {
                int newX = x + dx * i;
                int newY = y + dy * i;

                // Check if the new position is valid and on the board
                if (!isValidPosition(newX, newY)) {
                    break;
                }

                // Check if the square at the new position is occupied
                Square square = getSquareByName(Coordinates.fromCoordinatesToNotation(newX, newY), squares);
                if (square.occupied) {
                    // If the square is occupied by an opponent's piece, add it to possible moves
                    if (isOpponentPiece(newX, newY, squares)) {
                        possibleMoves.add(square.getName());
                    }
                    // Break the loop because we can't move past an occupied square
                    break;
                }

                // If the square is not occupied, add it to possible moves
                possibleMoves.add(Coordinates.fromCoordinatesToNotation(newX, newY));
            }
        }

        return possibleMoves;
    }

}

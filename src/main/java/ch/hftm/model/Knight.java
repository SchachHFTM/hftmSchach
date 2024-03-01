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

    /*
     * @Override
     * public void checkPossibleMoves() {
     * int x = this.x;
     * int y = this.y;
     * this.possibleMoves = new ArrayList<>();
     * // Array representing eight possible directions on the chessboard
     * int[][] directions = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 },
     * { 1, -2 }, { -1, 2 }, { -1, -2 } };
     * // Iterate through each direction and check for occupied squares
     * for (int[] direction : directions) {
     * int dx = direction[0];
     * int dy = direction[1];
     * // Calculate the next position based on the current direction
     * String move = Coordinates.fromCoordinatesToNotation(x + dx, y + dy);
     * 
     * // Check if the square is within the bounds of the chessboard
     * // TODO: getPiecesByName Methodik
     * if (getSquareByName(move) != null) {
     * // TODO: getPiecesByName Methodik
     * if (getSquareByName(move).occupied) {
     * possibleMoves.add(move);
     * }
     * }
     * }
     * 
     * }
     */
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

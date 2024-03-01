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
    /*
     * @Override
     * public void checkPossibleMoves() {
     * int x = this.x;
     * int y = this.y;
     * this.possibleMoves = new ArrayList<>();
     * 
     * // Array representing eight possible directions on the chessboard
     * int[][] directions = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, {
     * -1, 1 }, { -1, 0 }, { -1, -1 } };
     * 
     * // Iterate through each direction and check for occupied squares
     * for (int[] direction : directions) {
     * int dx = direction[0];
     * int dy = direction[1];
     * 
     * // Calculate the next position based on the current direction
     * String move = Coordinates.fromCoordinatesToNotation(x + dx, y + dy);
     * 
     * // Check if the square is within the bounds of the chessboard
     * // TODO: getPiecesByName Methodik
     * if (getSquareByName(move) != null) {
     * // If the square is occupied, add it to the list of possible moves
     * // TODO: getPiecesByName Methodik
     * if (getSquareByName(move).occupied) {
     * possibleMoves.add(move);
     * }
     * }
     * }
     * }
     */

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
            if (targetSquare != null && (isOpponentPiece(newX, newY) || !targetSquare.occupied)) {
                possibleMoves.add(targetSquare.getName());
            }
        }

        return possibleMoves;
    }
}

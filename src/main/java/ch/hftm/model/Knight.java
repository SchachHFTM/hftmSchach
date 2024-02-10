package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class Knight extends Piece {

    //Constructor
    public Knight(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Knight";

    }

    @Override
    public void checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        this.possibleMoves = new ArrayList<>();
        // Array representing eight possible directions on the chessboard
        int[][] directions = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };
        // Iterate through each direction and check for occupied squares
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            // Calculate the next position based on the current direction
            String move = Coordinates.fromCoordinatesToNotation(x + dx, y + dy);

            // Check if the square is within the bounds of the chessboard
            // TODO: getPiecesByName Methodik
            if (getSquareByName(move) != null) {
                // TODO: getPiecesByName Methodik
                if (getSquareByName(move).occupied) {
                    possibleMoves.add(move);
                }
            }
        }

    }

}

package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;

public class King extends Piece {
    //Constructor
    public King(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "King";

    }

    @Override
    public void checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        this.possibleMoves = new ArrayList<>();

        // Array representing eight possible directions on the chessboard
        int[][] directions = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 } };

        // Iterate through each direction and check for occupied squares
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            // Calculate the next position based on the current direction
            String move = Coordinates.fromCoordinatesToNotation(x + dx, y + dy);

            // Check if the square is within the bounds of the chessboard
            // TODO: getPiecesByName Methodik
            if (getSquareByName(move) != null) {
                // If the square is occupied, add it to the list of possible moves
                // TODO: getPiecesByName Methodik
                if (getSquareByName(move).occupied) {
                    possibleMoves.add(move);
                }
            }
        }
    }

}

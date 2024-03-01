package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.Coordinates;
import ch.hftm.Square;

public class Bishop extends Piece {

    // Constructor
    public Bishop(EColorPiece color, int x, int y) {
        super(color, x, y);
        this.type = "Bishop";

    }

    @Override
    public ArrayList<String> checkPossibleMoves() {
        int x = this.x;
        int y = this.y;
        this.possibleMoves = new ArrayList<>();
        // TODO Aktueller Spiele Implementieren und diese Attribut loschen oder ersetzen
        String currentPlayer = "Withe";

        int[] xDiagonal = { -1, -1, 1, 1 }; // x Diagnoal directions
        int[] yDiagonal = { -1, 1, -1, 1 }; // y Diagonal directions
        // Iterate through each direction (left, right, up, down)
        for (int direction = 0; direction < 4; direction++) {
            // Move along the current direction
            for (int nextX = x + xDiagonal[direction], nextY = y + yDiagonal[direction]; nextX >= 0 && nextX < 8
                    && nextY >= 0
                    && nextY < 8; nextX += xDiagonal[direction], nextY += yDiagonal[direction]) {
                String squareName = Coordinates.fromCoordinatesToNotation(nextX, nextY);
                Square currentSquare = getSquareByName(squareName);
                // Check if the square is occupied
                if (currentSquare.occupied) {
                    Piece piece = getPieceByName(squareName);
                    // If the square is occupied by an opponent's piece, it can be attacked
                    if (!piece.getColor().equals(currentPlayer)) {
                        possibleMoves.add(squareName);
                    }
                    break; // Regardless of whether it's a friend or foe, stop the loop
                }

                // If the square is not occupied, it can be entered
                possibleMoves.add(squareName);
            }
        }
        return possibleMoves;
    }

}

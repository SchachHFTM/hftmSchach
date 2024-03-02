package ch.hftm.Mock;

import java.util.ArrayList;

import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import ch.hftm.model.Square;

public class MockPiece extends Piece {

    public MockPiece(EColorPiece color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public ArrayList<String> checkPossibleMoves(ArrayList<Square> squares) {
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("A1");
        possibleMoves.add("B2");
        return possibleMoves;
    }
}
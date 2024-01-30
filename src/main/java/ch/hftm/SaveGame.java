package ch.hftm;

import ch.hftm.model.Bishop;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.King;
import ch.hftm.model.Knight;
import ch.hftm.model.Pawn;
import ch.hftm.model.Piece;
import ch.hftm.model.Queen;
import ch.hftm.model.Rook;

public class SaveGame {

    Piece[][] piecesPosition;
    Boolean startOrLoad;

    //TODO konzept überdenken start und laden

    public SaveGame(Piece[][] piecesPosition, Boolean startOrLoad) {
        this.piecesPosition = piecesPosition;
    }

    public static Piece[][] startPiecesPos() {
        Piece[][] startingPosition = new Piece[2][16];

        // White
        startingPosition[0][0] = new Rook(EColorPiece.WHITE, 0, 0);
        startingPosition[0][1] = new Knight(EColorPiece.WHITE, 1, 0);
        startingPosition[0][2] = new Bishop(EColorPiece.WHITE, 2, 0);
        startingPosition[0][3] = new Queen(EColorPiece.WHITE, 3, 0);
        startingPosition[0][4] = new King(EColorPiece.WHITE, 4, 0);
        startingPosition[0][5] = new Bishop(EColorPiece.WHITE, 5, 0);
        startingPosition[0][6] = new Knight(EColorPiece.WHITE, 6, 0);
        startingPosition[0][7] = new Rook(EColorPiece.WHITE, 7, 0);

        startingPosition[0][8] = new Pawn(EColorPiece.WHITE, 7, 1);
        startingPosition[0][9] = new Pawn(EColorPiece.WHITE, 0, 1);
        startingPosition[0][10] = new Pawn(EColorPiece.WHITE, 1, 1);
        startingPosition[0][11] = new Pawn(EColorPiece.WHITE, 2, 1);
        startingPosition[0][12] = new Pawn(EColorPiece.WHITE, 3, 1);
        startingPosition[0][13] = new Pawn(EColorPiece.WHITE, 4, 1);
        startingPosition[0][14] = new Pawn(EColorPiece.WHITE, 5, 1);
        startingPosition[0][15] = new Pawn(EColorPiece.WHITE, 6, 1);

        // Black
        startingPosition[1][0] = new Rook(EColorPiece.BLACK, 0, 7);
        startingPosition[1][1] = new Knight(EColorPiece.BLACK, 1, 7);
        startingPosition[1][2] = new Bishop(EColorPiece.BLACK, 2, 7);
        startingPosition[1][3] = new Queen(EColorPiece.BLACK, 3, 7);
        startingPosition[1][4] = new King(EColorPiece.BLACK, 4, 7);
        startingPosition[1][5] = new Bishop(EColorPiece.BLACK, 5, 7);
        startingPosition[1][6] = new Knight(EColorPiece.BLACK, 6, 7);
        startingPosition[1][7] = new Rook(EColorPiece.BLACK, 7, 7);

        startingPosition[1][8] = new Pawn(EColorPiece.BLACK, 0, 6);
        startingPosition[1][9] = new Pawn(EColorPiece.BLACK, 1, 6);
        startingPosition[1][10] = new Pawn(EColorPiece.BLACK, 2, 6);
        startingPosition[1][11] = new Pawn(EColorPiece.BLACK, 3, 6);
        startingPosition[1][12] = new Pawn(EColorPiece.BLACK, 4, 6);
        startingPosition[1][13] = new Pawn(EColorPiece.BLACK, 5, 6);
        startingPosition[1][14] = new Pawn(EColorPiece.BLACK, 6, 6);
        startingPosition[1][15] = new Pawn(EColorPiece.BLACK, 7, 6);

        return startingPosition;
    }

}

package ch.hftm;

import java.util.ArrayList;

import ch.hftm.control.Game;
import ch.hftm.model.Piece;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    @FXML
    GridPane board;
    public ArrayList<Square> squares = new ArrayList<>();
    public SaveGame currentGame = new SaveGame();
    public Game game;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Board(GridPane board, SaveGame currentGameFromSave) {
        this.board = board;
        initializeBoard(this.board);
        setPiecesOnBoard(currentGameFromSave.piecesPosition);
        currentGame = currentGameFromSave;
    }

    public Board(GridPane board) {
        this.board = board;
        initializeBoard(this.board);
        currentGame.setNewGamePiecesPosition();
        setPiecesOnBoard(currentGame.piecesPosition);
    }

    private void initializeBoard(GridPane board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #facf9d;");
                } else {
                    square.setStyle("-fx-background-color: #cd8c3f;");
                }
                square.setOnMouseClicked(event -> handlePieceSelection(square));
                board.add(square, col, row);
                squares.add(square);
            }
        }
    }

    private void addPiece(Square sq, Piece pi) {
        sq.getChildren().add(pi);
        sq.occupied = true;
        pi.setImage();
    }

    private void setPiecesOnBoard(Piece[][] piArray) {
        for (int i = 0; i < piArray.length; i++) {
            for (int j = 0; j < piArray[i].length; j++) {
                Piece pi = piArray[i][j];
                int x = pi.getPieceX();
                int y = pi.getPieceY();
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    for (Square square : squares) {
                        if (square.x == x && square.y == y) {
                            square.setPiece(pi); // Associate the piece with the square
                            addPiece(square, pi);
                        }
                    }
                }
            }
        }
    }

    private void updateEventHandlers() {
        for (Square square : squares) {
            square.setOnMouseClicked(event -> {
                if (selectedPiece != null) {
                    handleSquareClick(square);
                } else {
                    handlePieceSelection(square);
                }
            });
        }
    }

    private void handlePieceSelection(Square square) {
        Piece piece = square.getPiece();
        if (piece != null) {
            System.out.println("Selected" + piece.type);
            selectedPiece = piece;
            selectedSquare = square;
            updateEventHandlers();
        } else {
        }
    }

    private void handleSquareClick(Square square) {
        if (selectedPiece != null && selectedSquare != null) {
            ArrayList<String> possibleMoves = selectedPiece.checkPossibleMoves();
            if (possibleMoves.contains(square.getName())) {
                movePiece(selectedSquare, square);
                selectedPiece = null;
                selectedSquare = null;
                updateEventHandlers();
            } else {
            }
        }
    }

    private void movePiece(Square sourceSquare, Square destinationSquare) {
        // Retrieve the Piece from the source square
        Piece piece = sourceSquare.getPiece();

        // Get the row and column indices of the destination square
        int destRow = GridPane.getRowIndex(destinationSquare);
        int destCol = GridPane.getColumnIndex(destinationSquare);

        // Update the x and y coordinates of the piece
        piece.x = destCol;
        piece.y = destRow;

        // Remove the piece from its current position
        sourceSquare.getChildren().remove(piece);
        sourceSquare.setPiece(null); // Clear the piece from the source square
        sourceSquare.occupied = false; // Update the occupied status

        // Add the piece to the destination square
        GridPane.setColumnIndex(piece, destCol);
        GridPane.setRowIndex(piece, destRow);
        destinationSquare.getChildren().add(piece);
        destinationSquare.setPiece(piece); // Set the piece on the destination square
        destinationSquare.occupied = true; // Update the occupied status

        // Update possible moves after moving the piece
        piece.checkPossibleMoves();
    }
}

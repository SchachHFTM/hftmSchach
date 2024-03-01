package ch.hftm;

import java.util.ArrayList;

import ch.hftm.control.Game;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    @FXML
    GridPane board;
    public ArrayList<Square> squares = new ArrayList<>();
    public SaveGame currentGame = new SaveGame();
    public Game game;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Board(GridPane board, SaveGame currentGameFromSave, Game game) {
        this.board = board;
        this.game = game;
        initializeBoard(this.board);
        setPiecesOnBoard(currentGameFromSave.piecesPosition);
        currentGame = currentGameFromSave;
    }

    public Board(GridPane board, Game game) {
        this.board = board;
        this.game = game;
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

            boolean isWhitePiece = piece.getColor() == EColorPiece.WHITE;
            if ((isWhitePiece && !game.whiteTurn) || (!isWhitePiece && game.whiteTurn)) {
                return;
            }
            ArrayList<String> possibleMoves = piece.checkPossibleMoves(squares);
            for (String move : possibleMoves) {
                for (Square possibleMove : squares) {
                    if (possibleMove.getName().equals(move)) {
                        possibleMove.setEffect(new Glow(0.6));
                    }
                }
            }
            piece.setEffect(new Glow(0.8));
            selectedPiece = piece;
            selectedSquare = square;
            updateEventHandlers();
        } else {
        }
    }

    private void handleSquareClick(Square square) {
        System.out.println(square.name);
        if (selectedPiece != null && selectedSquare != null) {
            ArrayList<String> possibleMoves = selectedPiece.checkPossibleMoves(squares);
            if (possibleMoves.contains(square.getName())) {
                game.movePiece(selectedSquare, square, squares);
                selectedPiece.setEffect(null);
                for (String move : possibleMoves) {
                    for (Square possibleMove : squares) {
                        if (possibleMove.getName().equals(move)) {
                            possibleMove.setEffect(null);
                        }
                    }
                }
                selectedPiece = null;
                selectedSquare = null;
                updateEventHandlers();
            } else {
            }
        }
    }
}

package chess.utils;

import chess.domain.Position;
import chess.piece.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class InitializedChessboardGenerator {
    private static final int SIZE_OF_BOARD = 8;
    private static final int ROW_OF_BLACK_WITHOUT_PAWN = 0;
    private static final int ROW_OF_BLACK_PAWN = 1;
    private static final int ROW_OF_WHITE_WITHOUT_PAWN = 7;
    private static final int ROW_OF_WHITE_PAWN = 6;
    private static final int START_ROW_OF_BLANK = 2;
    private static final int END_ROW_OF_BLANK = 6;

    public static Map<Position, Piece> generate() {
        Map<Position, Piece> board = new LinkedHashMap<>();

        addWithoutPawn(board, ROW_OF_BLACK_WITHOUT_PAWN, Color.BLACK);
        addPawn(board, ROW_OF_BLACK_PAWN, Color.BLACK);

        addAllBlank(board);

        addPawn(board, ROW_OF_WHITE_PAWN, Color.WHITE);
        addWithoutPawn(board, ROW_OF_WHITE_WITHOUT_PAWN, Color.WHITE);

        return board;
    }

    private static void addWithoutPawn(Map<Position, Piece> board, int row, Color color) {
        board.put(new Position(row, 0), new Rook(color));
        board.put(new Position(row, 1), new Knight(color));
        board.put(new Position(row, 2), new Bishop(color));
        board.put(new Position(row, 3), new Queen(color));
        board.put(new Position(row, 4), new King(color));
        board.put(new Position(row, 5), new Bishop(color));
        board.put(new Position(row, 6), new Knight(color));
        board.put(new Position(row, 7), new Rook(color));
    }

    private static void addPawn(Map<Position, Piece> board, int row, Color color) {
        for (int col = 0; col < SIZE_OF_BOARD; col++) {
            board.put(new Position(row, col), new Pawn(color));
        }
    }

    private static void addAllBlank(Map<Position, Piece> board) {
        for (int row = START_ROW_OF_BLANK; row <= END_ROW_OF_BLANK; row++) {
            addBlank(board, row);
        }
    }

    private static void addBlank(Map<Position, Piece> board, int row) {
        for (int col = 0; col < SIZE_OF_BOARD; col++) {
            board.put(new Position(row, col), new Blank());
        }
    }
}

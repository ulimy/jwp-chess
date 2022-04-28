package chess.view;

import chess.domain.Chessboard;
import chess.domain.Position;
import chess.dto.ScoreDto;
import chess.piece.Piece;

import java.util.Map;

public class OutputView {

    private static final String START_ERROR = "[ERROR] ";

    public static void printError(String message) {
        System.out.println(START_ERROR + message);
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Chessboard chessboard) {
        Map<Position, Piece> board = chessboard.getBoard();

        for (int i = 0; i < 8; i++) {
            printBoardLine(board, i);
        }
        System.out.println();
    }

    public static void printScore(ScoreDto score) {
        System.out.println("\n획득 점수");
        System.out.println("Black : " + score.getBlack());
        System.out.println("White : " + score.getWhite());
        System.out.println("승리 : " + score.getWinner());
    }

    private static void printBoardLine(Map<Position, Piece> chessboard, int x) {
        for (int i = 0; i < 8; i++) {
            System.out.print(chessboard.get(new Position(x, i)).getSymbolByColor());
        }
        System.out.println();
    }

}

package chess.chessgame;

import chess.dto.ScoreDto;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.*;
import chess.utils.UnicodeConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private static final double MINUS_SCORE_OF_SAME_Y_PAWN = 0.5;

    private final Turn turn;
    private State state;

    public ChessGame() {
        this.state = new Ready();
        this.turn = new Turn();
    }

    public ChessGame(String state, String turn, Map<Position, Piece> boards) {
        this.state = convertToState(state, new Chessboard(boards));
        this.turn = new Turn(turn);
    }

    public void start() {
        state = state.start();
    }

    public void move(MovingPosition movingPosition) {
        state = state.move(movingPosition, turn);
    }

    public void end() {
        state = new Finish(state.getChessboard());
    }

    public Map<Position, Piece> getChessBoard() {
        return state.getChessboard().getBoard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public ScoreDto computeScore() {
        double scoreOfBlack = state.computeScore(Color.BLACK, MINUS_SCORE_OF_SAME_Y_PAWN);
        double scoreOfWhite = state.computeScore(Color.WHITE, MINUS_SCORE_OF_SAME_Y_PAWN);

        if (scoreOfBlack > scoreOfWhite) {
            return new ScoreDto(scoreOfBlack, scoreOfWhite, Color.BLACK);
        }
        if (scoreOfBlack < scoreOfWhite) {
            return new ScoreDto(scoreOfBlack, scoreOfWhite, Color.WHITE);
        }

        return new ScoreDto(scoreOfBlack, scoreOfWhite);
    }

    public List<String> getPiecesByUnicode() {
        return state.getChessboard()
                .getBoard()
                .values()
                .stream()
                .map(piece -> UnicodeConverter.convert(piece.getSymbolByColor()))
                .collect(Collectors.toList());
    }

    public String getStateToString() {
        return state.getStateToString();
    }

    public String getColorOfTurn() {
        return turn.getColor();
    }

    private State convertToState(String stateName, Chessboard chessboard) {
        return StateFactory.valueOf(stateName)
                .create(chessboard);
    }

}

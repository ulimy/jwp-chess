package chess.state;

import chess.domain.Chessboard;
import chess.domain.MovingPosition;
import chess.domain.Turn;


public class Play implements State {

    @Override
    public State start() {
        throw new UnsupportedOperationException(UNSUPPORTED_STATE);
    }

    @Override
    public State move(Chessboard chessboard, MovingPosition movingPosition, Turn turn) {
        chessboard.move(movingPosition, turn);

        if (chessboard.isOver()) {
            return new Finish();
        }

        turn.nextTurn();
        return new Play();
    }

    @Override
    public State end() {
        return new Finish();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getStateToString() {
        return "PLAY";
    }

}

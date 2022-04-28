package chess.piece;

import chess.domain.MovingPosition;
import chess.domain.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public boolean isMovable(MovingPosition movingPosition) {
        return movingPosition.isCross();
    }

    @Override
    public List<Position> computeMiddlePosition(MovingPosition movingPosition) {
        return movingPosition.computeCrossMiddle();
    }
}

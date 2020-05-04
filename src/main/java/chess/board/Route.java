package chess.board;

import chess.exception.InvalidConstructorValueException;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;
import java.util.Objects;

import static java.lang.Math.abs;

public class Route {
    private final Map<Location, Piece> route;
    private final Location now;
    private final Location destination;

    public Route(Map<Location, Piece> route, Location now, Location destination) {
        validNullValueOfRoute(route);
        validNullValueOfLocations(now, destination);
        this.route = route;
        this.now = now;
        this.destination = destination;
    }

    private void validNullValueOfLocations(Location now, Location destination) {
        if (Objects.isNull(now) || Objects.isNull(destination)) {
            throw new InvalidConstructorValueException();
        }
    }

    private void validNullValueOfRoute(Map<Location, Piece> route) {
        if (Objects.isNull(route)) {
            throw new InvalidConstructorValueException();
        }
    }

    public boolean isDiagonal() {
        return abs(now.getRowValue() - destination.getRowValue())
                == abs(now.getColValue() - destination.getColValue());
    }

    public boolean isStraight() {
        return now.isSameRow(destination) || isVertical();
    }

    public boolean isForwardDiagonal(int value) {
        int colGap = Math.abs(destination.getColValue() - now.getColValue());
        Location forwardRowLocation = now.plusRowBy(value);
        return forwardRowLocation.isSameRow(destination) && colGap == 1;
    }

    private boolean isVertical() {
        return now.isSameCol(destination);
    }

    public boolean isNotEmptyDestination() {
        return route.containsKey(destination);
    }

    public boolean isEnemyNowAndDestination() {
        Piece nowPiece = route.get(now);
        Piece destinationPiece = route.get(destination);

        return nowPiece.isReverseTeam(destinationPiece);
    }

    public boolean isExistPieceIn(Location location) {
        return route.containsKey(location);
    }

    public boolean isNotExistPieceIn(Location location) {
        return !route.containsKey(location);
    }

    public boolean isNotExistInDestination() {
        return Objects.isNull(route.get(destination));
    }

    public Location getNow() {
        return now;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Location, Piece> entry : route.entrySet()) {
            sb.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue());
        }
        return sb.toString();
    }
}

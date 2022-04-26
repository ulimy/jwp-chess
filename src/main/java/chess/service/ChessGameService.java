package chess.service;

import chess.chessgame.ChessGame;
import chess.chessgame.MovingPosition;
import chess.chessgame.Position;
import chess.dao.ChessboardDao;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.piece.Piece;
import chess.utils.PieceGenerator;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChessGameService {

    private ChessboardDao dao;
    private ChessGame chessGame = new ChessGame();

    public ChessGameService(ChessboardDao dao) {
        this.dao = dao;
    }

    public void init() {
        if (dao.isDataExist()) {
            setChessGameByDto(dao.load());
            return;
        }
        chessGame = new ChessGame();
    }

    public void start() {
        chessGame.start();
    }

    public void restart() {
        dao.truncateAll();
        chessGame = new ChessGame();
    }

    public void move(String from, String to) {
        chessGame.move(new MovingPosition(from, to));
    }

    public ScoreDto status() {
        return chessGame.computeScore();
    }

    public void save() {
        dao.save(ChessGameDto.of(chessGame));
    }

    public void end() {
        chessGame.end();
    }

    public List<String> getPiecesByUnicode() {
        return chessGame.getPiecesByUnicode();
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    private void setChessGameByDto(ChessGameDto dto) {
        LinkedHashMap<Position, Piece> boards = dto.getPieces()
                .stream()
                .collect(Collectors.toMap(
                                pieceDto -> new Position(pieceDto.getX(), pieceDto.getY()),
                                pieceDto -> PieceGenerator.generate(pieceDto.getType(), pieceDto.getColor()),
                                (key1, key2) -> key1,
                                LinkedHashMap::new
                        )
                );

        chessGame = new ChessGame(dto.getState(), dto.getTurn(), boards);
    }

}

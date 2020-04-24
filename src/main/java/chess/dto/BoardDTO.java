package chess.dto;

import static chess.util.NullValidator.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.board.PositionFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class BoardDTO {
	private Map<String, String> board;

	public BoardDTO(Map<String, String> board) {
		this.board = board;
	}

	public static BoardDTO from(ChessBoard chessBoard) {
		validateNull(chessBoard);

		Map<Position, Piece> boardData = chessBoard.getBoard();
		Map<String, String> boardDTOData = new HashMap<>();

		for (Position position : boardData.keySet()) {
			boardDTOData.put(position.getName(), boardData.get(position).getName());
		}

		return new BoardDTO(boardDTOData);
	}

	public Map<String, String> getBoard() {
		return this.board;
	}

	public Map<Position, Piece> createBoard() {
		Map<Position, Piece> boardData = new HashMap<>();

		for (String positionName : this.board.keySet()) {
			Position position = PositionFactory.of(positionName);
			String pieceName = this.board.get(positionName);
			Piece piece = PieceType.of(pieceName).createPiece(position);

			boardData.put(position, piece);
		}

		return boardData;
	}
}
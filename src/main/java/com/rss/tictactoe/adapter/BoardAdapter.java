package com.rss.tictactoe.adapter;

import com.rss.tictactoe.dto.BoardDTO;
import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Player;

import java.util.Arrays;

import static com.rss.tictactoe.game.Player.FIRST;
import static com.rss.tictactoe.game.Player.SECOND;

public class BoardAdapter {

  public static Board convert(BoardDTO boardDTO) {
    Board board = new Board();
    board.setBoard(boardDTO.getBoard());
    board.setPlayer(evaluatePlayer(boardDTO.getBoard()));

    return board;
  }

  public static BoardDTO convert(Board board) {
    char[][] boardArray = Arrays.stream(board.getBoard())
        .map(el -> el.clone())
        .toArray($ -> board.getBoard());

    return new BoardDTO(boardArray);
  }

  public static Player evaluatePlayer(char[][] board) {
    int countX = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 'X') {
          countX++;
        } else if (board[i][j] == 'O') {
          countX--;
        }
      }
    }
    return countX == 0 ? FIRST : SECOND;
  }
}

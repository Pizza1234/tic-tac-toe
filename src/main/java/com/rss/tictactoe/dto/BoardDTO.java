package com.rss.tictactoe.dto;

import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Player;

import java.util.Arrays;

import static com.rss.tictactoe.game.Player.FIRST;
import static com.rss.tictactoe.game.Player.SECOND;

public class BoardDTO {

  private char[][] board;

  public BoardDTO() {
    int dim = 3;
    this.board = new char[dim][dim];
    for (int i = 0; i < dim; i++) {
      Arrays.fill(board[i], ' ');
    }
  }

  public BoardDTO(char[][] board) {
    this.board = Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
  }

  public BoardDTO(Board board) {
    this.board = Arrays.stream(board.getBoard()).map(el -> el.clone()).toArray($ -> board.getBoard());
  }

  public char[][] getBoard() {
    return board;
  }

  public void setBoard(char[][] board) {
    this.board = board;
  }

  public Board createBoard() {
    Board board = new Board();
    board.setBoard(this.board);
    board.setPlayer(evaluatePlayer());

    return board;
  }

  private Player evaluatePlayer() {
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

package com.rss.tictactoe.dto;

import java.util.Arrays;

public class BoardDTO {

  private char[][] board;
  private String status;

  public BoardDTO() {
    int dim = 3;
    this.board = new char[dim][dim];
    for (int i = 0; i < dim; i++) {
      Arrays.fill(board[i], ' ');
    }
  }

  public BoardDTO(char[][] board) {
    this.board = Arrays.stream(board)
        .map(el -> el.clone())
        .toArray($ -> board.clone());
  }

  public char[][] getBoard() {
    return board;
  }

  public void setBoard(char[][] board) {
    this.board = board;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

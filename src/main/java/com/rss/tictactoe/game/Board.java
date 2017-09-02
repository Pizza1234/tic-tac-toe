package com.rss.tictactoe.game;

import java.util.Arrays;

import static com.rss.tictactoe.game.Player.FIRST;
import static com.rss.tictactoe.game.Player.SECOND;

public class Board {
  private int dim;
  private char[][] board;
  private Player player;

  public Board() {
    this.dim = 3;
    this.player = FIRST;
    this.board = new char[dim][dim];
    for (int i = 0; i < dim; i++) {
      Arrays.fill(board[i], ' ');
    }
  }

  public Board(Board board) {
    this.dim = board.dim;
    this.player = board.player;
    this.board = Arrays.stream(board.board).map(el -> el.clone()).toArray($ -> board.board.clone());
  }

  public Player check(int x, int y) {
    if (board[x][y] == FIRST.getLogo()) {
      return FIRST;
    } else if (board[x][y] == SECOND.getLogo()) {
      return SECOND;
    } else {
      return null;
    }
  }

  public void move(int x, int y) {
    if (!isValidMove(x, y)) {
      throw new IllegalArgumentException();
    }

    board[x][y] = player.getLogo();
    player = player.next();
  }

  public boolean isValidMove(int x, int y) {
    return board[x][y] == ' ';
  }

  public int getDim() {
    return dim;
  }

  public void setDim(int dim) {
    this.dim = dim;
  }

  public char[][] getBoard() {
    return board;
  }

  public void setBoard(char[][] board) {
    this.board = board;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < getDim(); i++) {
      sb.append(Arrays.toString(board[i]) + '\n');
    }
    return sb.toString();
  }
}

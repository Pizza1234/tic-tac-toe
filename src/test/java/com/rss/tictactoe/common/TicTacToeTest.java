package com.rss.tictactoe.common;

import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Player;

public interface TicTacToeTest {
  int RECURSION_DEPTH = 1;
  char[][] FULL_BOARD = {
      {'O','O','X'},
      {'X','X','O'},
      {'O','X','X'}
  };
  char[][] FIRST_MOVE_BOARD = {
      {' ',' ',' '},
      {' ','X',' '},
      {' ',' ',' '}
  };

  default Board createBoard(char[][] boardArray, Player player) {
    Board board = new Board();
    board.setDim(3);
    board.setBoard(boardArray);
    board.setPlayer(player);

    return board;
  }
}

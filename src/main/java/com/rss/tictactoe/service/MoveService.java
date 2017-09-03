package com.rss.tictactoe.service;

import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Direction;
import com.rss.tictactoe.game.Line;
import com.rss.tictactoe.game.Move;
import org.springframework.stereotype.Service;


@Service
public class MoveService {
  private static final int MAX_RECURSION_DEPTH = 2;

  public Board makeMove(Board board, int recursionDepth) {
    if (movesLeft(board) == 0) {
      throw new IllegalArgumentException();
    }
    Move bestMove = getMove(board, recursionDepth);
    board.move(bestMove.getX(), bestMove.getY());

    return board;
  }

  public String getStatus(Board board) {
    char[][] brd = board.getBoard();
    if (checkBoard(brd, 'X')) {
          return "X wins!";
    }
    if (checkBoard(brd, 'O')) {
      return "O wins!";
    }
    if (movesLeft(board) == 0) {
      return "Draw!";
    }

    return "Playing!";
  }

  private boolean checkBoard(char[][] brd, char ch) {
    return (brd[0][0] == brd[0][1] && brd[0][1] == brd[0][2] && brd[0][2] == ch) ||
        (brd[1][0] == brd[1][1] && brd[1][1] == brd[1][2] && brd[1][2] == ch) ||
        (brd[2][0] == brd[2][1] && brd[2][1] == brd[2][2] && brd[2][2] == ch) ||
        (brd[0][0] == brd[1][0] && brd[1][0] == brd[2][0] && brd[2][0] == ch) ||
        (brd[0][1] == brd[1][1] && brd[1][1] == brd[2][1] && brd[2][1] == ch) ||
        (brd[0][2] == brd[1][2] && brd[1][2] == brd[2][2] && brd[2][2] == ch) ||
        (brd[0][0] == brd[1][1] && brd[1][1] == brd[2][2] && brd[2][2] == ch) ||
        (brd[0][2] == brd[1][1] && brd[1][1] == brd[2][0] && brd[2][0] == ch);
  }

  public Move getMove(Board board, int recursionDepth) {
    if (movesLeft(board) == 0) {
      throw new IllegalArgumentException();
    }

    int dim = board.getDim();
    int weight = Integer.MIN_VALUE;
    Move move = null;
    for (int x = 0; x < dim; x++) {
      for (int y = 0; y < dim; y++) {
        int newWeight = getMoveWeight(board, x, y, recursionDepth);
        if (weight < newWeight) {
          move = new Move(x,y);
          weight = newWeight;
        }
      }
    }

    return move;
  }

  private int getMoveWeight(Board board, int x, int y, int recursionDepth) {
    if (!board.isValidMove(x,y)) {
      return Integer.MIN_VALUE;
    }

    int weight = 0;

    for (Direction direction : Direction.getMainDirections()) {

      Line line = getLine(board, direction, x, y);

      if (line.getLineLength() == board.getDim() - 1) {
        return Integer.MAX_VALUE;
      } else if (line.getTotalLength() == board.getDim()) {
        weight += Math.pow(10, line.getLineLength());
      }
    }

    if (recursionDepth >= MAX_RECURSION_DEPTH || movesLeft(board) <= 1) {
      return weight;
    }

    Board newBoard = new Board(board);
    newBoard.move(x,y);
    Move move = getMove(newBoard, recursionDepth + 1);

    return weight - getMoveWeight(newBoard, move.getX(), move.getY(), recursionDepth + 1);
  }

  private Line getLine(Board board, Direction direction, int x, int y) {
    return new Line(0,1)
        .add(getLine(board, direction, x, y, new Line()))
        .add(getLine(board, direction.getOpposite(), x, y, new Line()));
  }

  private Line getLine(Board board, Direction direction, int x, int y, Line line) {
    if (!isValidDirection(board, direction, x, y)) {
      return line;
    }

    boolean isSet = board.check(direction.getX() + x, direction.getY() + y) == board.getPlayer();
    boolean isAvailable = board.check(direction.getX() + x, direction.getY() + y) == null;

    if (isSet) {
      Line newLine = line.add(new Line(1, 0));
      return getLine(board, direction, x + direction.getX(), y + direction.getY(), newLine);
    } else if (isAvailable) {
      Line newLine = line.add(new Line(0, 1));
      return getLine(board, direction, x + direction.getX(), y + direction.getY(), newLine);
    } else {
      return line;
    }

  }

  private boolean isValidDirection(Board board, Direction direction, int x, int y) {

    return (direction.getX() + x >= 0) &&
        (direction.getX() + x < board.getDim()) &&
        (direction.getY() + y >= 0) &&
        (direction.getY() + y < board.getDim());
  }

  public int movesLeft(Board board) {
    int count = 0;
    int dim = board.getDim();
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if (board.getBoard()[i][j] == ' ') {
          count++;
        }
      }
    }

    return count;
  }

}

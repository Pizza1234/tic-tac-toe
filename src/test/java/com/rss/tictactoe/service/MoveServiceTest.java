package com.rss.tictactoe.service;

import com.rss.tictactoe.common.TicTacToeTest;
import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Move;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static com.rss.tictactoe.game.Player.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MoveServiceTest implements TicTacToeTest {

  @InjectMocks
  private MoveService moveService;

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenMakeMoveOnFullBoard() throws Exception {
    moveService.makeMove(createBoard(FULL_BOARD, SECOND), RECURSION_DEPTH);
  }

  @Test
  public void shouldReturnCorrectFirstMoveWhenMakeMove() throws Exception {
    char[][] board = moveService.makeMove(new Board(), RECURSION_DEPTH).getBoard();
    assertTrue(EqualsBuilder.reflectionEquals(FIRST_MOVE_BOARD, board));
  }

  @Test
  public void shouldReturnCorrectFirstMoveWhenGetMove() throws Exception {
    Move actualMove = moveService.getMove(new Board(), RECURSION_DEPTH);
    Move expectedMove = new Move(1, 1);
    assertTrue(EqualsBuilder.reflectionEquals(expectedMove, actualMove));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenGetMoveOnFullBoard() throws Exception {
    moveService.getMove(createBoard(FULL_BOARD, SECOND), RECURSION_DEPTH);
  }

  @Test
  public void shouldReturnCorrectMovesNumberWhenMovesLeftOnEmptyBoard() throws Exception {
    assertEquals(moveService.movesLeft(new Board()), 9);
  }

  @Test
  public void shouldReturnCorrectMovesNumberWhenMovesLeftOnFirstMoveBoard() throws Exception {
    assertEquals(moveService.movesLeft(createBoard(FIRST_MOVE_BOARD, SECOND)), 8);
  }

  @Test
  public void shouldReturnCorrectMovesNumberWhenMovesLeftOnFullBoard() throws Exception {
    assertEquals(moveService.movesLeft(createBoard(FULL_BOARD, SECOND)), 0);
  }

}
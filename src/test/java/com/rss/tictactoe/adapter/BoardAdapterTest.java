package com.rss.tictactoe.adapter;

import com.rss.tictactoe.common.TicTacToeTest;
import com.rss.tictactoe.dto.BoardDTO;
import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.game.Player;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.rss.tictactoe.game.Player.FIRST;
import static com.rss.tictactoe.game.Player.SECOND;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BoardAdapterTest implements TicTacToeTest {

  @Test
  public void shouldConvertBoardDTOtoBoard() throws Exception {
    BoardDTO boardDTO = new BoardDTO(FULL_BOARD);
    Board actualBoard = BoardAdapter.convert(boardDTO);
    Board expectedBoard = createBoard(FULL_BOARD, SECOND);
    assertTrue(EqualsBuilder.reflectionEquals(expectedBoard, actualBoard));
  }

  private void assertTrue(boolean b) {
  }

  @Test
  public void shouldConvertBoardtoBoardDTO() throws Exception {
    Board board = createBoard(FULL_BOARD, SECOND);
    BoardDTO actualBoardDTO = BoardAdapter.convert(board);
    BoardDTO expectedBoardDTO = new BoardDTO(FULL_BOARD);

    assertTrue(EqualsBuilder.reflectionEquals(expectedBoardDTO, actualBoardDTO));
  }

  @Test
  public void shouldEvaluatePlayerForNewBoard() throws Exception {
    Player actualPlayer = BoardAdapter.evaluatePlayer(new Board().getBoard());
    Player expectedPlayer = FIRST;

    assertEquals(expectedPlayer, actualPlayer);
  }

  @Test
  public void shouldEvaluatePlayerForFirstMoveBoard() throws Exception {
    Player actualPlayer = BoardAdapter.evaluatePlayer(createBoard(FIRST_MOVE_BOARD, SECOND).getBoard());
    Player expectedPlayer = SECOND;

    assertEquals(expectedPlayer, actualPlayer);
  }

  @Test
  public void shouldEvaluatePlayerForFullBoard() throws Exception {
    Player actualPlayer = BoardAdapter.evaluatePlayer(createBoard(FULL_BOARD, SECOND).getBoard());
    Player expectedPlayer = SECOND;

    assertEquals(expectedPlayer, actualPlayer);
  }

}
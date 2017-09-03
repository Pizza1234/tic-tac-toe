package com.rss.tictactoe.controller;

import com.rss.tictactoe.adapter.BoardAdapter;
import com.rss.tictactoe.dto.BoardDTO;
import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

  @Autowired
  private MoveService moveService;

  @PostMapping
  public ResponseEntity makeMove(@RequestBody BoardDTO boardDTO) {
    Board board = BoardAdapter.convert(boardDTO);
    String status = moveService.getStatus(board);
    if (!status.equals("Playing!")) {
      boardDTO.setStatus(status);
      return new ResponseEntity(boardDTO, OK);
    }

    Board boardAfterMove = moveService.makeMove(board, 0);

    BoardDTO resultBoard = BoardAdapter.convert(boardAfterMove);
    resultBoard.setStatus(moveService.getStatus(boardAfterMove));
    return new ResponseEntity(resultBoard, OK);
  }

}

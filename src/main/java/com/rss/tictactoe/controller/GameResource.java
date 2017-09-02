package com.rss.tictactoe.controller;

import com.rss.tictactoe.dto.BoardDTO;
import com.rss.tictactoe.game.Board;
import com.rss.tictactoe.service.MoveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/board")
public class GameResource {

  @PostMapping
  public ResponseEntity makeMove(@RequestBody BoardDTO boardDTO) {
    Board board = boardDTO.createBoard();
    if (MoveService.movesLeft(board) == 0) {
      return new ResponseEntity("Game Over", BAD_REQUEST);
    }

    return new ResponseEntity(new BoardDTO(MoveService.makeMove(board, 0)), OK);
  }

}

package com.rss.tictactoe.game;

public enum Player {
  FIRST("Player 1", 'X'),
  SECOND("Player 2", 'O');

  Player(String name, char logo) {
    this.name = name;
    this.logo = logo;
  }

  private String name;
  private char logo;

  public String getName() {
    return name;
  }

  public char getLogo() {
    return logo;
  }

  public Player next() { return this == FIRST ? SECOND : FIRST; }

}

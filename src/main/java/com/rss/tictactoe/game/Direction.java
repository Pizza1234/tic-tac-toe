package com.rss.tictactoe.game;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum Direction {
  LEFT(-1, 0),
  UP_LEFT(-1, 1),
  UP(0, 1),
  UP_RIGHT(1, 1),
  RIGHT(1, 0),
  DOWN_RIGHT(1, -1),
  DOWN(0, -1),
  DOWN_LEFT(-1, -1);

  private int x;
  private int y;

  Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Direction getOpposite() {
    if (this == LEFT) return RIGHT;
    if (this == RIGHT) return LEFT;
    if (this == UP_LEFT) return DOWN_RIGHT;
    if (this == DOWN_RIGHT) return UP_LEFT;
    if (this == UP) return DOWN;
    if (this == DOWN) return UP;
    if (this == UP_RIGHT) return DOWN_LEFT;
    if (this == DOWN_LEFT) return UP_RIGHT;

    return null;
  }

  public static List<Direction> getMainDirections() {
    return ImmutableList.of(LEFT, UP_LEFT, UP, UP_RIGHT);
  }

}

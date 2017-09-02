package com.rss.tictactoe.game;

public class Line {
  private int lineLength;
  private int availableLength;

  public Line() {
  }

  public Line(int lineLength, int availableLength) {
    this.lineLength = lineLength;
    this.availableLength = availableLength;
  }

  public int getTotalLength() {
    return lineLength + availableLength;
  }

  public int getLineLength() {
    return lineLength;
  }

  public void setLineLength(int lineLength) {
    this.lineLength = lineLength;
  }

  public int getAvailableLength() {
    return availableLength;
  }

  public void setAvailableLength(int availableLength) {
    this.availableLength = availableLength;
  }

  public Line add(Line statistics) {
    return new Line(lineLength + statistics.lineLength, availableLength + statistics.availableLength);
  }
}

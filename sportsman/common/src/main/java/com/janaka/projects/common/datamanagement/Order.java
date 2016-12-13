package com.janaka.projects.common.datamanagement;

public class Order {

  private int index;

  private OrderType dir;

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public OrderType getDir() {
    return dir;
  }

  public void setDir(OrderType dir) {
    this.dir = dir;
  }
}

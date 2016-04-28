package liarspoker.common;

public final class PlayerBid {

  public final int player;
  public final Bid bid;

  public PlayerBid(int player, Bid bid) {
    this.player = player;
    this.bid = bid;
  }
}

package liarspoker.impl.bids;

import liarspoker.common.Bid;
import liarspoker.common.Card;

public final class Bids {

  public static Bid highCard(Card.Value value) {
    return new HighCardBid(value);
  }

  public static Bid pair(Card.Value value) {
    return new PairBid(value);
  }

  public static Bid callBluff() {
    return new CallBluffBid();
  }
}

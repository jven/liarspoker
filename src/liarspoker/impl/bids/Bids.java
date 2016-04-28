package liarspoker.impl.bids;

import liarspoker.common.Bid;
import liarspoker.common.Bid.Type;
import liarspoker.common.Card;

public final class Bids {

  public static Bid highCard(Card.Value value) {
    return new NOfAKindBid(Type.HIGH_CARD, 1, "one", value);
  }

  public static Bid pair(Card.Value value) {
    return new NOfAKindBid(Type.PAIR, 2, "two", value);
  }

  public static Bid threeOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.THREE_OF_A_KIND, 3, "three", value);
  }

  public static Bid fourOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.FOUR_OF_A_KIND, 4, "four", value);
  }

  public static Bid fiveOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.FIVE_OF_A_KIND, 5, "five", value);
  }

  public static Bid sixOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.SIX_OF_A_KIND, 6, "six", value);
  }

  public static Bid sevenOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.SEVEN_OF_A_KIND, 7, "seven", value);
  }

  public static Bid eightOfAKind(Card.Value value) {
    return new NOfAKindBid(Type.EIGHT_OF_A_KIND, 8, "eight", value);
  }

  public static Bid callBluff() {
    return new CallBluffBid();
  }
}

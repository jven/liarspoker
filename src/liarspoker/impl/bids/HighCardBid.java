package liarspoker.impl.bids;

import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.RevealedPosition;

final class HighCardBid extends Bid {

  private final Card.Value highCardValue;

  public HighCardBid(Card.Value highCardValue) {
    super(Bid.Type.HIGH_CARD);

    if (highCardValue.isWild()) {
      throw new IllegalArgumentException("Can't bid a wild high card.");
    }
    this.highCardValue = highCardValue;
  }

  @Override
  public boolean canPlayAfterOtherBidOfSameType(Bid other) {
    HighCardBid otherHighCardBid = (HighCardBid) other;
    return highCardValue.isMoreValuableThan(otherHighCardBid.highCardValue);
  }

  @Override
  public boolean existsInRevealedPosition(RevealedPosition revealedPosition) {
    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      for (int j = 0; j < hand.size(); j++) {
        Card c = hand.get(j);
        if (c.isWild() || c.getValue() == highCardValue) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public String getBidMessageForPlayer(int player) {
    return "Player " + player + " bids one " + highCardValue + ".";
  }
}

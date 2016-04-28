package liarspoker.impl.bids;

import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.RevealedPosition;

final class TwoPairBid extends Bid {

  private final Card.Value highPairValue;
  private final Card.Value lowPairValue;

  public TwoPairBid(Card.Value highPairValue, Card.Value lowPairValue) {
    super(Bid.Type.TWO_PAIR);
    if (highPairValue.isWild() || lowPairValue.isWild()) {
      throw new IllegalArgumentException("Can't bid a two pair with wild value.");
    }
    if (!highPairValue.isMoreValuableThan(lowPairValue)) {
      throw new IllegalArgumentException("High pair value must be greater than low pair value.");
    }

    this.highPairValue = highPairValue;
    this.lowPairValue = lowPairValue;
  }

  @Override
  protected boolean canPlayAfterOtherBidOfSameType(Bid other) {
    TwoPairBid twoPairBid = (TwoPairBid) other;
    return highPairValue.isMoreValuableThan(twoPairBid.highPairValue) ||
        highPairValue == twoPairBid.highPairValue &&
            lowPairValue.isMoreValuableThan(twoPairBid.lowPairValue);
  }

  @Override
  public boolean existsInRevealedPosition(RevealedPosition revealedPosition) {
    int numHigh = 0;
    int numLow = 0;
    int numWild = 0;

    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      for (int j = 0; j < hand.size(); j++) {
        Card c = hand.get(j);
        if (c.getValue() == highPairValue) {
          numHigh++;
        } else if (c.getValue() == lowPairValue) {
          numLow++;
        } else if (c.isWild()) {
          numWild++;
        }
      }
    }

    int wildsNeeded = Math.max(2 - numHigh, 0) + Math.max(2 - numLow,  0);
    return numWild >= wildsNeeded;
  }

  @Override
  public String getBidMessageForPlayer(int player) {
    return "Player " + player + " bids two " + highPairValue + "s and two " + lowPairValue + "s.";
  }

}

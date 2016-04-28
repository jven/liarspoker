package liarspoker.impl.bids;

import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.RevealedPosition;

final class PairBid extends Bid {

  private final Card.Value pairValue;

  public PairBid(Card.Value pairValue) {
    super(Bid.Type.PAIR);

    if (pairValue.isWild()) {
      throw new IllegalArgumentException("Can't bid a wild pair.");
    }
    this.pairValue = pairValue;
  }

  @Override
  public boolean canPlayAfterOtherBidOfSameType(Bid other) {
    PairBid pairBid = (PairBid) other;
    return pairValue.isMoreValuableThan(pairBid.pairValue);
  }

  @Override
  public boolean existsInRevealedPosition(RevealedPosition revealedPosition) {
    int numFound = 0;

    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      for (int j = 0; j < hand.size(); j++) {
        Card c = hand.get(j);
        if (c.isWild() || c.getValue() == pairValue) {
          numFound++;
          if (numFound >= 2) {
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public String getBidMessageForPlayer(int player) {
    return "Player " + player + " bids two " + pairValue + "s.";
  }
}

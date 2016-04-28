package liarspoker.impl.bids;

import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.RevealedPosition;

final class NOfAKindBid extends Bid {

  private final int n;
  private final String nLabel;
  private final Card.Value value;

  public NOfAKindBid(Bid.Type type, int n, String nLabel, Card.Value value) {
    super(type);

    if (value.isWild()) {
      throw new IllegalArgumentException("Can't bid a wild n of a kind.");
    }
    this.n = n;
    this.nLabel = nLabel;
    this.value = value;
  }

  @Override
  public boolean canPlayAfterOtherBidOfSameType(Bid other) {
    NOfAKindBid nOfAKindBid = (NOfAKindBid) other;
    return value.isMoreValuableThan(nOfAKindBid.value);
  }

  @Override
  public boolean existsInRevealedPosition(RevealedPosition revealedPosition) {
    int numFound = 0;

    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      for (int j = 0; j < hand.size(); j++) {
        Card c = hand.get(j);
        if (c.isWild() || c.getValue() == value) {
          numFound++;
          if (numFound >= n) {
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public String getBidMessageForPlayer(int player) {
    return nLabel == "one"
        ? "Player " + player + " bids one " + value + "."
        : "Player " + player + " bids " + nLabel + " " + value + "s.";
  }
}

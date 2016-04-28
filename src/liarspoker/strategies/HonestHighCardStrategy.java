package liarspoker.strategies;

import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.History;
import liarspoker.common.Strategy;
import liarspoker.impl.bids.Bids;

public final class HonestHighCardStrategy implements Strategy {

  @Override
  public Bid playBidForHistory(History history) {
    // Find the highest card in my hand.
    List<Card> myHand = history.getMyHand();
    Card.Value myHighestValue = Card.Value.THREE;
    for (int i = 0; i < myHand.size(); i++) {
      Card c = myHand.get(i);
      if (c.isWild()) {
        myHighestValue = Card.Value.ACE;
      } else if (c.getValue().isMoreValuableThan(myHighestValue)) {
        myHighestValue = c.getValue();
      }
    }

    // If I can, bid my highest card. Otherwise, BS!
    Bid myBid = Bids.highCard(myHighestValue);
    Bid lastBid = history.getLastBid();
    return lastBid == null || myBid.canPlayAfterOtherBid(history.getLastBid())
        ? myBid
        : Bids.callBluff();
  }

  @Override
  public String getDescription() {
    return "bid my highest card";
  }
}

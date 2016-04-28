package liarspoker.impl.bids;

import liarspoker.common.Bid;
import liarspoker.common.RevealedPosition;

final class CallBluffBid extends Bid {

  public CallBluffBid() {
    super(Bid.Type.BLUFF_CALL);
  }

  @Override
  public boolean canPlayAfterOtherBidOfSameType(Bid other) {
    throw new IllegalStateException("Attempted to compare a bluff call to another bid.");
  }

  @Override
  public boolean existsInRevealedPosition(RevealedPosition revealedPosition) {
    throw new IllegalStateException("Attempted to find whether a bluff call exists in a position.");
  }

  @Override
  public String getBidMessageForPlayer(int player) {
    return "Player " + player + " says 'BS!'";
  }
}

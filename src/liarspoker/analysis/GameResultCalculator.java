package liarspoker.analysis;

import java.util.List;

import liarspoker.analysis.GameResult.Reason;
import liarspoker.common.Bid;
import liarspoker.common.RevealedPosition;
import liarspoker.common.Strategy;
import liarspoker.impl.MutableHistory;

public final class GameResultCalculator {

  private final boolean throwForIllegalBids;

  public GameResultCalculator(boolean throwForIllegalBids) {
    this.throwForIllegalBids = throwForIllegalBids;
  }

  public GameResult calculateGameResult(
      RevealedPosition revealedPosition, List<Strategy> strategies) {
    int numPlayers = revealedPosition.getNumPlayers();
    if (strategies.size() != numPlayers) {
      throw new IllegalArgumentException("Incorrent number of strategies.");
    }

    MutableHistory history = new MutableHistory(revealedPosition);

    Bid penultimateBid = null;
    Bid lastBid = null;
    while (penultimateBid == null || lastBid == null || !lastBid.isBluffCall()) {
      Strategy strategy = strategies.get(history.getMyPlayer());
      Bid bid = strategy.playBidForHistory(history);
      if (lastBid != null && !bid.canPlayAfterOtherBid(lastBid) ||
          lastBid == null && bid.isBluffCall()) {
        // The player made an illegal bid.
        if (throwForIllegalBids) {
          throw new IllegalStateException(
              "Player " + history.getMyPlayer() + " made an illegal bid.");
        }
        return new GameResult(
            Reason.ILLEGAL_BID,
            history.getMyPlayer() /* loser */,
            revealedPosition,
            history.getAllPreviousPlayerBids());
      }

      history.iterateWithBid(bid);
      penultimateBid = lastBid;
      lastBid = bid;
    }

    // The last player called a bluff. Either that player or the one before loses.
    Reason reasonForLoss;
    int loserOffset;
    if (penultimateBid.existsInRevealedPosition(revealedPosition)) {
      reasonForLoss = Reason.CALLED_BLUFF_ON_EXISTING_BID;
      loserOffset = 1;
    } else {
      reasonForLoss = Reason.BLUFFED_AND_CALLED;
      loserOffset = 2;
    }

    int loser = (history.getMyPlayer() - loserOffset + numPlayers) % numPlayers;
    return new GameResult(
        reasonForLoss, loser, revealedPosition, history.getAllPreviousPlayerBids());
  }
}

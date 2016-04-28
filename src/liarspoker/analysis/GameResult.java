package liarspoker.analysis;

import java.util.List;

import liarspoker.common.PlayerBid;
import liarspoker.common.RevealedPosition;

public final class GameResult {

  public enum Reason {
    BLUFFED_AND_CALLED {
      @Override
      public String getMessageForPlayer(int player) {
        return "Player " + player + " bluffed and was called!";
      }
    },
    CALLED_BLUFF_ON_EXISTING_BID {
      @Override
      public String getMessageForPlayer(int player) {
        return "Player " + player + " called a bluff but the bid was correct!";
      }
    },
    ILLEGAL_BID {
      @Override
      public String getMessageForPlayer(int player) {
        return "Player " + player + " made an illegal bid!";
      }
    };

    public abstract String getMessageForPlayer(int player);
  }

  public final Reason reasonForLoss;
  public final RevealedPosition revealedPosition;
  public final int loser;
  public final List<PlayerBid> playerBids;

  public GameResult(
      Reason reasonForLoss,
      int loser,
      RevealedPosition revealedPosition,
      List<PlayerBid> playerBids) {
    this.reasonForLoss = reasonForLoss;
    this.loser = loser;
    this.revealedPosition = revealedPosition;
    this.playerBids = playerBids;
  }
}

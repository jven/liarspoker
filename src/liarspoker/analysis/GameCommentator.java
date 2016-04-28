package liarspoker.analysis;

import java.util.List;

import liarspoker.common.Card;
import liarspoker.common.PlayerBid;
import liarspoker.common.RevealedPosition;
import liarspoker.common.Strategy;

public final class GameCommentator {

  public void printCommentaryForGameResult(GameResult gameResult, List<Strategy> strategies) {
    if (gameResult.revealedPosition.getNumPlayers() != strategies.size()) {
      throw new IllegalArgumentException("Number of players doesn't match number of strategies.");
    }

    RevealedPosition revealedPosition = gameResult.revealedPosition;
    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      System.out.println("Player " + i + " has " + Card.handToString(hand) + " playing a \""
          + strategies.get(i).getDescription() + "\" strategy.");
    }

    List<PlayerBid> playerBids = gameResult.playerBids;
    for (int i = 0; i < playerBids.size(); i++) {
      PlayerBid playerBid = playerBids.get(i);
      System.out.println(playerBid.bid.getBidMessageForPlayer(playerBid.player));
    }

    System.out.println(gameResult.reasonForLoss.getMessageForPlayer(gameResult.loser));
  }
}

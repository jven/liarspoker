package liarspoker.analysis;

import java.util.List;

import liarspoker.common.Card;
import liarspoker.common.PlayerBid;
import liarspoker.common.RevealedPosition;

public final class GameCommentator {

  public void printCommentaryForGameResult(GameResult gameResult) {
    RevealedPosition revealedPosition = gameResult.revealedPosition;
    for (int i = 0; i < revealedPosition.getNumPlayers(); i++) {
      List<Card> hand = revealedPosition.getHandForPlayer(i);
      System.out.println("Player " + i + " has " + Card.handToString(hand) + ".");
    }

    List<PlayerBid> playerBids = gameResult.playerBids;
    for (int i = 0; i < playerBids.size(); i++) {
      PlayerBid playerBid = playerBids.get(i);
      System.out.println(playerBid.bid.getBidMessageForPlayer(playerBid.player));
    }

    System.out.println(gameResult.reasonForLoss.getMessageForPlayer(gameResult.loser));
  }
}

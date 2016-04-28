package liarspoker.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import liarspoker.common.Bid;
import liarspoker.common.Card;
import liarspoker.common.History;
import liarspoker.common.PlayerBid;
import liarspoker.common.Position;
import liarspoker.common.RevealedPosition;

public final class MutableHistory implements History {

  private final RevealedPosition revealedPosition;
  private int currentPlayer;
  private List<PlayerBid> previousPlayerBids;

  public MutableHistory(RevealedPosition revealedPosition) {
    this.revealedPosition = revealedPosition;
    currentPlayer = 0;
    previousPlayerBids = new ArrayList<PlayerBid>();
  }

  public void iterateWithBid(Bid bid) {
    previousPlayerBids.add(new PlayerBid(currentPlayer, bid));
    currentPlayer = (currentPlayer + 1) % revealedPosition.getNumPlayers();
  }

  @Override
  public int getMyPlayer() {
    return currentPlayer;
  }

  @Override
  public List<Card> getMyHand() {
    return Collections.unmodifiableList(revealedPosition.getHandForPlayer(currentPlayer));
  }

  @Override
  public Position getPosition() {
    return revealedPosition;
  }

  @Override
  public Bid getLastBid() {
    int numBids = previousPlayerBids.size();
    return numBids > 0 ? previousPlayerBids.get(numBids - 1).bid : null;
  }

  @Override
  public List<PlayerBid> getAllPreviousPlayerBids() {
    return Collections.unmodifiableList(previousPlayerBids);
  }

}

package liarspoker.common;

import java.util.List;

public interface History {

  public int getMyPlayer();

  public List<Card> getMyHand();

  public Position getPosition();

  public Bid getLastBid();

  public List<PlayerBid> getAllPreviousPlayerBids();
}

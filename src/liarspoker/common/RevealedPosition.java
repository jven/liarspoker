package liarspoker.common;

import java.util.List;

public interface RevealedPosition extends Position {

  public List<Card> getHandForPlayer(int player);
}

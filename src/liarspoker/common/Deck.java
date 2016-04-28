package liarspoker.common;

public interface Deck {

  public int getNumTotalCards();

  public boolean isEmpty();

  public Card draw();
}

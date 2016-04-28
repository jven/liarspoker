package liarspoker.common;

import java.util.List;

public final class Card {

  private final Value value;
  private final Suit suit;

  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  public enum Value {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "T"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");

    private final int v;
    private final String s;

    private Value(int v, String s) {
      this.v = v;
      this.s = s;
    }

    public boolean isWild() {
      // Deuces are wild!
      return v == 2;
    }

    public boolean isMoreValuableThan(Value value) {
      return v > value.v;
    }
  }

  public enum Suit {
    HEART("h"),
    DIAMOND("d"),
    CLUB("c"),
    SPADE("s");

    private final String s;

    private Suit(String s) {
      this.s = s;
    }
  }

  public Value getValue() {
    return value;
  }

  public Suit getSuit() {
    return suit;
  }

  public boolean isWild() {
    return value.isWild();
  }

  @Override
  public String toString() {
    return value.s + suit.s;
  }

  public static String handToString(List<Card> hand) {
    String s = "[";
    for (int i = 0; i < hand.size(); i++) {
      s += hand.get(i).toString();
      if (i < hand.size() - 1) {
        s += ", ";
      }
    }
    return s + "]";
  }
}

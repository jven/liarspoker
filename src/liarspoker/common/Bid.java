package liarspoker.common;

public abstract class Bid {

  public enum Type {
    HIGH_CARD(0),
    PAIR(1),
    TWO_PAIR(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8),
    FIVE_OF_A_KIND(9),
    SIX_OF_A_KIND(10),
    SEVEN_OF_A_KIND(11),
    EIGHT_OF_A_KIND(12),
    BLUFF_CALL(13);

    private final int rank;

    private Type(int rank) {
      this.rank = rank;
    }
  }

  private final Type type;

  protected Bid(Type type) {
    this.type = type;
  }

  public final boolean isBluffCall() {
    return type == Type.BLUFF_CALL;
  }

  public final boolean canPlayAfterOtherBid(Bid other) {
    return type != other.type
        ? type.rank > other.type.rank
        : canPlayAfterOtherBidOfSameType(other);
  }

  protected abstract boolean canPlayAfterOtherBidOfSameType(Bid other);

  public abstract boolean existsInRevealedPosition(RevealedPosition revealedPosition);

  public abstract String getBidMessageForPlayer(int player);
}

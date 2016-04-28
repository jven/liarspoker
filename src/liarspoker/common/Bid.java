package liarspoker.common;

public abstract class Bid {

  public enum Type {
    HIGH_CARD(0),
    PAIR(1),
    BLUFF_CALL(9999);

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
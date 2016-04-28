package liarspoker.common;

public interface Strategy {

  public Bid playBidForHistory(History history);

  public String getDescription();
}

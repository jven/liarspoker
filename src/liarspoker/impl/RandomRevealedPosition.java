package liarspoker.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import liarspoker.common.Card;
import liarspoker.common.Deck;
import liarspoker.common.RevealedPosition;

public final class RandomRevealedPosition implements RevealedPosition {

  private final List<List<Card>> hands;

  public RandomRevealedPosition(Deck deck, int[] cardsPerPlayer) {
    hands = new ArrayList<List<Card>>();
    for (int i = 0; i < cardsPerPlayer.length; i++) {
      List<Card> hand = new ArrayList<Card>();
      for (int j = 0; j < cardsPerPlayer[i]; j++) {
        hand.add(deck.draw());
      }
      hands.add(hand);
    }
  }

  @Override
  public int getNumPlayers() {
    return hands.size();
  }

  @Override
  public int getNumCardsForPlayer(int player) {
    return getHandForPlayer(player).size();
  }

  @Override
  public List<Card> getHandForPlayer(int player) {
    if (player < 0 || player >= getNumPlayers()) {
      throw new IllegalArgumentException("Invalid player.");
    }

    return Collections.unmodifiableList(hands.get(player));
  }
}

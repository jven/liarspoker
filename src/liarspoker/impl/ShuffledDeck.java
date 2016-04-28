package liarspoker.impl;

import liarspoker.common.Card;
import liarspoker.common.Deck;

public final class ShuffledDeck implements Deck {

  private static final int TOTAL_CARDS = Card.Value.values().length * Card.Suit.values().length;

  private final int[] cards;
  private int cardsLength;

  public ShuffledDeck() {
    cards = new int[TOTAL_CARDS];
    for (int i = 0; i < TOTAL_CARDS; i++) {
      cards[i] = i;
    }
    cardsLength = TOTAL_CARDS;
  }

  @Override
  public int getNumTotalCards() {
    return TOTAL_CARDS;
  }

  @Override
  public boolean isEmpty() {
    return cardsLength == 0;
  }

  @Override
  public Card draw() {
    if (isEmpty()) {
      throw new IllegalStateException("Called draw on an empty deck.");
    }

    int drawnCardIndex = (int) (Math.random() * cardsLength);
    int drawnCardNumber = cards[drawnCardIndex];
    cards[drawnCardIndex] = cards[cardsLength - 1];
    cardsLength--;

    return cardFromNumber(drawnCardNumber);
  }

  private static Card cardFromNumber(int cardNumber) {
    if (cardNumber < 0 || cardNumber >= TOTAL_CARDS) {
      throw new IllegalArgumentException("Invalid card number.");
    }

    Card.Value[] values = Card.Value.values();
    Card.Suit[] suits = Card.Suit.values();
    return new Card(values[cardNumber / suits.length], suits[cardNumber % suits.length]);
  }
}

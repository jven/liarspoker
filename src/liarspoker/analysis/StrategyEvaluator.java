package liarspoker.analysis;

import java.util.List;

import liarspoker.common.Deck;
import liarspoker.common.Strategy;
import liarspoker.impl.RandomRevealedPosition;
import liarspoker.impl.ShuffledDeck;

public class StrategyEvaluator {

  private final GameResultCalculator calculator;

  public StrategyEvaluator(GameResultCalculator calculator) {
    this.calculator = calculator;
  }

  public void evaluateStrategies(List<Strategy> strategies, int[] cardsPerPlayer, int numGames) {
    if (cardsPerPlayer.length != strategies.size()) {
      throw new IllegalArgumentException("Number of players doesn't match number of strategies.");
    }

    int[] losses = new int[cardsPerPlayer.length];
    for (int i = 0; i < numGames; i++) {
      Deck deck = new ShuffledDeck();
      GameResult gameResult = calculator.calculateGameResult(
          new RandomRevealedPosition(deck, cardsPerPlayer), strategies);
      losses[gameResult.loser]++;
    }

    System.out.println("Out of " + numGames + " games:");
    for (int i = 0; i < cardsPerPlayer.length; i++) {
      System.out.println("Player " + i + " lost " + losses[i] + " games playing a \""
          + strategies.get(i).getDescription() + "\" strategy.");
    }
  }
}

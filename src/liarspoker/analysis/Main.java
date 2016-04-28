package liarspoker.analysis;

import java.util.ArrayList;
import java.util.List;

import liarspoker.common.Strategy;
import liarspoker.impl.RandomRevealedPosition;
import liarspoker.impl.ShuffledDeck;
import liarspoker.strategies.HonestHighCardStrategy;

public class Main {

  public static void main(String[] args) {
    // Play one game and commentate it.
    GameResultCalculator calculator = new GameResultCalculator(true /* throwForIllegalBids */);
    int[] cardsPerPlayer = new int[] {1, 1, 1};
    List<Strategy> strategies = new ArrayList<Strategy>();
    strategies.add(new HonestHighCardStrategy());
    strategies.add(new HonestHighCardStrategy());
    strategies.add(new HonestHighCardStrategy());

    GameResult gameResult = calculator.calculateGameResult(
        new RandomRevealedPosition(new ShuffledDeck(), cardsPerPlayer), strategies);
    new GameCommentator().printCommentaryForGameResult(gameResult);

    // Play a bunch of games and see who loses more.
    int numGames = 10000;
    new StrategyEvaluator(calculator).evaluateStrategies(strategies, cardsPerPlayer, numGames);
  }
}

package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Enum of predefined Bots, with the complex combination of strategies.
 */
public enum PredefinedBot {

  ADVANCED_BOT(new AdvancedBot(new ArrayList<>(Arrays.asList(
    new BaseScoreStrategy(),
    new CornerStrategy(),
    new AdjacentIndicatorStrategy(),
    new FlipCountStrategy()
  ))));

  private final IBot bot;

  PredefinedBot(IBot bot) {
    this.bot = bot;
  }

  /**
   * Get the bot of the enum.
   *
   * @return The bot.
   */
  public IBot getBot() {
    return bot;
  }
}


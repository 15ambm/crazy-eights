import java.util.HashMap;
import java.util.Map;

abstract class Game {

    // tracking game state
    Pile gamePile;
    Pile discardPile;
    Pile inPlayPile;
    Map<Integer, Pile> hands;
    Integer numberOfPlayers;
    Integer currentPlayerId;
    static final InputHandler inputHandler = new InputHandler();

    // Running a game
    public Game(Pile initialDeck, Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.gamePile = initialDeck;
        this.discardPile = new Pile();
        this.inPlayPile = new Pile();
        this.hands = new HashMap<>();
    }

    public abstract void setupGame();

    public abstract void runGame();

    // Checking gamestate
    public void printGamePile() {
        this.gamePile.printPile();
    }

    public Integer getCurrentPlayer() {
        return currentPlayerId + 1;
    }
    public void setNextPlayer() {
        this.currentPlayerId = (this.currentPlayerId + 1) % numberOfPlayers;
    }
}

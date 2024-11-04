import Exceptions.IllegalCardException;

public class CrazyEights extends Game {

    public CrazyEights(Pile initialDeck, Integer numPlayers) {
        super(initialDeck, numPlayers);
    }

    @Override
    public void setupGame() {
        // Shuffle the initial game pile
        gamePile.shuffle();
        // The first player to start
        this.currentPlayerId = 0;
        // Initialize the hand of each player
        for (int i = 0; i < this.numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1) + " draws their hand");
            Pile hand = new Pile();
            // each player begins by drawing 5 cards
            for (int j = 0; j < 5; j++) {
                hand.addCard(gamePile.drawCard());
            }
            hand.printPile();
            hands.put(i, hand);
        }
        inPlayPile.addCard(gamePile.drawCard());
        inPlayPile.printTopCard();
    }

    public void runGame() {
        setupGame();
        String input = "";
        while (!input.equals("q")) {
            System.out.println("Player " + getCurrentPlayer() + "s turn");
            input = inputHandler.getInput();
            try {
                processInput(input);
            } catch (IllegalCardException e) {
                System.out.println("Illegal card input");
            }
            System.out.println("-----------------------------------");
        }
    }

    private void processInput(String input) throws IllegalCardException {
        if (input.equals("print game pile")) printGamePile();
        if (input.contains("hand")) seeHand(currentPlayerId);
        if (input.contains("play")) playACard(input);
        if (input.contains("draw")) drawCard();
    }

    private void seeHand(Integer playerId) {
        System.out.println("Hand of Player " + getCurrentPlayer());
        hands.get(playerId).printPile();
    }
    private void playACard(String input) {
        System.out.println("Player " + getCurrentPlayer() + " plays");
        Card inputCard = inputHandler.getCardInput(input);
        Pile hand = hands.get(currentPlayerId);

        if (inputCard != null) {
            // check if player has this card
            Card card = hand.getCard(inputCard);
            if (card != null) {
                // Game logic for playing a card
                inPlayPile.addCard(card);
                System.out.println("Player " + getCurrentPlayer() + " has played " + card);
                setNextPlayer();
            } else {
                System.out.println("Player " + getCurrentPlayer() + " does not have that card");
            }
        } else {
            System.out.println("Invalid input, please try again");
        }
    }
    private void drawCard() {
        Card card = gamePile.drawCard();
        if (card == null) {
            gamePile.addCards(discardPile.cards);
            gamePile.shuffle();
            System.out.println("Game pile is empty, shuffling the discard pile and adding to game pile");
        }
        Pile hand = hands.get(currentPlayerId);
        hand.addCard(card);
        System.out.println("Player " + getCurrentPlayer() + " draws " + card);
        setNextPlayer();
    }
}

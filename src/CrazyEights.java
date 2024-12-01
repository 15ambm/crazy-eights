import Exceptions.IllegalCardException;

public class CrazyEights extends Game {

    String wildCardSuit;
    public CrazyEights(Pile initialDeck, Integer numPlayers) {
        super(initialDeck, numPlayers);
        wildCardSuit = null;
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
        //inPlayPile.printTopCard();
    }

    public void runGame() {
        setupGame();
        String input = "";
        while (!input.equals("q")) {
            System.out.println("Player " + getCurrentPlayer() + "s turn");
            inPlayPile.printTopCard();
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
        // System.out.println("Player " + getCurrentPlayer() + " plays");
        Card inputCard = inputHandler.getCardInput(input);
        Pile hand = hands.get(currentPlayerId);

        if (inputCard != null) {
            // check if player has this card
            Card playCard = hand.findCard(inputCard);
            if (playCard != null) {
                // Game logic for playing a card
                if (validAction(playCard)) {
                    inPlayPile.addCard(playCard);
                    hand.drawCard(playCard);
                    System.out.println("Player " + getCurrentPlayer() + " has played " + playCard);
                    setNextPlayer();
                }else {
                    System.out.println("Card " + playCard + " is not a valid play option");
                }
            } else {
                System.out.println("Player " + getCurrentPlayer() + " does not have that card");
            }
        } else {
            System.out.println("Invalid input, please try again");
        }
    }

    // Checks if the given card can be played, based on card on top of game pile
    private boolean validAction(Card card) {
        Card topCard = inPlayPile.getTopCard();

        // What if game pile is empty? Can this be?
        if (topCard == null) return false;

        if (card.value == 8) {
            String input = null;
            do {
                input = inputHandler.getWildCardInput();
            } while(input == null);
            wildCardSuit = input;
            return true;
        }

        if (topCard.value == 8 && card.suit.equals(wildCardSuit)) return true;

        return card.suit.equals(topCard.suit) || card.value == topCard.value;
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

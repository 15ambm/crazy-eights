import Exceptions.IllegalCardException;

import java.util.*;

class Pile {
    // We could make this a stack for faster draws
    List<Card> cards;
    private static final Set<String> suits = new HashSet<>(Set.of("H","D","C","S"));
    Pile() {
        cards = new ArrayList<>();
    }

    public static Pile createClassicDeck() throws IllegalCardException {
        Pile classicDeck = new Pile();
        // Create a standard deck of 52 cards
        for (int i = 2; i <= 14; i++ ){
            for (String suit : suits.stream().toList()) {
                Card card = new Card(i, suit);
                classicDeck.addCard(card);
            }
        }
        return classicDeck;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    // Draw from top of pile
    public Card drawCard() {
        if (!this.cards.isEmpty())
            return this.cards.remove(this.cards.size()-1);
        else
            return null;
    }
    // Draw specific card if its in the hand
    public Card drawCard(Card searchCard)  {
        //System.out.println(" getCard() value: " + searchCard.value + " suit " + searchCard.suit);
        for (Card card : cards) {
            if (card.equals(searchCard)) {
                //System.out.println("Card compare " + searchCard + " " + card);
                cards.remove(card);
                return card;
            }
        }
        return null;
    }

    // See if a card exists in a pile and get a reference
    public Card findCard(Card searchCard)  {
        //System.out.println(" getCard() value: " + searchCard.value + " suit " + searchCard.suit);
        for (Card card : cards) {
            if (card.equals(searchCard)) {
                //System.out.println("Card compare " + searchCard + " " + card);
                return card;
            }
        }
        return null;
    }
    public Card getTopCard()  {
        if (!this.cards.isEmpty())
            return this.cards.get(this.cards.size()-1);
        else
            return null;
    }
    public static Set<String> getSuits() {return suits; }

    public void printPile() {
        for (Card card : cards) System.out.println(card.toString());
        System.out.println("-----------------------------------\n");
    }

    public void printTopCard() { System.out.println("Top card is: " + cards.get(cards.size()-1)); }
    public void shuffle() {
        System.out.println("Shuffling deck");
        Collections.shuffle(cards);
    }

}

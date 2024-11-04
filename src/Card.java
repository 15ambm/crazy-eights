import Exceptions.IllegalCardException;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Card {
    int value;
    String suit;
    static Map<Integer, String> faceValueMap = Map.ofEntries(
            Map.entry(11,"J"),
            Map.entry(12, "Q"),
            Map.entry(13, "K"),
            Map.entry(14,"A"),
            Map.entry(1, "A"));

    static Map<String, Integer> stringValueMap = Map.ofEntries(
            Map.entry("2",2),
            Map.entry("3",3),
            Map.entry("4",4),
            Map.entry("5",5),
            Map.entry("6",6),
            Map.entry("7",7),
            Map.entry("8",8),
            Map.entry("9",9),
            Map.entry("10",10),
            Map.entry("J",11),
            Map.entry("Q", 12),
            Map.entry("K", 13),
            Map.entry("A",14));

    static Set<String> suits = new HashSet<>(Set.of("H","D","C","S"));

    Card(Integer value, String suit) throws IllegalCardException {
        if (value <= 0 || value >= 15 || !suits.contains(suit))
            throw new IllegalCardException();
        this.value = value;
        this.suit = suit;
    }

    Card(String[] inputs) throws IllegalCardException {
        Integer value = Integer.parseInt(inputs[1]);
        String suit = inputs[2] ;
        if (value <= 0 || value >= 15 || !suits.contains(suit))
            throw new IllegalCardException();
        this.value = value;
        this.suit = suit;
    }

    public String toString() {
        if (value > 10 || value == 1) return faceValueMap.get(value) + " of " + suit;
        else return value + " of " + suit;
    }

    public boolean equals(Card card) {
        if (card == null) return false;
        if ((this.suit == null) ? (card.suit != null) : !card.suit.equals(this.suit)) return false;
        return this.value == card.value;
    }
}
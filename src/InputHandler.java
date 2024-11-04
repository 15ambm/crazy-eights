import Exceptions.IllegalCardException;

import java.util.Scanner;
public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public InputHandler() {}

    public String getInput() {
        System.out.print("Input: ");
        String input = scanner.nextLine();
        input = input.toLowerCase().trim();
        //System.out.println("    " + input);
        return input;
    }

    public Card getCardInput(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length != 3) return null;
        String valueString = inputs[1].toUpperCase().trim();
        Integer value = Card.stringValueMap.getOrDefault(valueString, -1);
        String suit = inputs[2].toUpperCase().trim();
        if (value <= 1 || value > 14 || !Card.suits.contains(suit)) return null;
        try {
            return new Card(value, suit);
        } catch (IllegalCardException e) {
            System.out.println("handleCardInput() Error creating card");
        } return null;
    }

}

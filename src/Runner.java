import Exceptions.IllegalCardException;

public class Runner {
    public void run() throws IllegalCardException {
        System.out.println("Hello world!");

        //Card oneHearts = new Card(1, "H");

//        try {
//            Card badCard = new Card(0,"H");
//        } catch(IllegalCardException e) {
//            System.out.println("Illegal card");
//        }


        Pile classicPile = Pile.createClassicDeck();
        Game game = new CrazyEights(classicPile, 2);
        game.runGame();
    }

}



package somethingfishygame;

/**
 *
 * @author memoonamalik
 */
import java.util.Scanner;

public class SomethingFishyGame {
    private final Deck deck;
    private final Player player;
    private final Player computer;

    public SomethingFishyGame() {
        deck = new Deck();
        player = new Player("Player");
        computer = new Player("Computer");
        dealInitialCards();
    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player.addCardToHand(deck.drawCard());
            computer.addCardToHand(deck.drawCard());
        }
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);

        while (deck.getSize() > 0) {
            // Player's turn
            System.out.println("Your hand: " + player.getHand());
            System.out.print("Ask for a rank: ");
            String rank = scanner.nextLine();

            Card card = computer.removeCardFromHand(rank);
            if (card != null) {
                System.out.println("Computer has a " + card);
                player.addCardToHand(card);
            } else {
                System.out.println("Go Fish!");
                player.addCardToHand(deck.drawCard());
            }

           
        }

        scanner.close();
    }

    public static void main(String[] args) {
        SomethingFishyGame game = new SomethingFishyGame();
        game.play();
    }
}

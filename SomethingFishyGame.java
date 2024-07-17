package somethingfishygame;

/*@author memoonamalik, melanieogwang */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SomethingFishyGame {
    private List<Player> players;
    private Deck deck;
    private int roundCounter;

    public SomethingFishyGame(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new Deck();
        roundCounter = 0;
        dealInitialCards();
    }

    private void dealInitialCards() {
        int cardsToDeal = 5;
        for (int i = 0; i < cardsToDeal; i++) {
            for (Player player : players) {
                player.addCardToHand(deck.dealCard());
            }
        }
    }

    private void printBanner(String message) {
        System.out.println("********************************************************************");
        System.out.println("*                                     *");
        System.out.println("*      " + message + "      *");
        System.out.println("*                                     *");
        System.out.println("********************************************************************");
    }

    private void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        
        printBanner("Welcome to Something Fishy Game!");
        
        while (!gameOver && roundCounter < 3) {
            roundCounter++;
            //printSeparator();
            System.out.println("~~~~~~~~~~~~~~~ Round " + roundCounter + " ~~~~~~~~~~~~~~~");
            printSeparator();
            
            for (Player player : players) {
                if (deck.cardsLeft() == 0) {
                    gameOver = true;
                    break;
                }

                System.out.println(player.getName() + "'s turn. Your hand: " + player.getHand());
                System.out.print("Ask for a rank: ");
                String rank = scanner.nextLine();

                boolean found = false;
                for (Player opponent : players) {
                    if (!opponent.equals(player) && opponent.hasRank(rank)) {
                        Card card = opponent.giveCard(rank);
                        player.addCardToHand(card);
                        found = true;
                        if (rank.equals("A")) {
                            System.out.println("You got an " + card);
                        } else {
                            System.out.println("You got a " + card);
                        }
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Go Fish!");
                    player.addCardToHand(deck.dealCard());
                }
                
                printSeparator();
            }
        }

        scanner.close();

        printBanner("Game over!");

        // Calculate and display scores
        System.out.println("Final Scores:");
        int maxScore = -1;
        List<Player> winners = new ArrayList<>();

        for (Player player : players) {
            int score = player.getHand().size();
            System.out.println(player.getName() + ": " + score + " cards");

            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == maxScore) {
                winners.add(player);
            }
        }

        // Determine and display the winners
        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0).getName() + " with " + maxScore + " cards!");
        } else {
            System.out.println("It's a tie between the following players:");
            for (Player winner : winners) {
                System.out.println(winner.getName() + " with " + maxScore + " cards!");
            }
        }
        //Thank players for playing.
        System.out.println("Thank you for playing!");
    }

    public static void main(String[] args) {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("Melanie");
        playerNames.add("Memoona");
        playerNames.add("Sagun");
        playerNames.add("Jas");

        SomethingFishyGame game = new SomethingFishyGame(playerNames);
        game.play();
    }
}

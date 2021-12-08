package sample.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private static final Integer MAX_USER_COUNT = 3;
    private static final ArrayList<BlackjackPlayer> humanPlayers = new ArrayList<BlackjackPlayer>();
    private static final BlackjackPlayer dealer = new Dealer();
    private static final Scanner scanner = new Scanner(System.in);
    private static Deck deck;


    public static void main(String[] args) {

        System.out.println("Welcome to sample.blackjack.Blackjack World");

        int numberOfPlayer;
        do {
            System.out.print("How many player for this game(1-" + MAX_USER_COUNT + ")? ");
            numberOfPlayer = scanner.nextInt();
        } while (numberOfPlayer > MAX_USER_COUNT || numberOfPlayer < 1);

        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.print("Please enter the name of the " + (i + 1) + " user ");
            humanPlayers.add(new BlackjackPlayer(scanner.next()));
        }


        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (BlackjackPlayer player : humanPlayers) {
                player.addCard(deck.retriveCard());
            }
            dealer.addCard(deck.retriveCard());
        }

        for (BlackjackPlayer player : humanPlayers) {
            do {
                System.out.println(player.getName() + ": your total point is " + player.calculatePoints());
                System.out.println(player.showHand());
                System.out.print("Do you want to Hit? Y/y");
                if ("Y".equalsIgnoreCase(scanner.next())) {
                    player.addCard(deck.retriveCard());
                    if (player.isBusted()) {
                        System.out.println("Sorry you are busted!");
                        System.out.println(player.showHand());
                        System.out.println();
                    }
                } else {
                    break;
                }
            } while (!player.isBusted());
        }

    }
}

package sample.blackjack;

import java.util.Scanner;

public class BlackjackClient {

    public static void main(String[] args) throws Exception {

        System.out.println("Start Blackjack client...");

        Blackjack blackjack = new Blackjack();

        int numberOfPlayer;
        do {
            System.out.print("How many player for this game(1-" + Blackjack.MAX_USER_COUNT + ")? ");
            numberOfPlayer = new Scanner(System.in).nextInt();
        } while (numberOfPlayer > Blackjack.MAX_USER_COUNT || numberOfPlayer < 1);

        String[] playerNames = new String[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.print("Please enter the name of the " + (i + 1) + " user ");
            playerNames[i] = new Scanner(System.in).nextLine();
        }

        blackjack.initPlayers(playerNames);
        blackjack.setDealer(new Dealer("DefaultDealer", new SimpleBlackjackPlayRule()));
        blackjack.beginGame();

        for (String playerName : blackjack.getPlayerNames()) {
            do {
                System.out.print(playerName + ": ");
                System.out.println(blackjack.showHand(playerName));
                System.out.print("Please enter Y(es) to Hit or any other keys to Stand ");
                if ("Y".equalsIgnoreCase(new Scanner(System.in).nextLine())) {
                    blackjack.hit(playerName);
                    if (blackjack.isBursted(playerName)) {
                        System.out.println();
                        System.out.println("Sorry, you are bursted!");
                        System.out.println(blackjack.showHand(playerName));
                        System.out.println();
                    }
                } else {
                    break;
                }
            } while (!blackjack.isBursted(playerName));
        }

        blackjack.beginDealerPlay();

        GameResult result = blackjack.generateGameResult();
        System.out.println();
        System.out.println("============ Game over, play results as following:");
        System.out.println("Game winner are:");
        for (BlackjackPlayer player : result.getWinner()) {
            System.out.print(player.getName() + ": ");
            System.out.println(player.showHand());
        }
        System.out.println();
        System.out.println("Game Loser are:");
        for (BlackjackPlayer player : result.getLoser()) {
            System.out.print(player.getName() + ": ");
            System.out.println(player.showHand());
        }

        System.out.println("Game completed");

    }
}

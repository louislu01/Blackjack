package sample.blackjack;

import java.util.*;

public class Blackjack {

    public static final int MAX_USER_COUNT = 3;
    public static final int BLACKJACK_SCORE = 21;
    private final List<BlackjackPlayer> humanPlayers = new ArrayList<BlackjackPlayer>();
    private final Map<String, BlackjackPlayer> humanPlayersMap = new HashMap<String, BlackjackPlayer>();
    private Dealer dealer = new Dealer("Dealer");
    private Deck deck = new Deck();

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void initPlayers(String[] names) throws Exception{

        if (names == null || names.length == 0 || names.length > MAX_USER_COUNT) {
            throw new Exception("Number of player is between 1 - 3");
        }

        humanPlayers.clear();
        humanPlayersMap.clear();

        for (String name : names) {
            BlackjackPlayer blackjackPlayer = new BlackjackPlayer(name);
            humanPlayers.add(blackjackPlayer);
            humanPlayersMap.put(name, blackjackPlayer);
        }
    }

    public void hit(String playerName) {
        BlackjackPlayer player = getBlackjackPlayer(playerName);
        if (player != null) {
            player.addCard(deck.assignCard());
        }
    }

    public boolean isBursted(String playerName) {
        BlackjackPlayer player = getBlackjackPlayer(playerName);
        if (player != null) {
            return player.isBursted();
        }
        return false;
    }

    public boolean isBlackjack(String playerName) {
        BlackjackPlayer player = getBlackjackPlayer(playerName);
        if (player != null) {
            return player.isBlackjack();
        }
        return false;
    }

    public String showHand(String playerName) {
        BlackjackPlayer player = getBlackjackPlayer(playerName);
        if (player != null) {
            return player.showHand();
        }
        return null;
    }

    public String[] getPlayerNames() {
        int playerNumber = humanPlayers.size();
        String[] playerNames = new String[playerNumber];

        for (int i = 0; i < playerNumber; i++) {
            playerNames[i] = humanPlayers.get(i).getName();
        }
        return playerNames;
    }

    public void initContext() {
        deck = new Deck();
        dealer.clearHand();
    }

    public void beginGame() throws Exception {

        if (humanPlayers.size() == 0) {
            throw new Exception("Please assign the players first!");
        }

        initContext();

        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (BlackjackPlayer player : humanPlayers) {
                player.addCard(deck.assignCard());
            }
            dealer.addCard(deck.assignCard());
        }
    }

    /**
     * Dealer going to play the card based on predefined rule
     * @return true if dealer was bursted.
     */
    public void beginDealerPlay() {
        while (dealer.wantToHit(humanPlayers)) {
            dealer.addCard(deck.assignCard());
        }
    }

    public GameResult generateGameResult() {
        List<BlackjackPlayer> winner = new ArrayList<BlackjackPlayer>();
        List<BlackjackPlayer> loser = new ArrayList<BlackjackPlayer>();

        if (dealer.isBursted()) {
            loser.add(dealer);
            for (BlackjackPlayer humanPlayer : humanPlayers) {
                if (!humanPlayer.isBursted()) {
                    winner.add(humanPlayer);
                } else {
                    loser.add(humanPlayer);
                }
            }
        } else {
            int dealerScore = dealer.calculateScore();
            for (BlackjackPlayer humanPlayer : humanPlayers) {
                int humanPlayerScore = humanPlayer.calculateScore();
                if (!humanPlayer.isBursted() && humanPlayerScore >= dealerScore) {
                    winner.add(humanPlayer);
                    if (humanPlayerScore == dealerScore) {
                        winner.add(dealer);
                    }
                } else {
                    loser.add(humanPlayer);
                }
            }
            if (winner.size() == 0 ) {
                winner.add(dealer);
            } else {
                loser.add(dealer);
            }
        }

        Collections.sort(winner, new Comparator<BlackjackPlayer>() {
            @Override
            public int compare(BlackjackPlayer a, BlackjackPlayer b) {
                return b.calculateScore() - a.calculateScore();
            }
        });
        return new GameResult(winner, loser);
    }

    public void startDefaultGame() {
        initContext();

        System.out.println("Welcome to sample.blackjack.Blackjack World");

        int numberOfPlayer;
        do {
            System.out.print("How many player for this game(1-" + MAX_USER_COUNT + ")? ");
            numberOfPlayer = new Scanner(System.in).nextInt();
        } while (numberOfPlayer > MAX_USER_COUNT || numberOfPlayer < 1);

        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.print("Please enter the name of the " + (i + 1) + " user ");
            BlackjackPlayer blackjackPlayer = new BlackjackPlayer(new Scanner(System.in).nextLine());
            humanPlayers.add(blackjackPlayer);
            humanPlayersMap.put(blackjackPlayer.getName(), blackjackPlayer);
        }

        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (BlackjackPlayer player : humanPlayers) {
                player.addCard(deck.assignCard());
            }
            dealer.addCard(deck.assignCard());
        }

        for (BlackjackPlayer player : humanPlayers) {
            do {
                System.out.println(player.getName() + ": your total point is " + player.calculateScore());
                System.out.println(player.showHand());
                System.out.print("Please enter Y(es) to Hit or any other keys to Stand");
                if ("Y".equalsIgnoreCase(new Scanner(System.in).nextLine())) {
                    player.addCard(deck.assignCard());
                    if (player.isBursted()) {
                        System.out.println("Sorry you are busted!");
                        System.out.println(player.showHand());
                        System.out.println();
                    }
                } else {
                    break;
                }
            } while (!player.isBursted());
        }

        beginDealerPlay();

        GameResult result = generateGameResult();
        System.out.println();
        System.out.println("============ Game over, play results are as following:");
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

    private BlackjackPlayer getBlackjackPlayer(String name) {
        return humanPlayersMap.get(name);
    }

    public static void main(String[] args) {

        System.out.println("Start default game...");
        Blackjack defaultGame = new Blackjack();
        defaultGame.startDefaultGame();
    }
}

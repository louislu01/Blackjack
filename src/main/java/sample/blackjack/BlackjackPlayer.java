package sample.blackjack;

import java.io.Serializable;

public class BlackjackPlayer implements Serializable {

    private final String name;
    private final Hand hand = new Hand();

    public BlackjackPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBlackjack() {
        return hand.calculateScore() == 21;
    }

    public boolean isBursted() {
        return hand.calculateScore() > 21;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public int getTotalPoints() {
        return hand.getTotalPoint();
    }

    public String showHand() {

        StringBuilder sb = new StringBuilder(hand.showHands());

        sb.append(" With Score: " + hand.calculateScore());

        return sb.toString();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void clearHand() {
        hand.clearHand();
    }
}

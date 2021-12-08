package sample.blackjack;

import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clearHand() {
        cards.clear();
    }

    public int calculatePoints() {
        boolean isContainAce = false;
        int totalPoints = 0;
        for (Card card : cards) {
            if (card.getRank() == Rank.Ace) {
                isContainAce = true;
            }
            totalPoints += card.getRank().getBlackJackPoint();
        }

        // adjust the points using ACS value 11 instead.
        if (isContainAce && totalPoints <= 11) {
            totalPoints += 10;
        }
        return totalPoints;
    }

    @Override
    public String toString() {
        return "sample.blackjack.Hand{" +
                "cards=" + cards +
                '}';
    }
}

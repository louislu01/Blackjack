package sample.blackjack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand implements Serializable {

    private final List<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clearHand() {
        cards.clear();
    }

    /**
     * Get total face point.
     *
     * @return total face point
     */
    public int getTotalPoint() {
        int totalPoints = 0;
        for (Card card : cards) {
            totalPoints += card.getRank().getBlackJackPoint();
        }
        return totalPoints;
    }

    /**
     * Cards have numeric point value, with face cards are value 10. An ace is either 1 or 11 to be used to
     * best advantage (highest total without busting 21).
     *
     * @return highest total points without busting 21 or busted points
     */
    public int calculateScore() {
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

    public String showHands() {
        return cards.toString();
    }
}

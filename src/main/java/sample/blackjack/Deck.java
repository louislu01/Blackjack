package sample.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> deck = new ArrayList<Card>();


    public Deck() {

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card retriveCard() {

        return deck.remove(0);
    }

    public int size() {
        return deck.size();
    }
}

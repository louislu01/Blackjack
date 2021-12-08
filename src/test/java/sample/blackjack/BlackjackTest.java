package sample.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.blackjack.*;

class BlackjackTest {

    @BeforeEach
    void setUp() {
        System.out.println("Start testing");
    }

    @Test
    public void testHandAceLogic() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.Ace, Suit.Clubs));
        hand.addCard(new Card(Rank.King, Suit.Clubs));
        Assertions.assertEquals(21, hand.calculatePoints());
    }

    @Test
    public void testBlackjackPlayerIsBlackjack() {
        BlackjackPlayer player = new BlackjackPlayer();
        player.addCard(new Card(Rank.Ace, Suit.Clubs));
        player.addCard(new Card(Rank.King, Suit.Clubs));

        Assertions.assertTrue(player.isBlackjack());
    }

    @Test
    public void testBlackjackPlayerIsBusted() {
        BlackjackPlayer player = new BlackjackPlayer();
        player.addCard(new Card(Rank.Ace, Suit.Clubs));
        player.addCard(new Card(Rank.King, Suit.Clubs));
        player.addCard(new Card(Rank.Jack, Suit.Clubs));
        player.addCard(new Card(Rank.Ace, Suit.Clubs));

        Assertions.assertTrue(player.isBusted());
    }

    @Test
    public void testDeckCreation() {
        Deck deck = new Deck();
        Assertions.assertEquals(56, deck.size());
        Card card = deck.retriveCard();
        Assertions.assertEquals(55, deck.size());
        Assertions.assertEquals(Rank.Joker, card.getRank());
    }
}
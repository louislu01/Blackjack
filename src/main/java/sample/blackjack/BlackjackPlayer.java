package sample.blackjack;

public class BlackjackPlayer {

    private String name;
    private final Hand hand = new Hand();

    public BlackjackPlayer() {
    }

    public BlackjackPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBlackjack() {
        return hand.calculatePoints() == 21;
    }

    public boolean isBusted() {
        return hand.calculatePoints() > 21;
    }

    public Integer calculatePoints() {
        return hand.calculatePoints();
    }

    public String showHand() {
        return hand.toString();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }
}

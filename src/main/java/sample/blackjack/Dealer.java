package sample.blackjack;

import java.util.List;

public class Dealer extends BlackjackPlayer {

    private DealerStrategy dealerStrategy = new SimpleBlackjackPlayRule();

    public Dealer(String name) {
        super(name);
    }

    public Dealer(String name, DealerStrategy dealerStrategy) {
        super(name);
        this.dealerStrategy = dealerStrategy;
    }

    public void setDealerStrategy(DealerStrategy dealerStrategy) {
        this.dealerStrategy = dealerStrategy;
    }

    public DealerStrategy getDealerStrategy() {
        return dealerStrategy;
    }

    public boolean wantToHit(List<BlackjackPlayer> humanPlayers) {
        return dealerStrategy.wantToHit(this, humanPlayers);
    }
}

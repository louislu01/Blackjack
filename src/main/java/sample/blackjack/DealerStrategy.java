package sample.blackjack;

import java.util.List;

public interface DealerStrategy {
    boolean wantToHit(BlackjackPlayer dealer, List<BlackjackPlayer> humanPlayers);
}

package sample.blackjack;

import java.util.List;

public class SimpleBlackjackPlayRule implements DealerStrategy {

    @Override
    public boolean wantToHit(BlackjackPlayer dealer, List<BlackjackPlayer> humanPlayers) {
        int MAX_STOP_HIT_SCORE = 16;
        int hightestScore = 0;
        if (!dealer.isBursted() && !dealer.isBlackjack()) {
            for (BlackjackPlayer player : humanPlayers) {
                int playerScore = player.calculateScore();
                if (playerScore < Blackjack.BLACKJACK_SCORE && playerScore > hightestScore) {
                    hightestScore = playerScore;
                }
            }
            int dealerScore = dealer.calculateScore();
            return dealerScore > hightestScore || dealerScore < MAX_STOP_HIT_SCORE || dealer.getTotalPoints() < MAX_STOP_HIT_SCORE;
        }

        return false;
    }

    public SimpleBlackjackPlayRule() {}
}

package sample.blackjack;

import java.io.Serializable;
import java.util.*;

public class GameResult implements Serializable {
    private List<BlackjackPlayer> winner = new ArrayList<BlackjackPlayer>();
    private List<BlackjackPlayer> loser = new ArrayList<BlackjackPlayer>();

    public GameResult(List<BlackjackPlayer> winner, List<BlackjackPlayer> loser) {
        if (winner != null) {
            this.winner = winner;
        }
        if (loser != null) {
            this.loser = loser;
        }

    }
    public List<BlackjackPlayer> getWinner() {
        return winner;
    }

    public List<BlackjackPlayer> getLoser() {
        return loser;
    }
}

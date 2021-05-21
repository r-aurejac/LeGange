package com.example.legange.Bloc;

import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;
import com.example.legange.UI.BlackJackFragment;
import com.example.legange.Navigation.ScreenType;

import java.util.ArrayList;


public class BlackJackBloc extends AbstractBloc {


    public int currentTotal, playerIndex;
    public ArrayList<Player> players;

    int highScore = 0;
    ArrayList<Player> bestPlayers;

    public BlackJackBloc(ScreenType nextScreen) {
        super(nextScreen);
        blocFragment = BlackJackFragment.newInstance(this);
        players = PlayersManager.getPlayerListCopy();
        bestPlayers = new ArrayList<>();
        currentTotal = 0;
        playerIndex = 0;
    }


    public int draw() {
        int rand = 1 + (int) (Math.random() * ((13 - 1) + 1));
        currentTotal = currentTotal + rand;
        players.get(playerIndex).setRule_score(currentTotal);
        return rand;
    }

    public boolean nextPlayer() {
        checkHighScore();
        if (playerIndex < players.size() - 1) {
            currentTotal = 0;
            playerIndex++;
            return true;
        } else {
            end();
            return false;
        }
    }

    private void end() {

        rewardBestPlayers();
    }

    public Player getCurrentPlayer() {
        return players.get(playerIndex);
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    void checkHighScore() {
        if (currentTotal > highScore) {
            bestPlayers.clear();
            bestPlayers.add(getCurrentPlayer());
        }
        if (currentTotal == highScore) {
            bestPlayers.add(getCurrentPlayer());
        }

        if (currentTotal == 21) {
            getCurrentPlayer().incrementScore(-1);
        } else if (currentTotal == 20) {
            getCurrentPlayer().incrementScore(1);
        } else if (currentTotal > 21) {
            getCurrentPlayer().incrementScore(4);
        }

    }

    void rewardBestPlayers() {
        for (Player player : bestPlayers) {
            player.addTickets(3);
        }

    }

}

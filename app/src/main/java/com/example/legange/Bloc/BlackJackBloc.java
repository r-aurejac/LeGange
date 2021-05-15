    package com.example.legange.Bloc;

    import com.example.legange.Player.Player;
    import com.example.legange.Player.PlayerList;
    import com.example.legange.UI.BlackJackFragment;
    import com.example.legange.Navigation.ScreenType;

    import java.util.ArrayList;


    public class BlackJackBloc extends AbstractBloc {



        public int currentTotal,playerIndex;
        public ArrayList<Player> players;

        public BlackJackBloc(ScreenType nextScreen) {
            super(nextScreen);
            blocFragment = BlackJackFragment.newInstance(this);
            players = PlayerList.getPlayerListCopy();
            currentTotal = 0;
            playerIndex = 0;
        }



        public int draw()
        {
            int rand = 1 + (int)(Math.random() * ((13 - 1) + 1));
            currentTotal = currentTotal + rand;
            players.get(playerIndex).setRule_score(currentTotal);
           return rand;
        }

        public boolean nextPlayer()
        {

            if(playerIndex < players.size()-1) {
                currentTotal = 0;
                playerIndex++;
                return true;
            }
            else
            {
                end();
                return false;
            }
        }

        private void end()
        {
            for(Player player : players) {
                if (player.getRule_score()==21)
                    player.incrementScore(-1);
                else if (player.getRule_score()==20)
                    player.incrementScore(1);
                else if (player.getRule_score()>21)
                    player.incrementScore(4);
            }
        }
        public Player getCurrentPlayer()
        {
            return players.get(playerIndex);
        }
        public int getCurrentTotal() {
            return currentTotal;
        }

    }

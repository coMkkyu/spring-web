package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        playerMap.forEach((s, player) -> {
            int dealerResult = dealer.getHand().getCardSum();
            int playerResult = player.getHand().getCardSum();

            if (dealerResult > 21 && playerResult < 22){
                player.win();
            }
            else if (playerResult > 21){
                player.lost();
            }
            else if (playerResult > dealerResult) {
                if(playerResult == 21){
                    player.blackjack();
                }
                else{
                    player.win();
                }
            }
            else if (playerResult == dealerResult) {
                if(dealerResult == 21) { //딜러가 블랙잭인경우로 플레이어와 동일한 점수일 때는 딜러의 승리
                    player.lost();
                }
                else { //블랙잭이 아니고 점수가 같을경우는 모두 tie
                    player.tie();
                }
            }
            else {
                player.lost();
            }

        });

        return true;
    }


}
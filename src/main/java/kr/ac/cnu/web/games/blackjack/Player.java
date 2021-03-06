package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NotEnoughBalanceException;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }
        balance -= bet;
        currentBet = bet;

        isPlaying = true;

    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win() {
        balance += currentBet * 2;
        balance -= currentBet;
    }

    public void blackjack() {
        balance += currentBet * 2.5;
        balance -= currentBet;
    }

    public void tie() {

    }

    public void lost() {
        balance -= currentBet;
    }

    public Card hitCard() {

        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
    }

    public void doubleDown() {
        hand.drawCard();
        this.isPlaying = false;
    }

}
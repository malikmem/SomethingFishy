
package somethingfishygame;

/**
 *
 * @author memoonamalik, melanieogwang
 */
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public Card giveCard(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public boolean hasRank(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }
}

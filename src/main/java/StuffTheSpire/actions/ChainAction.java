package StuffTheSpire.actions;

import StuffTheSpire.cards.AbstractLinkedCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRng;

public class ChainAction extends AbstractGameAction {
    private int amount;
    private ArrayList<AbstractLinkedCard> LinkedCards = new ArrayList<>();

    public ChainAction(int amount) {
        this.duration = Settings.ACTION_DUR_LONG;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
        this.amount = amount;
    }

    @Override
    public void update() {
        for (AbstractCard l : AbstractDungeon.player.hand.group) {
            if (l instanceof AbstractLinkedCard) {
                this.LinkedCards.add((AbstractLinkedCard) l);
            }
        }
        if (amount >= this.LinkedCards.size()) {
            for (AbstractLinkedCard l : this.LinkedCards) {
                l.chain();
            }
            this.LinkedCards.clear();
            this.isDone = true;
            return;
        } else {
            ArrayList<AbstractLinkedCard> playme = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                int random = cardRng.random(0, this.LinkedCards.size() - 1);
                playme.add(this.LinkedCards.get(random));
                this.LinkedCards.remove(random);
            }
            for (AbstractLinkedCard l : playme) {
                l.chain();
            }
            this.LinkedCards.clear();
            playme.clear();
            this.isDone = true;
            return;
        }
    }
}

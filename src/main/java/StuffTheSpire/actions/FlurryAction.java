package StuffTheSpire.actions;

import StuffTheSpire.cards.AbstractLinkedCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class FlurryAction extends AbstractGameAction {
    private int amount;
    private ArrayList<AbstractLinkedCard> LinkedCards = new ArrayList<>();

    public FlurryAction() {
        this.duration = Settings.ACTION_DUR_LONG;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
    }

    @Override
    public void update() {
        for (AbstractCard l : AbstractDungeon.player.discardPile.group) {
            if (l instanceof AbstractLinkedCard) {
                l.exhaustOnUseOnce = true;
            }
        }
        for (AbstractCard l : AbstractDungeon.player.drawPile.group) {
            if (l instanceof AbstractLinkedCard) {
                l.exhaustOnUseOnce = true;
            }
        }
        for (AbstractCard l : AbstractDungeon.player.hand.group) {
            if (l instanceof AbstractLinkedCard) {
                l.exhaustOnUseOnce = true;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ChainFromDrawPileAction(AbstractDungeon.player.drawPile.group.size()));
        AbstractDungeon.actionManager.addToBottom(new ChainInHandAction(AbstractDungeon.player.hand.group.size()));
        AbstractDungeon.actionManager.addToBottom(new ChainFromDiscardPileAction(AbstractDungeon.player.discardPile.group.size()));
        this.isDone = true;
    }
}

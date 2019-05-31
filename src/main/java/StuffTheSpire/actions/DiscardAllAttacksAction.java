package StuffTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardAllAttacksAction extends AbstractGameAction {
    public DiscardAllAttacksAction() {
        this.duration = Settings.ACTION_DUR_LONG;
        this.actionType = ActionType.DISCARD;
        this.isDone = false;
        amount = 0;
    }

    @Override
    public void update() {

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c));
            }
        }
        this.isDone = true;
    }
}

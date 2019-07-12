package StuffTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShuffleCardCostsAction extends AbstractGameAction {
    public ShuffleCardCostsAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        for (AbstractCard a : AbstractDungeon.player.hand.group) {
            int i = AbstractDungeon.cardRandomRng.random(0, 3);
            a.setCostForTurn(i);
            isDone = true;
        }
        isDone = true;
    }
}

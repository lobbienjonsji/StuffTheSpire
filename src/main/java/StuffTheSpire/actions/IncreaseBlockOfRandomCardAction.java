package StuffTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IncreaseBlockOfRandomCardAction extends AbstractGameAction {

    public IncreaseBlockOfRandomCardAction(int amount) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
        this.amount = amount;
    }

    @Override
    public void update() {
        CardGroup BlockCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.baseBlock > 0) {
                BlockCards.addToTop(c);
            }
        }
        if (!BlockCards.isEmpty()) {
            AbstractCard c = BlockCards.getRandomCard(true);
            c.baseBlock += this.amount;
            c.flash();
        }
        this.isDone = true;
    }
}

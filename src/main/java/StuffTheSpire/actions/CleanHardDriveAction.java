package StuffTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class CleanHardDriveAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CleanHardDriveAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private float startingDuration;

    public CleanHardDriveAction(int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
        this.amount = amount;
        this.startingDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        CardGroup tmpGroup;
        if (this.duration == this.startingDuration) {
            if (AbstractDungeon.player.drawPile.isEmpty() || this.amount <= 0) {
                this.isDone = true;
                return;
            }
            tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (int i = 0; i < AbstractDungeon.player.drawPile.size(); i++) {
                tmpGroup.addToTop(
                        AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
            }
            AbstractDungeon.gridSelectScreen.open(tmpGroup, amount, false, TEXT[0]);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.drawPile.moveToExhaustPile(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }

}


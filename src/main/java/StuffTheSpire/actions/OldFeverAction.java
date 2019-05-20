package StuffTheSpire.actions;

import StuffTheSpire.relics.Expresso;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldFeverAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());

    public OldFeverAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        AbstractDungeon.player.drawPile.update();
        AbstractDungeon.player.discardPile.update();
        logger.info(AbstractDungeon.player.drawPile.group.size());
        logger.info(AbstractDungeon.player.discardPile.group.size());
        if (AbstractDungeon.player.drawPile.group.size() < AbstractDungeon.player.discardPile.group.size()) {
            AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
            AbstractDungeon.actionManager.addToBottom(new DrawPileToHandAction(AbstractDungeon.player));
        }
        this.isDone = true;
    }
}

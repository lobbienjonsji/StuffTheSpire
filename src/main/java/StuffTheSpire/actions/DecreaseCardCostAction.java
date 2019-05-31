package StuffTheSpire.actions;

import StuffTheSpire.relics.Expresso;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class DecreaseCardCostAction extends AbstractGameAction {
    private int cardcost = 99;
    private ArrayList<AbstractCard> CheapestCards = new ArrayList();
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());

    public DecreaseCardCostAction()
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
    }
    @Override
    public void update() {
        logger.info(AbstractDungeon.player.hand.group.size());
        CheapestCards.clear();
        cardcost = 99;
        for (AbstractCard card: AbstractDungeon.player.hand.group) {
            if(card.cost < cardcost && card.cost>= 0)
            {
                CheapestCards.clear();
                CheapestCards.add(card);
                cardcost = card.cost;
            }
            else if(card.cost == cardcost)
            {
                CheapestCards.add(card);
            }
        }
        if(!CheapestCards.isEmpty())
        {
            int r = AbstractDungeon.miscRng.random(0, CheapestCards.size()-1);
            CheapestCards.get(r).setCostForTurn(0);
        }
        else
        {
            logger.info("something went very wrong!");
        }
        this.isDone = true;
    }
}

package StuffTheSpire.actions;

import StuffTheSpire.relics.Expresso;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;

import static com.evacipated.cardcrawl.mod.stslib.StSLib.getMasterDeckEquivalent;

public class SortDrawPile extends AbstractGameAction {
    private int cardcost = 99;
    private ArrayList<AbstractCard> CheapestCards = new ArrayList();
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());
    public SortDrawPile()
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.isDone = false;
    }
    @Override
    public void update() {
        sortWithComparator(new CardAgeComparator(), AbstractDungeon.player.drawPile);
        this.isDone = true;
    }
    private void sortWithComparator(Comparator<AbstractCard> comp, CardGroup c)
    {
        c.group.sort(comp);
    }
    private class CardAgeComparator implements Comparator<AbstractCard>
    {
        private CardAgeComparator() {}
        public int compare(AbstractCard c1, AbstractCard c2)
        {
            if(getMasterDeckEquivalent(c1)!= null && getMasterDeckEquivalent(c2)!= null)
            {
                if(AbstractDungeon.player.masterDeck.group.indexOf(getMasterDeckEquivalent(c1)) > AbstractDungeon.player.masterDeck.group.indexOf(getMasterDeckEquivalent(c2)))
                {
                    logger.info("1");
                    logger.info("eeeee");
                    return 1;
                }
                if(AbstractDungeon.player.masterDeck.group.indexOf(getMasterDeckEquivalent(c1)) < AbstractDungeon.player.masterDeck.group.indexOf(getMasterDeckEquivalent(c2)))
                {
                    logger.info("-1");
                    logger.info("fffff");
                    return -1;
                }
            }
            else if(getMasterDeckEquivalent(c1)!= null && getMasterDeckEquivalent(c2)== null)
            {
                return 1;
            }
            else if(getMasterDeckEquivalent(c1)== null && getMasterDeckEquivalent(c2)!= null)
            {
                return -1;
            }
            else
            {
                logger.info("0");
            }
            return 0;
        }
    }
}

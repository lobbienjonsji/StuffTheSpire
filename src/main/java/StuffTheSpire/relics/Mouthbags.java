package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Mouthbags extends CustomRelic implements CustomSavable<Integer> {
    public static final String ID = StuffTheSpireMod.makeID("Mouthbags");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("mouthbags.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("mouthbags.png"));
    private boolean cardsSelected = true;
    AbstractCard selectedcard = null;
    public Mouthbags() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip()
    {
        this.cardsSelected = false;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if ((card.type != AbstractCard.CardType.CURSE )) {
                tmp.addToTop(card);
            }
        }
        if (tmp.group.isEmpty())
        {
            this.cardsSelected = true;
            return;
        }
        if (tmp.group.size() <= 1)
        {
            AbstractDungeon.player.masterDeck.group.clear();
            for (AbstractCard card : tmp.group)
            {
                AbstractDungeon.player.masterDeck.removeCard(card);
                card.baseBlock += 4;
                card.block = card.baseBlock;
                card.isBlockModified = true;
                card.baseDamage += 4;
                card.damage = card.baseDamage;
                card.isDamageModified = true;
                AbstractDungeon.player.masterDeck.addToTop(card);
                selectedcard = card;
            }
            this.cardsSelected = true;
        }
        else if (!AbstractDungeon.isScreenUp)
        {
            AbstractDungeon.gridSelectScreen.open(tmp, 1, this.DESCRIPTIONS[1], false, false, false, false);
        }
        else
        {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
            AbstractDungeon.gridSelectScreen.open(tmp, 1, this.DESCRIPTIONS[1], false, false, false, false);
        }
    }

    public void update()
    {
        super.update();
        if ((!this.cardsSelected) &&
                (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1))
        {
            this.cardsSelected = true;
            Iterator<AbstractCard> i = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
            while (i.hasNext())
            {
                AbstractCard card = i.next();
                card.untip();
                card.unhover();
                AbstractDungeon.player.masterDeck.removeCard(card);
                card.baseBlock += 4;
                card.block = card.baseBlock;
                card.isBlockModified = true;
                card.baseDamage += 4;
                card.damage = card.baseDamage;
                card.isDamageModified = true;
                AbstractDungeon.player.masterDeck.addToTop(card);
                selectedcard = card;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }

    public AbstractRelic makeCopy()
    {
        return new Mouthbags();
    }

    @Override
    public Integer onSave() {
        return AbstractDungeon.player.masterDeck.group.indexOf(selectedcard);
    }

    @Override
    public void onLoad(Integer integer) {
        AbstractCard card = AbstractDungeon.player.masterDeck.group.get(integer);
        AbstractDungeon.player.masterDeck.removeCard(card);
        card.baseBlock += 4;
        card.block = card.baseBlock;
        card.isBlockModified = true;
        card.baseDamage += 4;
        card.damage = card.baseDamage;
        card.isDamageModified = true;
        AbstractDungeon.player.masterDeck.addToTop(card);
        selectedcard = card;
    }
}

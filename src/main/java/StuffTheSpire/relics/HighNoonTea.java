package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class HighNoonTea extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Tea");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("tea.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("tea.png"));

    public HighNoonTea() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atPreBattle()
    {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        this.counter += 1;
        if (this.counter >= 12)
        {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractCard c:AbstractDungeon.player.hand.group) {
                if(c.type.equals(AbstractCard.CardType.ATTACK)&&c.costForTurn > 0)
                {
                    c.setCostForTurn(c.costForTurn - 1);
                }
            }
        }
    }

    public void onVictory()
    {
        this.counter = -1;
    }
}

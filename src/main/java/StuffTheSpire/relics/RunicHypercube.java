package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class RunicHypercube extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("RunicHypercube");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public RunicHypercube() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }
    @Override
    public void atTurnStart()
    {
        if (GameActionManager.turn >= 1)
        {
            AbstractDungeon.player.gameHandSize = AbstractDungeon.player.masterHandSize;
            if(3 - this.counter < 0)
            {
                AbstractDungeon.actionManager.addToTop (new LoseEnergyAction( this.counter - 3 ));
            }
            else {
                AbstractDungeon.actionManager.addToTop (new GainEnergyAction(3 - this.counter));
            }
            AbstractDungeon.player.gameHandSize += (5 - this.counter);
        }
    }

    @Override
    public void onPlayerEndTurn() {
        int charge = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group)
        {
            if(c.cost > 0)
            {
                charge += c.cost;
            }
        }
        this.counter = charge;
    }
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}

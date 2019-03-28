package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class PaperKangaroo extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Kangaroo");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public PaperKangaroo() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    public void atTurnStartPostDraw() {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p.amount > 1) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p.owner, p.owner, p.ID, 1));
            }else if (p.amount <= 1 && p.amount >= -1) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p.owner, p.owner, p.ID));
            }else if (p.amount < -1) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p.owner, p.owner, p.ID, -1));
            }
        }
    }
}



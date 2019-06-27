package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Experiment12 extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Experiment12");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Experiment12.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Experiment12.png"));
    private static final RelicTier TIER = RelicTier.COMMON;

    public Experiment12() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.costForTurn > 1) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, 2));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

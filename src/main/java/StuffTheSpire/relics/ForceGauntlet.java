package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.powers.ForceGauntletPower;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class ForceGauntlet extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("ForceGauntlet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("gauntlet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("gauntlet.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public ForceGauntlet() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart()
    {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ForceGauntletPower(AbstractDungeon.player, AbstractDungeon.player, 1)));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}

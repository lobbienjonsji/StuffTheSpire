package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.OldFeverAction;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class OldFever extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("OldFever");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("oldfever.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("oldfever.png"));
    private static final RelicTier TIER = RelicTier.COMMON;

    public OldFever() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new OldFeverAction());
    }
}

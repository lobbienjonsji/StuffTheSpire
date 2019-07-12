package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class TintedRock extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("TintedRock");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TintedRock.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TintedRock.png"));
    private static final RelicTier TIER = RelicTier.COMMON;

    public TintedRock() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.actNum <= 1;
    }
}

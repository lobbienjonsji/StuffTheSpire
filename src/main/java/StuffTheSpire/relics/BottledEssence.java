package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class BottledEssence extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("BottledEssence");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BottledEssence.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BottledEssence.png"));
    private static final RelicTier TIER = RelicTier.SHOP;

    public BottledEssence() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

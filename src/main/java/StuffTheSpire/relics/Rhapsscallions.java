package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Rhapsscallions extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Rhapsscallions");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Rhapsscallions.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Rhapsscallions.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public Rhapsscallions() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

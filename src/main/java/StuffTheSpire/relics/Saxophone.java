package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Saxophone extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Saxophone");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Saxophone.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Saxophone.png"));
    private static final RelicTier TIER = RelicTier.SHOP;

    public Saxophone() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

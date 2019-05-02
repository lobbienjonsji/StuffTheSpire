package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class DarkSteelAnvil extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("DarkSteelAnvil");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DarkSteelAnvil.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DarkSteelAnvil.png"));
    private static final RelicTier TIER = RelicTier.RARE;

    public DarkSteelAnvil() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

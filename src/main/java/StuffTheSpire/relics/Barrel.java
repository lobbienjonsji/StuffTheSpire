package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Barrel extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Barrel");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Barrel.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Barrel.png"));
    private static final RelicTier TIER = RelicTier.COMMON;

    public Barrel() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onVictory() {
        if (AbstractDungeon.player.gold <= 50) {
            AbstractDungeon.player.heal(8, true);
        }
    }
}

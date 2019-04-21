package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class RunicConfusion extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("RunicConfusion");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("confusion.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("confusion.png"));
    private static final RelicTier TIER = RelicTier.BOSS;

    public RunicConfusion() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public void onEquip()
    {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    public void onUnequip()
    {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }
}

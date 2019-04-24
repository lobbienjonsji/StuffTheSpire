package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class SapphireSignet extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = StuffTheSpireMod.makeID("SapphireSignet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("saphsignet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("basesignet.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public SapphireSignet() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (abstractPower.ID == "Frail")
        {
            AbstractDungeon.player.heal(2);
        }
        return true;
    }
}

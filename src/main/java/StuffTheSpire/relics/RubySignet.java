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

public class RubySignet extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = StuffTheSpireMod.makeID("RubySignet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rubysignet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("basesignet.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public RubySignet() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if(abstractPower.ID == "Vulnerable" && abstractCreature.isPlayer)
        {
            AbstractDungeon.player.heal(2);
        }
        return true;
    }
}

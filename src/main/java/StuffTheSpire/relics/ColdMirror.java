package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class ColdMirror extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("ColdMirror");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("coldmirror.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("coldmirror.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public ColdMirror() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public void onEvokeOrb(final AbstractOrb orb) {
        if (orb != null && this.counter == 0) {
            this.counter = 1;
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Frost()));
        }
    }
}

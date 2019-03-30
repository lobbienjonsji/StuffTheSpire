package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class LittleGreenCactus extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("MyLittleGreenCactus");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public LittleGreenCactus() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public int onAttackedMonster(DamageInfo info, int damageAmount) {
        if ((damageAmount > 0) && (info.owner != AbstractDungeon.player) && (info.type == DamageInfo.DamageType.THORNS))
        {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, AbstractDungeon.player, new PoisonPower(info.owner, AbstractDungeon.player, 1), 1, true));
        }

        return damageAmount;
    }
}

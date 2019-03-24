package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class NeowsTribute extends CustomRelic implements ClickableRelic { // You must implement things you want to use from StSlib
    public static final String ID = StuffTheSpireMod.makeID("NeowsTribute");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public NeowsTribute() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
        this.counter = 0;
    }
    @Override
    public void onRightClick() {
        if (!isObtained || this.counter < 1) {
            return;
        }
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.counter --;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, 1)));
            flash();
        }
        if(this.counter < 1)
        {
            stopPulse();
        }
    }
    public void atTurnStart() {
        if ( this.counter > 1) {
            beginLongPulse();
        }
    }

    @Override
    public void atPreBattle() {
        if ( this.counter > 1) {
            beginLongPulse();
        }
    }
    @Override
    public void onVictory() {
        stopPulse();
        if(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite)
        {
            this.counter ++;
        }
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

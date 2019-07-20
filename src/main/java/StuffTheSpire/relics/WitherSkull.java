package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class WitherSkull extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("WitherSkull");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("skull.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("skull.png"));
    private boolean isActive;

    public WitherSkull() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }
    public void atBattleStart() {
        this.isActive = false;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (!WitherSkull.this.isActive && AbstractDungeon.player.isBloodied) {
                    WitherSkull.this.flash();
                    WitherSkull.this.pulse = true;
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoxiousFumesPower(AbstractDungeon.player, 2), 2));
                    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, WitherSkull.this));
                    WitherSkull.this.isActive = true;
                    AbstractDungeon.onModifyPower();
                }

                this.isDone = true;
            }
        });
    }

    public void onBloodied() {
        this.flash();
        this.pulse = true;
        if (!this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new NoxiousFumesPower(p, 3), 2));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.isActive = true;
            AbstractDungeon.player.hand.applyPowers();
        }

    }

    public void onNotBloodied() {
        if (this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new NoxiousFumesPower(p, -2), -2));
        }
        this.stopPulse();
        this.isActive = false;
        AbstractDungeon.player.hand.applyPowers();
    }

    public void onVictory() {
        this.pulse = false;
        this.isActive = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

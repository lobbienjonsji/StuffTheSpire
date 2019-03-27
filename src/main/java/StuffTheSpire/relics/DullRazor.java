package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class DullRazor extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("DullRazor");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private int cardcost = 99;
    private ArrayList<AbstractCard> CheapestCards = new ArrayList();
    public DullRazor() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.CLINK);
    }
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public void atTurnStartPostDraw()
    {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
        {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                c.tookDamage();
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                c.tookDamage();
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                c.tookDamage();
            }
            for (AbstractRelic r :  AbstractDungeon.player.relics) {
                r.onLoseHp(1);
            }
            for (AbstractPower p :  AbstractDungeon.player.powers) {
                p.onLoseHp(1);
            }
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onAttacked(new DamageInfo(AbstractDungeon.player, 1), 1);
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onAttacked(new DamageInfo(AbstractDungeon.player, 1), 1);
            }
        }
    }


}

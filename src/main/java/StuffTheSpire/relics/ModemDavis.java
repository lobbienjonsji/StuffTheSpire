package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class ModemDavis extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("ModemDavis");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ModemDavis.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ModemDavis.png"));
    private static final RelicTier TIER = RelicTier.BOSS;

    public ModemDavis() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onVictory() {
        this.counter = -1;
    }

    public void atPreBattle() {
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.cost == 1) {
            counter++;
        }
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        if (counter < 2) {
            return true;
        }
        return card.cost != 1;
    }

    @Override
    public void onPlayerEndTurn() {
        counter = 0;
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }
}

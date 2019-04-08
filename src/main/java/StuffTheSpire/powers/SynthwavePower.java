package StuffTheSpire.powers;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SynthwavePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = StuffTheSpireMod.makeID("SynthwavePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power32.png");
    public SynthwavePower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0]+ this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0]+ this.amount + DESCRIPTIONS[2];
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new SynthwavePower(owner, amount);
    }
    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        if(isPlayer) {
            for (int i = 0; i < this.amount; i++) {
                for (AbstractOrb O : AbstractDungeon.player.orbs) {
                    if (O instanceof Lightning) {
                        O.onEndOfTurn();
                        O.onStartOfTurn();
                    }
                }
            }
        }
    }
}

package StuffTheSpire.powers;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IncursionPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = StuffTheSpireMod.makeID("IncursionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power32.png");
    private static boolean multiattackagainstsingleenemy = false;
    private static int enemynumber = 0;
    public IncursionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.DEBUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public void atStartOfTurn()
    {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "StuffTheSpire:IncursionPower"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "StuffTheSpire:IncursionPower", 1));
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new IncursionPower(owner, source, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        return damageAmount*2;
    }
    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
}


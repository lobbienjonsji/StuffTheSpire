package StuffTheSpire.powers;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashMap;

import static StuffTheSpire.StuffTheSpireMod.Dark_Fade;

public class DarkFadePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = StuffTheSpireMod.makeID("DarkFadePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StuffTheSpireResources/images/powers/placeholder_power32.png");
    private HashMap<AbstractMonster, DamageInfo> Damage = new  HashMap<>();
    public DarkFadePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    public void onInitialApplication()
    {
        Dark_Fade.ishidden = false;
    }
    public void atEndOfRound()
    {

    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    @Override
    public void onVictory()
    {
        Damage.clear();
        Dark_Fade.ishidden = true;
    }
    @Override
    public void atStartOfTurn()
    {
        for(AbstractMonster m: Damage.keySet()) {
            if (!m.halfDead && !m.isDying && !m.escaped) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, Damage.get(m).output, DamageInfo.DamageType.THORNS)));

            }
        }
        Damage.clear();
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "StuffTheSpire:DarkFadePower"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "StuffTheSpire:DarkFadePower", 1));
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        if(target instanceof AbstractMonster)
        {
            if (!Damage.containsKey(target))
            {
                Damage.put((AbstractMonster) target, new DamageInfo(AbstractDungeon.player,(int)Math.floor(damageAmount*0.75), DamageInfo.DamageType.THORNS));
            }
            else
            {
                int amount = Damage.get(target).base;
                Damage.put((AbstractMonster) target, new DamageInfo(AbstractDungeon.player,(int)Math.floor(damageAmount*0.75) + amount, DamageInfo.DamageType.THORNS));
            }
        }
    }
    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
    @Override
    public AbstractPower makeCopy() {
        return new PointBlankPower(owner, source, amount);
    }
    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        if(isPlayer) {
        }
    }
    @Override
    public void onRemove()
    {
        Dark_Fade.ishidden = true;
    }

    @Override
    public void onDeath() {
        Dark_Fade.ishidden = true;
    }
}

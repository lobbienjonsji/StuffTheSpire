package StuffTheSpire.actions;

import StuffTheSpire.relics.Expresso;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GravityAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractMonster m;
    private DamageAction action;
    private ArrayList<AbstractCard> CheapestCards = new ArrayList();
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());
    public GravityAction(AbstractMonster t, DamageAction action, DamageInfo info)
    {
        this.info = info;
        this.m = t;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DAMAGE;
        this.isDone = false;
        this.action = action;
    }
    @Override
    public void update() {
        Boolean willLive = Boolean.valueOf(calculateDamageTakenAmount(info.output, info.type) < m.currentHealth);
        if ((info.owner != null) && (this.m.hasPower("Flight")) && (m.getPower("Flight").amount <=1)&&(info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && (info.output > 0) && (willLive.booleanValue()))
        {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, "GravityPower"));
            AbstractDungeon.actionManager.addToBottom(this.action);
        }
        this.isDone = true;
    }
    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type)
    {
        if ((type != DamageInfo.DamageType.HP_LOSS) && (type != DamageInfo.DamageType.THORNS)) {
            return damage / 2.0F;
        }
        return damage;
    }
}

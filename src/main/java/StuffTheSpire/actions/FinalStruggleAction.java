package StuffTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RepairPower;

public class FinalStruggleAction extends AbstractGameAction {
    private AbstractMonster m;
    private float Hploss;
    private DamageInfo damage;

    public FinalStruggleAction(AbstractMonster t, float Hploss, DamageInfo damage) {
        m = t;
        this.Hploss = Hploss;
        this.damage = damage;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, (int) Hploss, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RepairPower(AbstractDungeon.player, ((int) Hploss)), (int) Hploss));
        if (!(m.isDying || m.isDead || m.isDeadOrEscaped() || m.halfDead)) {
            AbstractDungeon.actionManager.addToBottom(new FinalStruggleAction(m, Hploss, damage));
        }
        this.isDone = true;
    }
}

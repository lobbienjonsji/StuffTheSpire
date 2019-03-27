package StuffTheSpire.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class ApplyPowerPatch {
    @SpirePatch(clz = ApplyPowerAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez={
            AbstractCreature.class,
            AbstractCreature.class,
            AbstractPower.class,
            int.class,
            boolean.class,
            AttackEffect.class
    })
    public static class ConstructorPatch {
        @SpirePostfixPatch
        public static void Patch(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source,
                                 AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
            if (target.isPlayer) {
                if (AbstractDungeon.player.hasRelic("StuffTheSpire:Kangaroo") && (powerToApply.type.equals(AbstractPower.PowerType.BUFF)) && __instance.amount >= 0) {
                    powerToApply.amount += 2;
                    __instance.amount += 2;
                }
            }
        }
    }
}
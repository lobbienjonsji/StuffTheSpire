package StuffTheSpire.patches.relics;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnReceivePowerPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

@SpirePatch(clz = ApplyPowerAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez={
        AbstractCreature.class,
        AbstractCreature.class,
        AbstractPower.class,
        int.class,
        boolean.class,
        AttackEffect.class
})
public class PowerConstructorPatch {
    @SpirePostfixPatch
    public static void Patch(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source,
                             AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
        if(target.isPlayer)
        {
            if (AbstractDungeon.player.hasRelic("stuffthespire:Kangaroo") && (powerToApply.type.equals(AbstractPower.PowerType.BUFF)) && stackAmount >= 0) {
                try {
                    Field field = ApplyPowerAction.class.getField("powerToApply");
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
                powerToApply.amount += 1;
                __instance.amount += 1;

            }
        }
    }

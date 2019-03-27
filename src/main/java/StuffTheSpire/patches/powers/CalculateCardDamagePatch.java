package StuffTheSpire.patches.powers;

import StuffTheSpire.StuffTheSpireMod;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
public class CalculateCardDamagePatch {
    private static int enemynumber = 0;
    @SpirePostfixPatch
    public static void Patch(AbstractCard __instance, AbstractMonster m){
        enemynumber = 0;
        for (AbstractMonster mo: AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDying || !mo.isDead)
            {
                enemynumber++;
            }
        }
        if(AbstractDungeon.player.hasPower(StuffTheSpireMod.makeID("PointBlankPower"))&& __instance.target == AbstractCard.CardTarget.ALL_ENEMY && enemynumber <= 1)
        {
            __instance.damage = MathUtils.floor((float) (__instance.damage * 1.5));
            __instance.isDamageModified = true;
        }
    }
}
package StuffTheSpire.patches.powers;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.relics.Expresso;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
public class CalculateCardDamagePatch {
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());
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
        logger.info("Calculated Card Damage");
        logger.info(enemynumber);
    }
}
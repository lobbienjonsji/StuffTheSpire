package StuffTheSpire.patches.powers;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.relics.Expresso;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(clz = AbstractCard.class, method = "applyPowers")
public class ApplyPowersPatch {
    private static final Logger logger = LogManager.getLogger(Expresso.class.getName());
    private static int enemynumber = 0;
    @SpirePostfixPatch
    public static void Patch(AbstractCard __instance){
        if (AbstractDungeon.player.hasRelic("StuffTheSpire:RunicConfusion")) {
            __instance.rawDescription = "???";
            __instance.initializeDescription();
        }
    }
}
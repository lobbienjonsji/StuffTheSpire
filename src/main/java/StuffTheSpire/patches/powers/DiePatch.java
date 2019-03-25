package StuffTheSpire.patches.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez={
        boolean.class
})
public class DiePatch {
    @SpirePostfixPatch
    public static void Patch(AbstractMonster __instance, boolean triggerrelics){
        for (AbstractCard c: AbstractDungeon.player.hand.group)
        {
            c.applyPowers();
        }
    }
}
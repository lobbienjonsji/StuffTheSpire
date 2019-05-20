package StuffTheSpire.patches.cards;

import StuffTheSpire.cards.blue.NuclearReactor;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;

@SpirePatch(clz = Frost.class, method = "onEvoke")
public class EvokeIcePatch {
    @SpirePostfixPatch
    public static void Patch(Frost __instance) {
        NuclearReactor.FrostEvokedThisturn++;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof NuclearReactor) {
                c.applyPowers();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof NuclearReactor) {
                c.setCostForTurn(c.costForTurn - 1);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof NuclearReactor) {
                c.applyPowers();
            }
        }
    }
}

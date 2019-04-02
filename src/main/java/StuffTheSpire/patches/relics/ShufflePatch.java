package StuffTheSpire.patches.relics;

import StuffTheSpire.actions.SortDrawPile;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class ShufflePatch {
    @SpirePatch(clz = EmptyDeckShuffleAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class ConstructorPatch {
        @SpirePostfixPatch
        public static void Patch(EmptyDeckShuffleAction __instance) {
            if (AbstractDungeon.player.hasRelic("StuffTheSpire:HistoryBook")) {
                AbstractDungeon.actionManager.addToTop(new SortDrawPile());
            }
        }
    }
    @SpirePatch(clz = ShuffleAction.class, method = SpirePatch.CONSTRUCTOR,paramtypez={
            CardGroup.class,
            boolean.class
    })
    public static class otherConstructorPatch {
        @SpirePostfixPatch
        public static void Patch(ShuffleAction __instance, CardGroup blebb, boolean blubb) {
            if (AbstractDungeon.player.hasRelic("StuffTheSpire:HistoryBook")) {
                AbstractDungeon.actionManager.addToTop(new SortDrawPile());
            }
        }
    }
}
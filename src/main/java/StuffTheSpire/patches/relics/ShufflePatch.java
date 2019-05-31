package StuffTheSpire.patches.relics;

import StuffTheSpire.actions.SortDrawPileAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class ShufflePatch {
    @SpirePatch(clz = EmptyDeckShuffleAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class ConstructorPatch {
        @SpirePostfixPatch
        public static void Patch(EmptyDeckShuffleAction __instance) {
            if (AbstractDungeon.player.hasRelic("StuffTheSpire:HistoryBook")) {
                AbstractDungeon.actionManager.addToTop(new SortDrawPileAction());
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
                AbstractDungeon.actionManager.addToTop(new SortDrawPileAction());
            }
        }
    }
}
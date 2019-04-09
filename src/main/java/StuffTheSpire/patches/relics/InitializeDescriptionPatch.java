package StuffTheSpire.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class InitializeDescriptionPatch {
    @SpirePatch(clz = AbstractCard.class, method = "initializeDescription")
    public static class otherConstructorPatch {
        @SpirePrefixPatch
        public static void Patch(AbstractCard __instance) {
            if (AbstractDungeon.player!= null && AbstractDungeon.player.hasRelic("StuffTheSpire:RunicConfusion")) {
                __instance.rawDescription = "???";
            }
        }
    }
}
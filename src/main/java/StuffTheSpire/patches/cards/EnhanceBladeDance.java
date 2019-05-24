package StuffTheSpire.patches.cards;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class EnhanceBladeDance {
    @SpirePatch(clz = BladeDance.class, method = "initializeDescription")
    public static class onInitializeDesc {
        private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ShivArche");

        @SpirePostfixPatch
        public static void Patch(BladeDance __instance) {
            if (Loader.isModLoaded("archetypeapi") && !__instance.upgraded) {
                __instance.rawDescription = BladeDance.DESCRIPTION + cardStrings.DESCRIPTION;
            }
        }
    }
}

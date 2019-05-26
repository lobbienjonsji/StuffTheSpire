package StuffTheSpire.patches.cards;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.BallLightning;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class EnhanceArchetypeCards {
    @SpirePatch(clz = AbstractCard.class, method = "initializeDescription")
    public static class onInitializeDesc {
        private static final CardStrings BDSTRINGS = CardCrawlGame.languagePack.getCardStrings("ShivArchetype");
        private static final CardStrings BLSTRINGS = CardCrawlGame.languagePack.getCardStrings("LightningArchetype");
        private static final CardStrings PSSTRINGS = CardCrawlGame.languagePack.getCardStrings("StrikeArchetype");
        private static final CardStrings PPSTRINGS = CardCrawlGame.languagePack.getCardStrings("DiscardArchetype");
        @SpirePostfixPatch
        public static void Patch(AbstractCard __instance) {
            if (!(__instance == null) && __instance instanceof BladeDance) {
                if (Loader.isModLoaded("archetypeapi") && !__instance.upgraded) {
                    __instance.rawDescription = BladeDance.DESCRIPTION + BDSTRINGS.DESCRIPTION;
                }
            }
            if (!(__instance == null) && __instance instanceof BallLightning) {
                if (Loader.isModLoaded("archetypeapi") && !__instance.upgraded) {
                    __instance.rawDescription = BallLightning.DESCRIPTION + BLSTRINGS.DESCRIPTION;
                }
            }
            if (!(__instance == null) && __instance instanceof PerfectedStrike) {
                if (Loader.isModLoaded("archetypeapi") && !__instance.upgraded) {
                    __instance.rawDescription = PerfectedStrike.DESCRIPTION + PSSTRINGS.DESCRIPTION;
                } else if (Loader.isModLoaded("archetypeapi") && __instance.upgraded) {
                    __instance.rawDescription = PerfectedStrike.UPGRADE_DESCRIPTION + PSSTRINGS.DESCRIPTION;
                }
            }
            if (!(__instance == null) && __instance instanceof Prepared) {
                if (Loader.isModLoaded("archetypeapi") && !__instance.upgraded) {
                    __instance.rawDescription = Prepared.DESCRIPTION + PPSTRINGS.DESCRIPTION;
                } else if (Loader.isModLoaded("archetypeapi") && __instance.upgraded) {
                    __instance.rawDescription = Prepared.UPGRADE_DESCRIPTION + PPSTRINGS.DESCRIPTION;
                }
            }
        }
    }

    @SpirePatch(clz = BladeDance.class, method = SpirePatch.CONSTRUCTOR)
    public static class bdconstrDesc {
        @SpirePostfixPatch
        public static void Patch(BladeDance __instance) {
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = BallLightning.class, method = SpirePatch.CONSTRUCTOR)
    public static class blconstrDesc {
        @SpirePostfixPatch
        public static void Patch(BallLightning __instance) {
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = PerfectedStrike.class, method = SpirePatch.CONSTRUCTOR)
    public static class psconstrDesc {
        @SpirePostfixPatch
        public static void Patch(PerfectedStrike __instance) {
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = Prepared.class, method = SpirePatch.CONSTRUCTOR)
    public static class ppconstrDesc {
        @SpirePostfixPatch
        public static void Patch(Prepared __instance) {
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = PerfectedStrike.class, method = "upgrade")
    public static class upgrPSDesc {
        @SpirePostfixPatch
        public static void Patch(PerfectedStrike __instance) {
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = Prepared.class, method = "upgrade")
    public static class upgrPPDesc {
        @SpirePostfixPatch
        public static void Patch(Prepared __instance) {
            __instance.initializeDescription();
        }
    }
}


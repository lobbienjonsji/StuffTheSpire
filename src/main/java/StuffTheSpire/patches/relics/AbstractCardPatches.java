package StuffTheSpire.patches.relics;

import StuffTheSpire.relics.BottledEssence;
import StuffTheSpire.relics.DarkSteelAnvil;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class AbstractCardPatches {
    @SpirePatch(clz = AbstractCard.class, method = "upgradeDamage")
    public static class DamagePatch {
        @SpirePrefixPatch
        public static void Patch(AbstractCard __instance, int amount) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(DarkSteelAnvil.ID)) {
                if (amount > 0) {
                    __instance.baseDamage += 2;
                }
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "upgradeBlock")
    public static class BlockPatch {
        @SpirePrefixPatch
        public static void Patch(AbstractCard __instance, int amount) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(DarkSteelAnvil.ID)) {
                if (amount > 0) {
                    __instance.baseBlock += 1;
                }
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "upgradeMagicNumber")
    public static class MagicPatch {
        @SpirePrefixPatch
        public static void Patch(AbstractCard __instance, int amount) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(DarkSteelAnvil.ID)) {
                if (amount > 0) {
                    __instance.baseMagicNumber += 1;
                } else if (amount < 0) {
                    __instance.baseMagicNumber -= 1;
                }
                __instance.magicNumber = __instance.baseMagicNumber;
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
    public static class ExhaustivePatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"card"})
        public static void Patch(AbstractCard __instance, AbstractCard card) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(BottledEssence.ID) && card.exhaust) {
                card.exhaust = false;
                ExhaustiveField.ExhaustiveFields.exhaustive.set(card, 2);
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "name");
                int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return new int[]{found[found.length - 1]};
            }
        }
    }
}

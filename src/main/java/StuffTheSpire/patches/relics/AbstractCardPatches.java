package StuffTheSpire.patches.relics;

import StuffTheSpire.relics.BottledEssence;
import StuffTheSpire.relics.DarkSteelAnvil;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

public class AbstractCardPatches {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ExhaustiveCard");
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
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
        @SpirePostfixPatch
        public static AbstractCard Patch(AbstractCard card, AbstractCard __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(BottledEssence.ID) && card.exhaust) {
                card.exhaust = false;
                ExhaustiveField.ExhaustiveFields.exhaustive.set(card, 2);
                card.rawDescription += DESCRIPTION;
                card.initializeDescription();
            }
            return card;
        }
    }
}

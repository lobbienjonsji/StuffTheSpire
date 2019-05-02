package StuffTheSpire.patches.relics;

import StuffTheSpire.relics.DarkSteelAnvil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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
}

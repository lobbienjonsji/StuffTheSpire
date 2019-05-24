package StuffTheSpire.patches.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.relics.ChainArchetype;
import StuffTheSpire.relics.ShivArchetype;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShowCardAndObtainPatch {
    @SpirePatch(clz = SoulGroup.class, method = "obtain")
    public static class ShowCardAndObtainEffectPatch
    {
        public static SpireReturn Prefix(SoulGroup __instance, AbstractCard card, boolean obtainCard)
        {
            if (card.hasTag(CardTags.CHAINARCHETYPE) && !StuffTheSpireMod.HasArchetypeRelic)
            {
                ChainArchetype relic = new ChainArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            if (card.cardID.equals(BladeDance.ID) && Loader.isModLoaded("archetypeapi") && !StuffTheSpireMod.HasArchetypeRelic) {
                ShivArchetype relic = new ShivArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            return SpireReturn.Continue();
        }
    }
}

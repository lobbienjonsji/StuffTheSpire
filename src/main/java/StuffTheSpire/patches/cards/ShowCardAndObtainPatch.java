package StuffTheSpire.patches.cards;

import StuffTheSpire.relics.ChainArchetype;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShowCardAndObtainPatch {
    @SpirePatch(clz = SoulGroup.class, method = "obtain")
    public static class ShowCardAndObtainEffectPatch
    {
        public static SpireReturn Prefix(SoulGroup __instance, AbstractCard card, boolean obtainCard)
        {
            if (card.hasTag(CardTags.CHAINARCHETYPE) && !(AbstractDungeon.player.hasRelic(ChainArchetype.ID)))
            {
                ChainArchetype relic = new ChainArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            return SpireReturn.Continue();
        }
    }
}

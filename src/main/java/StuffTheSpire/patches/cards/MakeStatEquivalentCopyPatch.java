package StuffTheSpire.patches.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
public class MakeStatEquivalentCopyPatch {
    @SpirePrefixPatch
    public static SpireReturn<AbstractCard> Patch(AbstractCard __instance){
       if(__instance.hasTag(CardTags.UNIQUE)&& !AbstractDungeon.player.masterDeck.contains(__instance))
       {
           return SpireReturn.Return(new Dazed());
       }
       return SpireReturn.Continue();
    }
}

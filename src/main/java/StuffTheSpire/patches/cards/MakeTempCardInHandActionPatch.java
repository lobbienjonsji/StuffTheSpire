package StuffTheSpire.patches.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


@SpirePatch(clz = MakeTempCardInHandAction.class, method = SpirePatch.CONSTRUCTOR,paramtypez={
        AbstractCard.class,
        int.class
})
public class MakeTempCardInHandActionPatch {
    @SpirePostfixPatch
    public static void Patch(MakeTempCardInHandAction __instance, AbstractCard c, int amount){
       if(c.hasTag(CardTags.UNIQUE))
       {
           AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Dazed(), 1));
           __instance.isDone = true;
       }
    }
}

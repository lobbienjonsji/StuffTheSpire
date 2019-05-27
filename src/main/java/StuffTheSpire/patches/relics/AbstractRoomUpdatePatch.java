package StuffTheSpire.patches.relics;

import StuffTheSpire.relics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;

@SpirePatch (
        clz = AbstractRoom.class,
        method = "update"
)
public class AbstractRoomUpdatePatch {
    @SpireInsertPatch (
            locator = Locator.class
    )
    public static void Insert(AbstractRoom __instance) {
        AbstractRelic ChainArcheType = AbstractDungeon.player.getRelic(ChainArchetype.ID);
        if (ChainArcheType != null) {
            ChainArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
        AbstractRelic ShivArcheType = AbstractDungeon.player.getRelic(ShivArchetype.ID);
        if (ShivArcheType != null) {
            ShivArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
        AbstractRelic LightningArcheType = AbstractDungeon.player.getRelic(LightningArchetype.ID);
        if (LightningArcheType != null) {
            LightningArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
        AbstractRelic StrikeArcheType = AbstractDungeon.player.getRelic(StrikeArchetype.ID);
        if (StrikeArcheType != null) {
            StrikeArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
        AbstractRelic DiscardArcheType = AbstractDungeon.player.getRelic(DiscardArchetype.ID);
        if (DiscardArcheType != null) {
            DiscardArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
        AbstractRelic FrostArcheType = AbstractDungeon.player.getRelic(FrostArchetype.ID);
        if (FrostArcheType != null) {
            FrostArcheType.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "loading_post_combat");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{found[found.length - 1]};
        }
    }
}

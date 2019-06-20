package StuffTheSpire.patches.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.blessings.Perfection;
import StuffTheSpire.relics.*;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.cards.blue.BallLightning;
import com.megacrit.cardcrawl.cards.blue.ColdSnap;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Collections;

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
            if (card.cardID.equals(BallLightning.ID) && Loader.isModLoaded("archetypeapi") && !StuffTheSpireMod.HasArchetypeRelic) {
                LightningArchetype relic = new LightningArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            if (card.cardID.equals(PerfectedStrike.ID) && Loader.isModLoaded("archetypeapi") && !StuffTheSpireMod.HasArchetypeRelic) {
                StrikeArchetype relic = new StrikeArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            if (card.cardID.equals(Prepared.ID) && Loader.isModLoaded("archetypeapi") && !StuffTheSpireMod.HasArchetypeRelic) {
                DiscardArchetype relic = new DiscardArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            if (card.cardID.equals(ColdSnap.ID) && Loader.isModLoaded("archetypeapi") && !StuffTheSpireMod.HasArchetypeRelic) {
                FrostArchetype relic = new FrostArchetype();
                relic.updateDescription(AbstractDungeon.player.chosenClass);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relic);
            }
            if (card.cardID.equals(Perfection.ID)) {
                ArrayList<AbstractCard> upgradableCards = new ArrayList();
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if ((c.canUpgrade())) {
                        upgradableCards.add(c);
                    }
                }
                Collections.shuffle(upgradableCards, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
                if (!upgradableCards.isEmpty()) {
                    if (upgradableCards.size() == 1) {
                        upgradableCards.get(0).upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(
                                upgradableCards.get(0).makeStatEquivalentCopy()));
                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    } else if (upgradableCards.size() == 2) {
                        upgradableCards.get(0).upgrade();
                        upgradableCards.get(1).upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(

                                upgradableCards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(

                                upgradableCards.get(1).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    } else {
                        upgradableCards.get(0).upgrade();
                        upgradableCards.get(1).upgrade();
                        upgradableCards.get(2).upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                        AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(2));
                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(

                                upgradableCards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(

                                upgradableCards.get(1).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(

                                upgradableCards.get(2).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }
}

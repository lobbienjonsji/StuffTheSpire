package StuffTheSpire.patches.relics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class RenderEnergyPatch {
    @SpirePatch(clz = AbstractCard.class, method = "renderEnergy", paramtypez={
            SpriteBatch.class
    })
    public static class Patch {
        @SpirePrefixPatch
        public static SpireReturn Patch(AbstractCard __instance, SpriteBatch b) {
                if (AbstractDungeon.player.hasRelic("StuffTheSpire:RunicConfusion")) {
                   return SpireReturn.Return(null);
                }
                return  SpireReturn.Continue();
            }
        }
    }


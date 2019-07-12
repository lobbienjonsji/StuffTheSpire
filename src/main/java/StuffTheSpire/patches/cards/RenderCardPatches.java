package StuffTheSpire.patches.cards;

import StuffTheSpire.StuffTheSpireMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import javassist.CtBehavior;

import static StuffTheSpire.StuffTheSpireMod.logger;

public class RenderCardPatches {
    @SpirePatch(clz = AbstractCard.class, method = "renderCardBg")
    public static class CardBgPatch {
        @SpirePrefixPatch
        public static SpireReturn patch(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (__instance.type == CardTypes.BLESSING) {
                renderHelper(sb, Color.WHITE.cpy(), StuffTheSpireMod.BlessingBgRegion, x, y, __instance);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard __instance) {
            sb.setColor(color);

            try {
                sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
                //sb.draw(img, drawX, drawY, 256.0F, 256.0F, 512.0F, 512.0F, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
            } catch (Exception var7) {
                logger.error(var7);
            }

        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderPortraitFrame")
    public static class CardPortraitPatch {
        @SpirePrefixPatch
        public static void patch(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (__instance.type == CardTypes.BLESSING) {
                renderHelper(sb, Color.WHITE.cpy(), StuffTheSpireMod.Frame, x, y, __instance);
            }
        }

        private static void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard __instance) {
            sb.setColor(color);

            try {
                sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);

            } catch (Exception var7) {
                logger.error(var7);
            }

        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class CardTypePatch {
        private static String[] Text = CardCrawlGame.languagePack.getUIString("AbstractBlessing").TEXT;

        @SpireInsertPatch(locator = Locator.class, localvars = {"text"})
        public static void patch(AbstractCard __instance, SpriteBatch sb, @ByRef String[] text) {
            if (__instance.type == CardTypes.BLESSING) {
                text[0] = Text[0];
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "angle");
                int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return found;
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "getCardBgAtlas")
    public static class BGAtlasPatch {
        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> patch(AbstractCard __instance) {
            if (__instance.type == CardTypes.BLESSING) {
                return SpireReturn.Return(ImageMaster.CARD_SKILL_BG_SILHOUETTE);
            }
            return SpireReturn.Continue();
        }
    }

    /*@SpirePatch(clz = AbstractCard.class, method = "renderPortrait")
    public static class CardTypePatch
    {
        private static String[] Text = CardCrawlGame.languagePack.getUIString("AbstractBlessing").TEXT;
        @SpireInsertPatch(locator = Locator.class, localvars = {"text"})
        public static void patch(AbstractCard __instance, SpriteBatch sb, @ByRef String[] text)
        {
            if(__instance.type == CardTypes.BLESSING)
            {
                text[0] = Text[0];
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "angle");
                int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return found;
            }
        }
    }*/
}

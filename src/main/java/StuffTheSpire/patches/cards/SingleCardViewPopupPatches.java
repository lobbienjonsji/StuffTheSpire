package StuffTheSpire.patches.cards;

import StuffTheSpire.cards.AbstractBlessing;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CtBehavior;

public class SingleCardViewPopupPatches {
    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderFrame")
    public static class CardTypePatch {
        private static String[] Text = CardCrawlGame.languagePack.getUIString("AbstractBlessing").TEXT;

        @SpireInsertPatch(locator = Locator.class, localvars = {"tmpImg"})
        public static void patch(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef TextureAtlas.AtlasRegion[] tmpImg) {
            if (((AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card")).type == CardTypes.BLESSING) {
                tmpImg[0] = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(SingleCardViewPopup.class, "card");
                int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return found;
            }
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
    public static class RenderCardTypeTextPatch {
        private static String[] Text = CardCrawlGame.languagePack.getUIString("AbstractBlessing").TEXT;

        @SpireInsertPatch(locator = Locator.class, localvars = {"label"})
        public static void patch(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef String[] label) {
            if (((AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card")).type == CardTypes.BLESSING) {
                label[0] = Text[0];
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
                int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return found;
            }
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "loadPortraitImg")
    public static class LoadCardPortraitPatch {
        @SpirePrefixPatch
        public static SpireReturn patch(SingleCardViewPopup __instance) {
            if (((AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card")).type == CardTypes.BLESSING) {
                ReflectionHacks.setPrivate(__instance, SingleCardViewPopup.class, "portraitImg", ((AbstractBlessing) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card")).getCardPortrait());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}

package StuffTheSpire.patches.cards;

public class CardGlowBorderPatch {
   /* @SpirePatch(clz = CardGlowBorder.class, method = SpirePatch.CONSTRUCTOR)
    public static class BorderPatch
    {
        @SpirePrefixPatch
        public static SpireReturn patch(CardGlowBorder __instance, AbstractCard card)
        {
            if(card.type == CardTypes.BLESSING)
            {
                logger.info("This is a blessing!");
                ReflectionHacks.setPrivate(__instance, Texture.class, "img", ImageMaster.CARD_SKILL_BG_SILHOUETTE);
                __instance.duration = 1.2F;
                if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                    ReflectionHacks.setPrivate(__instance, Color.class, "color", Color.valueOf("30c8dcff"));
                } else {
                    ReflectionHacks.setPrivate(__instance, Color.class, "color", Color.GREEN.cpy());
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }*/
}

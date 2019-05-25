package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static StuffTheSpire.StuffTheSpireMod.StrikeArchetype;
import static StuffTheSpire.StuffTheSpireMod.*;

public class StrikeArchetype extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("StrikeArchetype");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StrikeArchetype.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("StrikeArchetype.png"));
    private static final RelicTier TIER = RelicTier.SPECIAL;

    public StrikeArchetype() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
        HasArchetypeRelic = true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onTrigger() {
        int random = AbstractDungeon.cardRng.random(0, 2);
        if (random == 2) {
            RewardItem SpecialReward = new RewardItem();
            SpecialReward.type = RewardItem.RewardType.CARD;
            SpecialReward.text = DESCRIPTIONS[1];
            SpecialReward.cards.clear();
            AbstractCard c = GetRandomArchetype(StrikeArchetype);
            SpecialReward.cards.add(c.makeCopy());
            AbstractDungeon.getCurrRoom().rewards.add(0, SpecialReward);
        }
    }
}

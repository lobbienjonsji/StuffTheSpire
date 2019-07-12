package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.ShuffleCardCostsAction;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class GreaterSneckoEye extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("GreaterSneckoEye");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GreaterSneckoEye.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GreaterSneckoEye.png"));
    private static final RelicTier TIER = RelicTier.SPECIAL;

    public GreaterSneckoEye() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ShuffleCardCostsAction());
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
